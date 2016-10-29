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
package acmi.l2.clientmod.xdat.scripting;

import acmi.l2.clientmod.util.IOEntity;
import acmi.l2.clientmod.xdat.Dialogs;
import acmi.l2.clientmod.xdat.XdatEditor;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.codehaus.groovy.runtime.ResourceGroovyMethods.getText;

public class Controller implements Initializable {
    private static final Logger log = Logger.getLogger(Controller.class.getName());

    @FXML
    private TextArea scriptText;
    @FXML
    private TextArea output;

    private XdatEditor editor;
    private String scriptTemplate;

    private File initialDirectory = new File(System.getProperty("user.dir"), "");
    private File scriptFile;

    public Controller(XdatEditor editor) {
        this.editor = editor;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadScriptTemplate();
    }

    private void loadScriptTemplate() {
        try {
            scriptTemplate = getText(getClass().getResource("script.template"));

            log.info("Script template loaded");
        } catch (IOException e) {
            log.log(Level.WARNING, "Couldn't load script template", e);
        }
    }

    @FXML
    private void open() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open script");

        if (initialDirectory != null)
            fileChooser.setInitialDirectory(initialDirectory);

        scriptFile = fileChooser.showOpenDialog(editor.getStage());
        if (scriptFile == null)
            return;

        initialDirectory = scriptFile.getParentFile();

        try {
            scriptText.setText(getText(scriptFile));
            output.setText("");
        } catch (IOException e) {
            String msg = "Read error";
            log.log(Level.WARNING, msg, e);
            Dialogs.showException(Alert.AlertType.WARNING, msg, e.getMessage(), e);
        }
    }

    @FXML
    private void save() {
        if (scriptFile == null)
            saveAs();

        if (scriptFile == null)
            return;

        try (FileWriter writer = new FileWriter(scriptFile)) {
            writer.write(scriptText.getText());
        } catch (IOException e) {
            String msg = "Write error";
            log.log(Level.WARNING, msg, e);
            Dialogs.showException(Alert.AlertType.WARNING, msg, e.getMessage(), e);
        }
    }

    @FXML
    private void saveAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save script");
        if (scriptFile != null)
            fileChooser.setInitialFileName(scriptFile.getName());

        if (initialDirectory != null)
            fileChooser.setInitialDirectory(initialDirectory);

        File file = fileChooser.showSaveDialog(editor.getStage());
        if (file == null)
            return;

        this.scriptFile = file;
        initialDirectory = file.getParentFile();

        save();
    }

    @FXML
    private void execute() {
        Binding binding = new Binding();
        IOEntity xdat = editor.getXdatObject();
        binding.setVariable("xdat", xdat);
        GroovyShell shell = new GroovyShell(xdat.getClass().getClassLoader(), binding);

        PrintStream out = System.out;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream scriptOut = new PrintStream(baos, false);
        System.setOut(scriptOut);

        output.setText("");
        String script = scriptTemplate
                .replace("$pckg", editor.getXdatClass().getPackage().getName())
                .replace("$text", scriptText.getText());
        editor.execute(() -> {
            shell.evaluate(script);

            return null;
        }, exception -> exception.printStackTrace(scriptOut), () -> {
            System.setOut(out);

            Platform.runLater(() -> {
                output.setText(baos.toString());

                editor.setXdatObject(null);
                editor.setXdatObject(xdat);
            });
        });
    }

    @FXML
    private void pasteHistory() {
        scriptText.setText(editor.getHistory().toString());
        output.setText("");
    }
}
