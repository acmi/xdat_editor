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
package acmi.l2.clientmod.xdat;

import acmi.l2.clientmod.util.Description;
import acmi.l2.clientmod.util.IOEntity;
import acmi.l2.clientmod.util.SubclassManager;
import acmi.l2.clientmod.util.Type;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.control.textfield.TextFields;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller implements Initializable {
    private static final Logger log = Logger.getLogger(Controller.class.getName());

    private XdatEditor editor;

    private ResourceBundle interfaceResources;

    @FXML
    private MenuItem open;
    @FXML
    private MenuItem save;
    @FXML
    private MenuItem saveAs;
    @FXML
    private Menu versionMenu;
    private ToggleGroup version = new ToggleGroup();
    @FXML
    private TabPane tabs;
    @FXML
    private ProgressBar progressBar;

    private File initialDirectory = new File(System.getProperty("user.dir"), "");
    private File xdatFile;

    private List<ChangeListener<IOEntity>> xdatListeners = new ArrayList<>();

    public Controller(XdatEditor editor) {
        this.editor = editor;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        interfaceResources = resources;

        Node scriptingTab = loadScriptTabContent();

        editor.xdatClassProperty().addListener((ob, ov, nv) -> {
            log.log(Level.INFO, String.format("XDAT class selected: %s", nv.getName()));

            tabs.getTabs().clear();

            for (Iterator<ChangeListener<IOEntity>> it = xdatListeners.iterator(); it.hasNext(); ) {
                editor.xdatObjectProperty().removeListener(it.next());
                it.remove();
            }

            editor.setXdatObject(null);

            if (scriptingTab != null) {
                Tab tab = new Tab("script console");
                tab.setContent(scriptingTab);
                tabs.getTabs().add(tab);
            }

            Arrays.stream(nv.getDeclaredFields())
                    .filter(field -> List.class.isAssignableFrom(field.getType()))
                    .forEach(field -> {
                        field.setAccessible(true);
                        tabs.getTabs().add(createTab(field));
                    });
        });
        progressBar.visibleProperty().bind(editor.workingProperty());
        open.disableProperty().bind(Bindings.isNull(editor.xdatClassProperty()));
        BooleanBinding nullXdatObject = Bindings.isNull(editor.xdatObjectProperty());
        tabs.disableProperty().bind(nullXdatObject);
        save.disableProperty().bind(nullXdatObject);
        saveAs.disableProperty().bind(nullXdatObject);
    }

    public void registerVersion(String name, String xdatClass) {
        RadioMenuItem menuItem = new RadioMenuItem(name);
        menuItem.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                editor.execute(() -> {
                    Class<? extends IOEntity> clazz = Class.forName(xdatClass).asSubclass(IOEntity.class);
                    Platform.runLater(() -> editor.setXdatClass(clazz));
                    return null;
                }, e -> {
                    log.log(Level.WARNING, String.format("%s: XDAT class load error", name), e);
                    version.getToggles().remove(menuItem);
                    versionMenu.getItems().remove(menuItem);
                    Platform.runLater(() -> Dialogs.show(Alert.AlertType.ERROR,
                            name + ": XDAT class load error",
                            null,
                            e.getClass().getSimpleName() + ": " + e.getMessage()));
                });
            }
        });
        version.getToggles().add(menuItem);
        versionMenu.getItems().add(menuItem);
    }

    private Node loadScriptTabContent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("scripting/main.fxml"));
            loader.setControllerFactory(param -> new acmi.l2.clientmod.xdat.scripting.Controller(editor));
            return wrap(loader.load());
        } catch (IOException e) {
            log.log(Level.WARNING, "Couldn't load script console", e);
        }
        return null;
    }

    private static AnchorPane wrap(Node node) {
        AnchorPane anchorPane = new AnchorPane(node);
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        return anchorPane;
    }

    private Tab createTab(Field listField) {
        Tab tab = new Tab(listField.getName());

        SplitPane pane = new SplitPane();

        TextField filter = TextFields.createClearableTextField();
        VBox.setMargin(filter, new Insets(2));
        TreeView<Object> elements = createTreeView(listField, filter.textProperty());
        VBox.setVgrow(elements, Priority.ALWAYS);
        PropertySheet properties = createPropertySheet(elements);

        pane.getItems().addAll(new VBox(filter, elements), properties);
        pane.setDividerPositions(0.3);

        tab.setContent(wrap(pane));

        return tab;
    }

    private TreeView<Object> createTreeView(Field listField, ObservableValue<String> filter) {
        TreeView<Object> elements = new TreeView<>();
        elements.setShowRoot(false);
        elements.setContextMenu(createContextMenu(elements));

        ChangeListener<IOEntity> xdatChangeListener = (observable, oldValue, newValue) -> buildTree(newValue, listField, elements, filter.getValue());
        xdatListeners.add(xdatChangeListener);
        editor.xdatObjectProperty().addListener(xdatChangeListener);

        filter.addListener(observable -> buildTree(editor.xdatObjectProperty().get(), listField, elements, filter.getValue()));

        return elements;
    }

    private void buildTree(IOEntity entity, Field listField, TreeView<Object> elements, String nameFilter) {
        elements.setRoot(null);

        if (entity == null)
            return;

        try {
            List<IOEntity> list = (List<IOEntity>) listField.get(entity);
            if (!listField.isAnnotationPresent(Type.class)) {
                log.log(Level.WARNING, String.format("XDAT.%s: @Type not defined", listField.getName()));
                Dialogs.show(Alert.AlertType.ERROR,
                        "ReflectiveOperationException",
                        null,
                        String.format("XDAT.%s: @Type not defined", listField.getName()));
            } else {
                Class<? extends IOEntity> type = listField.getAnnotation(Type.class).value().asSubclass(IOEntity.class);
                TreeItem<Object> rootItem = new TreeItem<>(new ListHolder(entity, list, listField.getName(), type));

                elements.setRoot(rootItem);

                list.stream()
                        .map(this::createTreeItem)
                        .filter(treeItem -> checkTreeNode(treeItem, nameFilter))
                        .forEach(treeItem -> rootItem.getChildren().add(treeItem));
            }
        } catch (IllegalAccessException e) {
            log.log(Level.WARNING, String.format("%s.%s is not accessible", listField.getDeclaringClass().getSimpleName(), listField.getName()), e);
            Dialogs.show(Alert.AlertType.ERROR,
                    "ReflectiveOperationException",
                    null,
                    listField.getDeclaringClass().getSimpleName() + "." + listField.getName() + " is not accessible");
        }
    }

    private boolean checkTreeNode(TreeItem<Object> treeItem, String nameFilter) {
        if (checkName(Objects.toString(treeItem.getValue()), nameFilter))
            return true;

        for (TreeItem<Object> childItem : treeItem.getChildren())
            if (checkTreeNode(childItem, nameFilter))
                return true;

        return false;
    }

    private boolean checkName(String s, String nameFilter) {
        return s.toLowerCase().contains(nameFilter.toLowerCase());
    }

    private ContextMenu createContextMenu(TreeView<Object> elements) {
        ContextMenu contextMenu = new ContextMenu();
        elements.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            contextMenu.getItems().clear();

            if (newValue == null)
                return;

            Object value = newValue.getValue();
            if (value instanceof ListHolder) {
                contextMenu.getItems().add(createAddMenu("Add ..", newValue));
            } else {
                MenuItem add = createAddMenu("Add to parent ..", newValue.getParent());

                MenuItem delete = new MenuItem("Delete");
                delete.setOnAction(event -> {
                    ListHolder parent = (ListHolder) newValue.getParent().getValue();

                    int index = parent.list.indexOf(value);
                    editor.getHistory().valueRemoved(treeItemToScriptString(newValue.getParent()), index);

                    parent.list.remove(index);
                    newValue.getParent().getChildren().remove(newValue);
                });
                contextMenu.getItems().addAll(add, delete);
            }
        });
        return contextMenu;
    }

    private MenuItem createAddMenu(String name, TreeItem<Object> newValue) {
        ListHolder listHolder = (ListHolder) newValue.getValue();

        MenuItem add = new MenuItem(name);
        add.setOnAction(event -> {
            Collection<Class> classes = SubclassManager.getInstance().getClassWithAllSubclasses(listHolder.type);
            Stream<ClassHolder> st = classes.stream()
                    .map(ClassHolder::new);
            List<ClassHolder> list = st
                    .collect(Collectors.toList());

            ChoiceDialog<ClassHolder> cd = new ChoiceDialog<>(list.get(0), list);
            cd.setTitle("Select class");
            cd.setHeaderText(null);
            cd.showAndWait()
                    .ifPresent(toCreate -> {
                        IOEntity obj = null;
                        try {
                            obj = toCreate.clazz.newInstance();

                            listHolder.list.add(obj);
                            newValue.getChildren().add(createTreeItem(obj));

                            editor.getHistory().valueCreated(treeItemToScriptString(newValue), toCreate.clazz);
                        } catch (ReflectiveOperationException e) {
                            log.log(Level.WARNING, String.format("Couldn't instantiate %s", toCreate.clazz.getName()), e);
                            Dialogs.show(Alert.AlertType.ERROR,
                                    "ReflectiveOperationException",
                                    null,
                                    "Couldn't instantiate " + toCreate.clazz);
                        }
                    });
        });

        return add;
    }

    private TreeItem<Object> createTreeItem(IOEntity o) {
        TreeItem<Object> item = new TreeItem<>(o);

        List<Field> listFields = new ArrayList<>();
        Class<?> clazz = o.getClass();
        while (clazz != Object.class) {
            Arrays.stream(clazz.getDeclaredFields())
                    .filter(field -> List.class.isAssignableFrom(field.getType()))
                    .filter(field -> !field.isSynthetic())
                    .forEach(listFields::add);
            clazz = clazz.getSuperclass();
        }
        listFields.forEach(field -> {
            field.setAccessible(true);
            try {
                List<IOEntity> list = (List<IOEntity>) field.get(o);
                if (!field.isAnnotationPresent(Type.class)) {
                    log.log(Level.WARNING, String.format("%s.%s: @Type not defined", o.getClass().getName(), field.getName()));
                    Dialogs.show(Alert.AlertType.ERROR,
                            "ReflectiveOperationException",
                            null,
                            String.format("%s.%s: @Type not defined", o.getClass().getName(), field.getName()));
                } else {
                    Class<? extends IOEntity> type = field.getAnnotation(Type.class).value().asSubclass(IOEntity.class);
                    TreeItem<Object> listItem = new TreeItem<>(new ListHolder(o, list, field.getName(), type));

                    item.getChildren().add(listItem);

                    list.forEach(e -> listItem.getChildren().add(createTreeItem(e)));
                }
            } catch (IllegalAccessException e) {
                log.log(Level.WARNING, String.format("%s.%s is not accessible", o.getClass(), field.getName()), e);
                Dialogs.show(Alert.AlertType.ERROR,
                        "ReflectiveOperationException",
                        null,
                        String.format("%s.%s is not accessible", o.getClass(), field.getName()));
            }
        });
        return item;
    }

    private PropertySheet createPropertySheet(TreeView<Object> elements) {
        PropertySheet properties = new PropertySheet();
        properties.setSkin(new PropertySheetSkin(properties));

        elements.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            properties.getItems().clear();

            if (newValue == null)
                return;

            Object obj = newValue.getValue();

            if (obj instanceof ListHolder)
                return;

            Class<?> objClass = obj.getClass();
            while (objClass != Object.class) {
                for (Field field : objClass.getDeclaredFields()) {
                    if (field.isSynthetic())
                        continue;

                    String description = "";
                    if (field.isAnnotationPresent(Description.class))
                        description = field.getAnnotation(Description.class).value();
                    field.setAccessible(true);
                    ReflectionProperty property = new ReflectionProperty(obj, field, objClass.getSimpleName(), description);
                    property.addListener((observable1, oldValue1, newValue1) -> editor.getHistory().valueChanged(treeItemToScriptString(newValue), field.getName(), newValue1));
                    properties.getItems().add(property);
                }

                objClass = objClass.getSuperclass();
            }
        });

        return properties;
    }

    private String treeItemToScriptString(TreeItem item) {
        List<TreeItem> list = new ArrayList<>();
        do {
            list.add(item);
        } while ((item = item.getParent()) != null);
        Collections.reverse(list);

        StringBuilder sb = new StringBuilder("xdat");
        for (int i = 0; i < list.size(); i++) {
            Object value = list.get(i).getValue();
            if (value instanceof ListHolder) {
                ListHolder holder = (ListHolder) list.get(i).getValue();
                sb.append('.').append(holder.name);
                if (i + 1 < list.size()) {
                    sb.append('[')
                            .append(holder.list.indexOf(list.get(++i).getValue()))
                            .append(']');
                }
            }
        }
        return sb.toString();
    }

    @FXML
    private void open() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open interface.xdat");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XDAT (*.xdat)", "*.xdat"),
                new FileChooser.ExtensionFilter("All files", "*.*"));

        if (initialDirectory != null)
            fileChooser.setInitialDirectory(initialDirectory);

        xdatFile = fileChooser.showOpenDialog(editor.getStage());
        if (xdatFile == null)
            return;

        initialDirectory = xdatFile.getParentFile();

        try {
            IOEntity xdat = editor.getXdatClass().getConstructor().newInstance();

            editor.execute(() -> {
                try (InputStream is = new BufferedInputStream(new FileInputStream(xdatFile))) {
                    xdat.read(is);

                    Platform.runLater(() -> editor.setXdatObject(xdat));
                }
                return null;
            }, e -> {
                log.log(Level.WARNING, "Read error", e);
                Platform.runLater(() -> Dialogs.show(Alert.AlertType.ERROR,
                        "Read error",
                        null,
                        "Try to choose another version"));
            });
        } catch (ReflectiveOperationException e) {
            log.log(Level.WARNING, "XDAT class should have empty public constructor", e);
            Dialogs.show(Alert.AlertType.ERROR,
                    "ReflectiveOperationException",
                    null,
                    "XDAT class should have empty public constructor");
        }
    }

    @FXML
    private void save() {
        if (xdatFile == null)
            return;

        editor.execute(() -> {
            try (OutputStream os = new BufferedOutputStream(new FileOutputStream(xdatFile))) {
                editor.getXdatObject().write(os);
            }
            return null;
        }, e -> {
            log.log(Level.WARNING, "Write error", e);
            Platform.runLater(() -> Dialogs.show(Alert.AlertType.ERROR,
                    e.getClass().getSimpleName(),
                    null,
                    e.getMessage()));
        });
    }

    @FXML
    private void saveAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XDAT (*.xdat)", "*.xdat"),
                new FileChooser.ExtensionFilter("All files", "*.*"));
        fileChooser.setInitialFileName(xdatFile.getName());

        if (initialDirectory != null)
            fileChooser.setInitialDirectory(initialDirectory);

        File file = fileChooser.showSaveDialog(editor.getStage());
        if (file == null)
            return;

        this.xdatFile = file;
        initialDirectory = file.getParentFile();

        save();
    }

    @FXML
    private void exit() {
        Platform.exit();
    }

    @FXML
    private void about() {
        Dialogs.show(Alert.AlertType.INFORMATION,
                "About",
                null,
                new Label("XDAT Editor\n" +
                        "Version: " + editor.getApplicationVersion()));
    }

    private static class ListHolder {
        IOEntity entity;
        List<IOEntity> list;
        String name;
        Class<? extends IOEntity> type;

        ListHolder(IOEntity entity, List<IOEntity> list, String name, Class<? extends IOEntity> type) {
            this.entity = entity;
            this.list = list;
            this.name = name;
            this.type = type;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private static class ClassHolder {
        Class<? extends IOEntity> clazz;

        private ClassHolder(Class<? extends IOEntity> clazz) {
            this.clazz = clazz;
        }

        @Override
        public String toString() {
            return clazz.getSimpleName();
        }
    }
}
