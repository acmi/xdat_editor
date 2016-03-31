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

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.property.editor.AbstractPropertyEditor;

import java.util.HashMap;
import java.util.Map;

public class SysstringPropertyEditor extends AbstractPropertyEditor<Integer, TextField> {
    public static Map<Integer, String> strings = new HashMap<>();

    private ObservableValue<Integer> value;

    public SysstringPropertyEditor(PropertySheet.Item property) {
        super(property, createTextField());

        Tooltip tooltip = new Tooltip();
        tooltip.textProperty().bind(Bindings.createStringBinding(() -> strings.containsKey(getValue()) ? strings.get(getValue()) : "", getObservableValue()));
        getEditor().setTooltip(tooltip);
    }

    private static TextField createTextField() {
        return new CustomTextField() {
            @Override
            public void replaceText(int start, int end, String text) {
                if (validate(text)) {
                    super.replaceText(start, end, text);
                }
            }

            @Override
            public void replaceSelection(String text) {
                if (validate(text)) {
                    super.replaceSelection(text);
                }
            }

            private boolean validate(String text) {
                return text.isEmpty() || text.matches("-?[0-9]+");
            }
        };
    }

    @Override
    protected ObservableValue<Integer> getObservableValue() {
        if (value == null) {
            value = Bindings.createObjectBinding(() -> getEditor().getText().isEmpty() ? 0 : Integer.parseInt(getEditor().getText()), getEditor().textProperty());
        }
        return value;
    }

    @Override
    public void setValue(Integer value) {
        getEditor().setText(value.toString());
    }
}
