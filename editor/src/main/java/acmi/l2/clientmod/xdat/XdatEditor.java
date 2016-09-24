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

import acmi.l2.clientmod.util.IOEntity;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

public class XdatEditor extends Application {
    private static final Logger log = Logger.getLogger(XdatEditor.class.getName());

    private ResourceBundle interfaceResources = ResourceBundle.getBundle("acmi.l2.clientmod.xdat.interface", Locale.getDefault(), getClass().getClassLoader());

    private Stage stage;

    private Controller controller;

    private final ObjectProperty<Class<? extends IOEntity>> xdatClass = new SimpleObjectProperty<>();
    private final ObjectProperty<IOEntity> xdatObject = new SimpleObjectProperty<>();

    private String applicationVersion;

    private History history = new History();

    private final ReadOnlyBooleanWrapper working = new ReadOnlyBooleanWrapper();

    private Executor executor = Executors.newSingleThreadExecutor(r -> {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    });

    public Stage getStage() {
        return stage;
    }

    public Class<? extends IOEntity> getXdatClass() {
        return xdatClass.getValue();
    }

    public ObjectProperty<Class<? extends IOEntity>> xdatClassProperty() {
        return xdatClass;
    }

    public void setXdatClass(Class<? extends IOEntity> xdatClass) {
        this.xdatClass.setValue(xdatClass);
    }

    public IOEntity getXdatObject() {
        return xdatObject.get();
    }

    public ObjectProperty<IOEntity> xdatObjectProperty() {
        return xdatObject;
    }

    public void setXdatObject(IOEntity xdatObject) {
        this.xdatObject.set(xdatObject);
    }

    public History getHistory() {
        return history;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public ReadOnlyBooleanProperty workingProperty() {
        return working.getReadOnlyProperty();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"), interfaceResources);
        loader.setClassLoader(getClass().getClassLoader());
        loader.setControllerFactory(param -> new Controller(XdatEditor.this));
        Parent root = loader.load();
        controller = loader.getController();

        primaryStage.setTitle("XDAT Editor");
        primaryStage.setScene(new Scene(root));
        primaryStage.setWidth(Double.parseDouble(windowPrefs().get("width", String.valueOf(primaryStage.getWidth()))));
        primaryStage.setHeight(Double.parseDouble(windowPrefs().get("height", String.valueOf(primaryStage.getHeight()))));
        if (windowPrefs().getBoolean("maximized", primaryStage.isMaximized())) {
            primaryStage.setMaximized(true);
        } else {
            Rectangle2D bounds = new Rectangle2D(
                    Double.parseDouble(windowPrefs().get("x", String.valueOf(primaryStage.getX()))),
                    Double.parseDouble(windowPrefs().get("y", String.valueOf(primaryStage.getY()))),
                    primaryStage.getWidth(),
                    primaryStage.getHeight());
            if (Screen.getScreens()
                    .stream()
                    .map(Screen::getVisualBounds)
                    .anyMatch(r -> r.intersects(bounds))) {
                primaryStage.setX(bounds.getMinX());
                primaryStage.setY(bounds.getMinY());
            }
        }
        primaryStage.show();

        Platform.runLater(() -> {
            InvalidationListener listener = observable -> {
                if (primaryStage.isMaximized()) {
                    windowPrefs().putBoolean("maximized", true);
                } else {
                    windowPrefs().putBoolean("maximized", false);
                    windowPrefs().put("x", String.valueOf(Math.round(primaryStage.getX())));
                    windowPrefs().put("y", String.valueOf(Math.round(primaryStage.getY())));
                    windowPrefs().put("width", String.valueOf(Math.round(primaryStage.getWidth())));
                    windowPrefs().put("height", String.valueOf(Math.round(primaryStage.getHeight())));
                }
            };
            primaryStage.xProperty().addListener(listener);
            primaryStage.yProperty().addListener(listener);
            primaryStage.widthProperty().addListener(listener);
            primaryStage.heightProperty().addListener(listener);
        });
        Platform.runLater(this::postShow);
    }

    private void postShow() {
        applicationVersion = "unknown";

        try {
            applicationVersion = readAppVersion();
        } catch (FileNotFoundException ignore) {
        } catch (IOException | URISyntaxException e) {
            log.log(Level.WARNING, "version info load error", e);
        }

        loadSchema();
    }

    private String readAppVersion() throws IOException, URISyntaxException {
        try (JarFile jarFile = new JarFile(Paths.get(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).toFile())) {
            Manifest manifest = jarFile.getManifest();
            return manifest.getMainAttributes().getValue("Version");
        }
    }

    private void loadSchema() {
        String versionsFilePath = "/versions.csv";
        try (CSVParser parser = new CSVParser(new InputStreamReader(getClass().getResourceAsStream(versionsFilePath)), CSVFormat.DEFAULT)) {
            for (CSVRecord record : parser.getRecords()) {
                String name = record.get(0);
                String className = record.get(1);
                controller.registerVersion(name, className);
            }
        } catch (Exception e) {
            String msg = versionsFilePath + " read error";
            log.log(Level.WARNING, msg, e);
            Dialogs.showException(Alert.AlertType.WARNING, msg, e.getMessage(), e);
        }
    }

    public void execute(Callable<Void> r, Consumer<Throwable> exceptionConsumer) {
        execute(r, exceptionConsumer, null);
    }

    public void execute(Callable<Void> r, Consumer<Throwable> exceptionConsumer, Runnable finallyCallback) {
        executor.execute(() -> {
            Platform.runLater(() -> working.set(true));
            try {
                r.call();
            } catch (Throwable e) {
                if (exceptionConsumer != null)
                    exceptionConsumer.accept(e);
            } finally {
                try {
                    if (finallyCallback != null)
                        finallyCallback.run();
                } finally {
                    Platform.runLater(() -> working.set(false));
                }
            }
        });
    }

    public static Preferences getPrefs() {
        return Preferences.userRoot().node("l2clientmod").node("xdat_editor");
    }

    private static Preferences windowPrefs() {
        return getPrefs().node("window");
    }
}
