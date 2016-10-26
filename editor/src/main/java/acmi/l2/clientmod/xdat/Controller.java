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

import acmi.l2.clientmod.crypt.L2Crypt;
import acmi.l2.clientmod.l2resources.L2Context;
import acmi.l2.clientmod.l2resources.L2Resources;
import acmi.l2.clientmod.l2resources.Sysstr;
import acmi.l2.clientmod.l2resources.Tex;
import acmi.l2.clientmod.unreal.Environment;
import acmi.l2.clientmod.util.*;
import acmi.l2.clientmod.xdat.propertyeditor.*;
import groovy.lang.GroovyClassLoader;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.io.input.CountingInputStream;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.control.textfield.TextFields;
import org.controlsfx.property.editor.PropertyEditor;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller implements Initializable {
    private static final Logger log = Logger.getLogger(Controller.class.getName());

    private static final Map<String, String> UI_NODE_ICONS = new HashMap<>();
    private static final String UI_NODE_ICON_DEFAULT = "MissingIcon.png";

    static {
        UI_NODE_ICONS.put("BarCtrl", "ToggleButton.png");
        UI_NODE_ICONS.put("Button", "Button.png");
        UI_NODE_ICONS.put("CharacterViewportWindow", "MissingIcon.png");
        UI_NODE_ICONS.put("ChatWindow", "MissingIcon.png");
        UI_NODE_ICONS.put("CheckBox", "CheckBox.png");
        UI_NODE_ICONS.put("ComboBox", "ComboBox.png");
        UI_NODE_ICONS.put("DrawPanel", "Canvas.png");
        UI_NODE_ICONS.put("EditBox", "TextField.png");
        UI_NODE_ICONS.put("EffectButton", "Button.png");
        UI_NODE_ICONS.put("FishViewportWindow", "MissingIcon.png");
        UI_NODE_ICONS.put("FlashCtrl", "MissingIcon.png");
        UI_NODE_ICONS.put("HtmlCtrl", "HTMLEditor.png");
        UI_NODE_ICONS.put("InvenWeight", "ToggleButton.png");
        UI_NODE_ICONS.put("ItemWindow", "GridPane.png");
        UI_NODE_ICONS.put("ListBox", "MissingIcon.png");
        UI_NODE_ICONS.put("ListCtrl", "ListView.png");
        UI_NODE_ICONS.put("MinimapCtrl", "MissingIcon.png");
        UI_NODE_ICONS.put("MultiEdit", "MissingIcon.png");
        UI_NODE_ICONS.put("MultiSellItemInfo", "MissingIcon.png");
        UI_NODE_ICONS.put("MultiSellNeededItem", "MissingIcon.png");
        UI_NODE_ICONS.put("NameCtrl", "MissingIcon.png");
        UI_NODE_ICONS.put("Progress", "ProgressBar.png");
        UI_NODE_ICONS.put("PropertyController", "MissingIcon.png");
        UI_NODE_ICONS.put("Radar", "MissingIcon.png");
        UI_NODE_ICONS.put("RadarMapCtrl", "MissingIcon.png");
        UI_NODE_ICONS.put("RadioButton", "RadioButton.png");
        UI_NODE_ICONS.put("ScrollArea", "ScrollPane.png");
        UI_NODE_ICONS.put("ShortcutItemWindow", "MissingIcon.png");
        UI_NODE_ICONS.put("SliderCtrl", "Slider-h.png");
        UI_NODE_ICONS.put("StatusBar", "ToggleButton.png");
        UI_NODE_ICONS.put("StatusIconCtrl", "MissingIcon.png");
        UI_NODE_ICONS.put("Tab", "TabPane.png");
        UI_NODE_ICONS.put("TextBox", "Label.png");
        UI_NODE_ICONS.put("TextListBox", "Label.png");
        UI_NODE_ICONS.put("Texture", "Graphic.png");
        UI_NODE_ICONS.put("TreeCtrl", "TreeView.png");
        UI_NODE_ICONS.put("WebBrowserCtrl", "WebView.png");
        UI_NODE_ICONS.put("Window", "TitledPane.png");
    }

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

    private ObjectProperty<File> initialDirectory = new SimpleObjectProperty<>(this, "initialDirectory", new File(XdatEditor.getPrefs().get("initialDirectory", System.getProperty("user.dir"))));
    private ObjectProperty<File> xdatFile = new SimpleObjectProperty<>(this, "xdatFile");
    private ObjectProperty<Environment> environment = new SimpleObjectProperty<>(this, "environment");
    private ObjectProperty<L2Resources> l2resources = new SimpleObjectProperty<>(this, "l2resources");

    private List<InvalidationListener> xdatListeners = new ArrayList<>();

    public Controller(XdatEditor editor) {
        this.editor = editor;

        environment.bind(Bindings.createObjectBinding(() -> {
            if (xdatFile.getValue() == null)
                return null;

            return Environment.fromIni(new File(xdatFile.getValue().getParentFile(), "L2.ini"));
        }, xdatFile));
        l2resources.bind(Bindings.createObjectBinding(() -> {
            if (environment.getValue() == null)
                return null;

            return new L2Resources(environment.get());
        }, environment));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        interfaceResources = resources;

        Node scriptingTab = loadScriptTabContent();

        initialDirectory.addListener((observable, oldVal, newVal) -> {
            if (newVal != null)
                XdatEditor.getPrefs().put("initialDirectory", newVal.getPath());
        });
        editor.xdatClassProperty().addListener((ob, ov, nv) -> {
            log.log(Level.INFO, String.format("XDAT class selected: %s", nv.getName()));

            tabs.getTabs().clear();

            for (Iterator<InvalidationListener> it = xdatListeners.iterator(); it.hasNext(); ) {
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

        xdatFile.addListener((observable, oldValue, newValue) -> {
            if (newValue == null)
                return;

            Collection<File> files = FileUtils.listFiles(newValue.getParentFile(), new WildcardFileFilter("SysString-*.dat"), null);
            if (!files.isEmpty()) {
                File file = files.iterator().next();
                log.info("sysstring file: " + file);
                try (InputStream is = L2Crypt.decrypt(new FileInputStream(file), file.getName())) {
                    SysstringPropertyEditor.strings.clear();
                    int count = IOUtil.readInt(is);
                    for (int i = 0; i < count; i++) {
                        SysstringPropertyEditor.strings.put(IOUtil.readInt(is), IOUtil.readString(is));
                    }
                } catch (Exception ignore) {
                }
            }

            File file = new File(newValue.getParentFile(), "L2.ini");
            try {
                TexturePropertyEditor.environment = Environment.fromIni(file);
                TexturePropertyEditor.environment.getPaths().forEach(s -> log.info("environment path: " + s));
            } catch (Exception ignore) {
            }
        });
    }

    public void registerVersion(String name, String xdatClass) {
        RadioMenuItem menuItem = new RadioMenuItem(name);
        menuItem.setMnemonicParsing(false);
        menuItem.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                editor.execute(() -> {
                    Class<? extends IOEntity> clazz = Class.forName(xdatClass, true, new GroovyClassLoader(getClass().getClassLoader())).asSubclass(IOEntity.class);
                    Platform.runLater(() -> editor.setXdatClass(clazz));
                    return null;
                }, e -> {
                    String msg = String.format("%s: XDAT class load error", name);
                    log.log(Level.WARNING, msg, e);
                    Platform.runLater(() -> {
                        version.getToggles().remove(menuItem);
                        versionMenu.getItems().remove(menuItem);

                        Dialogs.showException(Alert.AlertType.ERROR, msg, e.getMessage(), e);
                    });
                });
            }
        });
        version.getToggles().add(menuItem);
        versionMenu.getItems().add(menuItem);
    }

    private Node loadScriptTabContent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("scripting/main.fxml"));
            loader.setClassLoader(getClass().getClassLoader());
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
        elements.setCellFactory(param -> new TreeCell<Object>() {
            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.toString());
                    if (UIEntity.class.isAssignableFrom(item.getClass())) {
                        try (InputStream is = getClass().getResourceAsStream("/acmi/l2/clientmod/xdat/nodeicons/" + UI_NODE_ICONS.getOrDefault(item.getClass().getSimpleName(), UI_NODE_ICON_DEFAULT))) {
                            setGraphic(new ImageView(new Image(is)));
                        } catch (IOException ignore) {}
                    }
                }
            }
        });
        elements.setShowRoot(false);
        elements.setContextMenu(createContextMenu(elements));

        InvalidationListener treeInvalidation = (observable) -> buildTree(editor.xdatObjectProperty().get(), listField, elements, filter.getValue());
        editor.xdatObjectProperty().addListener(treeInvalidation);
        xdatListeners.add(treeInvalidation);

        filter.addListener(treeInvalidation);

        return elements;
    }

    private static void buildTree(IOEntity entity, Field listField, TreeView<Object> elements, String nameFilter) {
        elements.setRoot(null);

        if (entity == null)
            return;

        try {
            @SuppressWarnings("unchecked")
            List<IOEntity> list = (List<IOEntity>) listField.get(entity);
            if (!listField.isAnnotationPresent(Type.class)) {
                String msg = String.format("XDAT.%s: @Type not defined", listField.getName());
                log.log(Level.WARNING, msg);
                Dialogs.showException(Alert.AlertType.ERROR, "ReflectiveOperationException", msg, null);
            } else {
                Class<? extends IOEntity> type = listField.getAnnotation(Type.class).value().asSubclass(IOEntity.class);
                TreeItem<Object> rootItem = new TreeItem<>(new ListHolder(entity, list, listField.getName(), type));

                elements.setRoot(rootItem);

                rootItem.getChildren().addAll(
                        list.stream()
                                .map(Controller::createTreeItem)
                                .filter(treeItem -> checkTreeNode(treeItem, nameFilter))
                                .collect(Collectors.toList()));
            }
        } catch (IllegalAccessException e) {
            String msg = String.format("%s.%s is not accessible", listField.getDeclaringClass().getSimpleName(), listField.getName());
            log.log(Level.WARNING, msg, e);
            Dialogs.showException(Alert.AlertType.ERROR, "ReflectiveOperationException", msg, e);
        }
    }

    private static boolean checkTreeNode(TreeItem<Object> treeItem, String nameFilter) {
        if (checkName(Objects.toString(treeItem.getValue()), nameFilter))
            return true;

        for (TreeItem<Object> childItem : treeItem.getChildren())
            if (checkTreeNode(childItem, nameFilter))
                return true;

        return false;
    }

    private static boolean checkName(String s, String nameFilter) {
        return s.toLowerCase().contains(nameFilter.toLowerCase());
    }

    private ContextMenu createContextMenu(TreeView<Object> elements) {
        ContextMenu contextMenu = new ContextMenu();
        InvalidationListener il = observable1 -> updateContextMenu(contextMenu, elements);
        elements.rootProperty().addListener(il);
        elements.getSelectionModel().selectedItemProperty().addListener(il);
        return contextMenu;
    }

    private void updateContextMenu(ContextMenu contextMenu, TreeView<Object> elements) {
        contextMenu.getItems().clear();

        TreeItem<Object> root = elements.getRoot();
        TreeItem<Object> selected = elements.getSelectionModel().getSelectedItem();

        if (selected == null) {
            if (root != null)
                contextMenu.getItems().add(createAddMenu("Add ..", elements, root));
        } else {
            Object value = selected.getValue();
            if (value instanceof ListHolder) {
                contextMenu.getItems().add(createAddMenu("Add ..", elements, selected));
            } else if (selected.getParent() != null && selected.getParent().getValue() instanceof ListHolder) {
                MenuItem add = createAddMenu("Add to parent ..", elements, selected.getParent());

                MenuItem delete = new MenuItem("Delete");
                delete.setOnAction(event -> {
                    ListHolder parent = (ListHolder) selected.getParent().getValue();

                    @SuppressWarnings("SuspiciousMethodCalls")
                    int index = parent.list.indexOf(value);
                    if (value instanceof Named) {
                        editor.getHistory().valueRemoved(treeItemToScriptString(selected.getParent()), ((Named) value).getName());
                    } else {
                        editor.getHistory().valueRemoved(treeItemToScriptString(selected.getParent()), index);
                    }

                    parent.list.remove(index);
                    selected.getParent().getChildren().remove(selected);

                    elements.getSelectionModel().selectPrevious();
                    elements.getSelectionModel().selectNext();
                });
                contextMenu.getItems().addAll(add, delete);
            }
            if (value instanceof ComponentFactory) {
                MenuItem view = new MenuItem("View");
                view.setOnAction(event -> {
                    if (value instanceof L2Context)
                        ((L2Context) value).setResources(l2resources.getValue());
                    Stage stage = new Stage();
                    stage.setTitle(value.toString());
                    Scene scene = new Scene(((ComponentFactory) value).getComponent());
                    scene.getStylesheets().add(getClass().getResource("l2.css").toExternalForm());
                    stage.setScene(scene);
                    stage.show();
                });
                contextMenu.getItems().add(view);
            }
        }
    }

    private MenuItem createAddMenu(String name, TreeView<Object> elements, TreeItem<Object> selected) {
        ListHolder listHolder = (ListHolder) selected.getValue();

        MenuItem add = new MenuItem(name);
        add.setOnAction(event -> {
            Stream<ClassHolder> st = SubclassManager.getInstance()
                    .getClassWithAllSubclasses(listHolder.type)
                    .stream()
                    .map(ClassHolder::new);
            List<ClassHolder> list = st
                    .collect(Collectors.toList());

            Optional<ClassHolder> choice;

            if (list.size() == 1) {
                choice = Optional.of(list.get(0));
            } else {
                ChoiceDialog<ClassHolder> cd = new ChoiceDialog<>(list.get(0), list);
                cd.setTitle("Select class");
                cd.setHeaderText(null);
                choice = cd.showAndWait();
            }
            choice.ifPresent(toCreate -> {
                try {
                    IOEntity obj = toCreate.clazz.newInstance();

                    listHolder.list.add(obj);
                    TreeItem<Object> treeItem = createTreeItem(obj);
                    selected.getChildren().add(treeItem);
                    elements.getSelectionModel().select(treeItem);
                    elements.scrollTo(elements.getSelectionModel().getSelectedIndex());

                    editor.getHistory().valueCreated(treeItemToScriptString(selected), toCreate.clazz);
                } catch (ReflectiveOperationException e) {
                    String msg = String.format("Couldn't instantiate %s", toCreate.clazz.getName());
                    log.log(Level.WARNING, msg, e);
                    Dialogs.showException(Alert.AlertType.ERROR, "ReflectiveOperationException", msg, e);
                }
            });
        });

        return add;
    }

    private static TreeItem<Object> createTreeItem(IOEntity o) {
        TreeItem<Object> item = new TreeItem<>(o);

        List<Field> fields = new ArrayList<>();
        Class<?> clazz = o.getClass();
        while (clazz != Object.class) {
            Arrays.stream(clazz.getDeclaredFields())
                    .filter(field -> !field.isSynthetic())
                    .filter(field -> List.class.isAssignableFrom(field.getType()) ||
                            IOEntity.class.isAssignableFrom(field.getType()))
                    .forEach(fields::add);
            clazz = clazz.getSuperclass();
        }
        fields.forEach(field -> {
            field.setAccessible(true);

            Optional<Object> obj = Optional.empty();
            try {
                obj = Optional.ofNullable(field.get(o));
            } catch (IllegalAccessException e) {
                String msg = String.format("%s.%s is not accessible", o.getClass(), field.getName());
                log.log(Level.WARNING, msg, e);
                Dialogs.showException(Alert.AlertType.ERROR, "ReflectiveOperationException", msg, e);
            }

            obj.ifPresent(val -> {
                if (List.class.isAssignableFrom(field.getType())) {
                    if (!field.isAnnotationPresent(Type.class)) {
                        String msg = String.format("%s.%s: @Type not defined", o.getClass().getName(), field.getName());
                        log.log(Level.WARNING, msg);
                        Dialogs.showException(Alert.AlertType.ERROR, "ReflectiveOperationException", msg, null);
                    } else {
                        @SuppressWarnings("unchecked")
                        List<IOEntity> list = (List<IOEntity>) val;
                        Class<? extends IOEntity> type = field.getAnnotation(Type.class).value().asSubclass(IOEntity.class);
                        TreeItem<Object> listItem = new TreeItem<>(new ListHolder(o, list, field.getName(), type));

                        item.getChildren().add(listItem);

                        listItem.getChildren().addAll(list.stream()
                                .map(Controller::createTreeItem)
                                .collect(Collectors.toList()));
                    }
                } else if (IOEntity.class.isAssignableFrom(field.getType())) {
                    IOEntity ioEntity = (IOEntity) val;

                    item.getChildren().add(createTreeItem(ioEntity));
                }
            });
        });
        return item;
    }

    private static Map<Class, List<PropertySheetItem>> map = new HashMap<>();

    private PropertySheet createPropertySheet(TreeView<Object> elements) {
        PropertySheet properties = new PropertySheet();
        properties.setSkin(new PropertySheetSkin(properties));

        elements.getSelectionModel().selectedItemProperty().addListener((selected, oldValue, newSelection) -> {
            properties.getItems().clear();

            if (newSelection == null)
                return;

            Object obj = newSelection.getValue();

            if (obj instanceof ListHolder)
                return;

            if (!map.containsKey(obj.getClass())) {
                map.put(obj.getClass(), loadProperties(obj));
            }
            List<PropertySheetItem> props = map.get(obj.getClass());
            props.forEach(property -> {
                property.setObject(obj);
                ChangeListener<Object> addToHistory = (observable1, oldValue1, newValue1) -> {
                    String objName = treeItemToScriptString(newSelection);
                    String propName = ((PropertySheetItem)observable1).getName();
                    if ("name".equals(propName) || "wnd".equals(propName)){
                        StringBuilder b = new StringBuilder(objName);
                        String nv = String.valueOf(newValue1);
                        int ind = objName.lastIndexOf(nv);
                        b.replace(ind, ind + nv.length(), String.valueOf(oldValue1) );
                        objName = b.toString();
                    }
                    editor.getHistory().valueChanged(objName, property.getName(), newValue1, property.hashCode());
                };
                property.addListener(addToHistory);

                selected.addListener(new InvalidationListener() {
                    @Override
                    public void invalidated(Observable observable) {
                        property.removeListener(addToHistory);
                        observable.removeListener(this);
                    }
                });
            });
            properties.getItems().setAll(props);
        });

        return properties;
    }

    private static List<PropertySheetItem> loadProperties(Object obj) {
        Class<?> objClass = obj.getClass();
        List<PropertySheetItem> list = new ArrayList<>();
        while (objClass != Object.class) {
            for (Field field : objClass.getDeclaredFields()) {
                if (field.isSynthetic() || Modifier.isStatic(field.getModifiers()))
                    continue;

                if (Collection.class.isAssignableFrom(field.getType()))
                    continue;

                if (field.isAnnotationPresent(Hide.class))
                    continue;

                String description = "";
                if (field.isAnnotationPresent(Description.class))
                    description = field.getAnnotation(Description.class).value();
                Class<? extends PropertyEditor<?>> propertyEditorClass = null;
                if (field.getType() == Boolean.class ||
                        field.getType() == Boolean.TYPE) {
                    propertyEditorClass = BooleanPropertyEditor.class;
                } else if (field.isAnnotationPresent(Tex.class)) {
                    propertyEditorClass = TexturePropertyEditor.class;
                } else if (field.isAnnotationPresent(Sysstr.class)) {
                    propertyEditorClass = SysstringPropertyEditor.class;
                }
                field.setAccessible(true);
                PropertySheetItem property = new FieldProperty(field, objClass.getSimpleName(), description, propertyEditorClass);
                list.add(property);
            }
            objClass = objClass.getSuperclass();
        }
        return list;
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
                    sb.append('[');
                    Object obj = list.get(++i).getValue();
                    if (obj instanceof Named) {
                        sb.append('"').append(((Named) obj).getName()).append('"');
                    } else {
                        //noinspection SuspiciousMethodCalls
                        sb.append(holder.list.indexOf(obj));
                    }
                    sb.append(']');
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

        if (initialDirectory.getValue() != null &&
                initialDirectory.getValue().exists() &&
                initialDirectory.getValue().isDirectory())
            fileChooser.setInitialDirectory(initialDirectory.getValue());

        File selected = fileChooser.showOpenDialog(editor.getStage());
        if (selected == null)
            return;

        xdatFile.setValue(selected);
        initialDirectory.setValue(selected.getParentFile());

        try (DataInputStream dis = new DataInputStream(new FileInputStream(selected))) {
            int i = Integer.reverseBytes(dis.readInt());

            if (i < 0 || i > 0xFFFF) {
                throw new IOException("File seems to be encrypted.");
            }
        } catch (IOException e) {
            Dialogs.showException(Alert.AlertType.ERROR, "Read error", e.getMessage(), e);
            return;
        }

        try {
            IOEntity xdat = editor.getXdatClass().getConstructor().newInstance();

            editor.execute(() -> {
                CountingInputStream cis = new CountingInputStream(new BufferedInputStream(new FileInputStream(selected)));
                try (InputStream is = cis) {
                    xdat.read(is);

                    Platform.runLater(() -> editor.setXdatObject(xdat));
                } catch (Throwable e) {
                    String msg = String.format("Read error before offset 0x%x", cis.getCount());
                    log.log(Level.WARNING, msg, e);
                    throw new IOException(msg, e);
                }
                return null;
            }, e -> Dialogs.showException(Alert.AlertType.ERROR, "Read error", "Try to choose another version", e));
        } catch (ReflectiveOperationException e) {
            String msg = "XDAT class should have empty public constructor";
            log.log(Level.WARNING, msg, e);
            Dialogs.showException(Alert.AlertType.ERROR, "ReflectiveOperationException", msg, e);
        }
    }

    @FXML
    private void save() {
        if (xdatFile.getValue() == null)
            return;

        editor.execute(() -> {
            try (OutputStream os = new BufferedOutputStream(new FileOutputStream(xdatFile.getValue()))) {
                editor.getXdatObject().write(os);
            }
            return null;
        }, e -> {
            String msg = "Write error";
            log.log(Level.WARNING, msg, e);
            Dialogs.showException(Alert.AlertType.ERROR, msg, e.getMessage(), e);
        });
    }

    @FXML
    private void saveAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XDAT (*.xdat)", "*.xdat"),
                new FileChooser.ExtensionFilter("All files", "*.*"));
        fileChooser.setInitialFileName(xdatFile.getValue().getName());

        if (initialDirectory.getValue() != null &&
                initialDirectory.getValue().exists() &&
                initialDirectory.getValue().isDirectory())
            fileChooser.setInitialDirectory(initialDirectory.getValue());

        File file = fileChooser.showSaveDialog(editor.getStage());
        if (file == null)
            return;

        this.xdatFile.setValue(file);
        initialDirectory.setValue(file.getParentFile());

        save();
    }

    @FXML
    private void exit() {
        Platform.exit();
    }

    @FXML
    private void about() {
        Dialog dialog = new Dialog();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle(interfaceResources.getString("about"));

        Label name = new Label("XDAT Editor");
        Label version = new Label("Version: " + editor.getApplicationVersion());
        Label jre = new Label("JRE: " + System.getProperty("java.version"));
        Label jvm = new Label("JVM: " + System.getProperty("java.vm.name") + " by " + System.getProperty("java.vendor"));
        Hyperlink link = new Hyperlink("GitHub");
        link.setOnAction(event -> editor.getHostServices().showDocument("https://github.com/acmi/xdat_editor"));

        Label license = new Label(interfaceResources.getString("help.open_source_licenses"));
        Hyperlink licenseApache = new Hyperlink("Apache Commons IO, Apache Commons CSV,\nApache Commons Lang, Groovy");
        licenseApache.setOnAction(event -> editor.getHostServices().showDocument("http://www.apache.org/licenses/LICENSE-2.0.txt"));
        Hyperlink licenseControlsFX = new Hyperlink("ControlsFX");
        licenseControlsFX.setOnAction(event -> editor.getHostServices().showDocument("https://bitbucket.org/controlsfx/controlsfx/raw/15b3171c215f00de751a37d14f6b678d6896f8a2/license.txt"));

        VBox content = new VBox(name, version, jre, jvm, link, license, licenseApache, licenseControlsFX);
        VBox.setMargin(jre, new Insets(10, 0, 0, 0));
        VBox.setMargin(link, new Insets(10, 0, 0, 0));
        VBox.setMargin(license, new Insets(15, 0, 0, 0));

        DialogPane pane = new DialogPane();
        pane.setContent(content);
        pane.getButtonTypes().addAll(ButtonType.OK);
        dialog.setDialogPane(pane);

        dialog.showAndWait();
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
