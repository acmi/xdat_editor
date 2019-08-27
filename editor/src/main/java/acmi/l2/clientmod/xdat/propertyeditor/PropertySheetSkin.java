/*
 * Copyright (c) 2016 acmi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package acmi.l2.clientmod.xdat.propertyeditor;

import com.sun.javafx.scene.control.behavior.BehaviorBase;
import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.editor.AbstractPropertyEditor;
import org.controlsfx.property.editor.PropertyEditor;

import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertySheetSkin extends BehaviorSkinBase<PropertySheet, BehaviorBase<PropertySheet>> {
    private static final Logger log = Logger.getLogger(PropertySheetSkin.class.getName());

    private static final int MIN_COLUMN_WIDTH = 100;

    // create category-based accordion
    private final Accordion accordion = new Accordion();

    public PropertySheetSkin(PropertySheet control) {
        super(control, new BehaviorBase<>(control, Collections.emptyList()));

        getChildren().add(accordion);

        control.getItems().addListener((ListChangeListener.Change<? extends PropertySheet.Item> change) -> updatePropertySheetContainer());
    }

    private void updatePropertySheetContainer() {
        accordion.getPanes().clear();

        // group by category
        Map<String, List<PropertySheet.Item>> categoryMap = new TreeMap<>();
        for (PropertySheet.Item p : getSkinnable().getItems()) {
            String category = p.getCategory();
            List<PropertySheet.Item> list = categoryMap.computeIfAbsent(category, k -> new ArrayList<>());
            list.add(p);
        }

        for (String category : categoryMap.keySet()) {
            PropertyPane props = new PropertyPane(categoryMap.get(category));
            // Only show non-empty categories
            if (props.getChildrenUnmodifiable().size() > 0) {
                ScrollPane scrollPane = new ScrollPane(props);
                scrollPane.setFitToWidth(true);
                TitledPane pane = new TitledPane(category, scrollPane);
                pane.setExpanded(true);
                accordion.getPanes().add(pane);
            }
        }

        if (!accordion.getPanes().isEmpty())
            accordion.setExpandedPane(accordion.getPanes().get(0));
    }

    private class PropertyPane extends GridPane {

        PropertyPane(List<PropertySheet.Item> properties) {
            this(properties, 0);
        }

        PropertyPane(List<PropertySheet.Item> properties, int nestingLevel) {
            setVgap(5);
            setHgap(5);
            setPadding(new Insets(5, 15, 5, 15 + nestingLevel * 10));
            getStyleClass().add("property-pane");
            setItems(properties);
        }

        void setItems(List<PropertySheet.Item> properties) {
            getChildren().clear();

            int row = 0;

            for (PropertySheet.Item item : properties) {

                // filter properties
                String title = item.getName();

                // setup property label
                Label label = new Label(title);
                label.setTooltip(new Tooltip(item.getDescription()));
                label.setMinWidth(MIN_COLUMN_WIDTH);

                // show description as a tooltip
                String description = item.getDescription();
                if (description != null && !description.trim().isEmpty()) {
                    label.setTooltip(new Tooltip(description));
                }

                add(label, 0, row);

                try {
                    Field changeListeners = item.getClass().getDeclaredField("changeListeners");
                    changeListeners.setAccessible(true);
                    List<?> list = (List) changeListeners.get(item);
                    list.removeIf(listener -> listener.getClass().getName().contains("AbstractPropertyEditor"));
                } catch (ReflectiveOperationException e) {
                    log.log(Level.WARNING, "Couldn't remove AbstractPropertyEditor listener", e);
                }

                // setup property editor
                Node editor = getEditor(item);

                if (editor instanceof Region) {
                    ((Region) editor).setMinWidth(MIN_COLUMN_WIDTH);
                    ((Region) editor).setMaxWidth(Double.MAX_VALUE);
                }
                label.setLabelFor(editor);
                add(editor, 1, row);
                GridPane.setHgrow(editor, Priority.ALWAYS);

                row++;
            }

        }

        @SuppressWarnings("unchecked")
        private Node getEditor(PropertySheet.Item item) {
            PropertyEditor editor = getSkinnable().getPropertyEditorFactory().call(item);
            if (editor == null) {
                editor = new AbstractPropertyEditor<Object, TextField>(item, new TextField(), true) {
                    {
                        getEditor().setEditable(false);
                        getEditor().setDisable(true);
                    }

                    @Override
                    protected ObservableValue getObservableValue() {
                        return getEditor().textProperty();
                    }

                    @Override
                    public void setValue(Object value) {
                        getEditor().setText(value == null ? "" : value.toString());
                    }
                };
            }
            editor.setValue(item.getValue());
            return editor.getEditor();
        }
    }
}
