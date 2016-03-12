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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.editor.AbstractPropertyEditor;

public class BooleanPropertyEditor extends AbstractPropertyEditor<Boolean, CheckBox> {
    private ObjectProperty<Boolean> value;

    public BooleanPropertyEditor(PropertySheet.Item property) {
        super(property, createCheckBox());
    }

    private static CheckBox createCheckBox() {
        CheckBox checkBox = new CheckBox();
        checkBox.setAllowIndeterminate(true);
        return checkBox;
    }

    @Override
    protected ObservableValue<Boolean> getObservableValue() {
        if (value == null) {
            value = new SimpleObjectProperty<>();
            value.bind(Bindings.createObjectBinding(() -> getEditor().isIndeterminate() ? null : getEditor().isSelected(),
                    getEditor().indeterminateProperty(), getEditor().selectedProperty()));
        }
        return value;
    }

    @Override
    public void setValue(Boolean value) {
        if (value == null)
            getEditor().setIndeterminate(true);
        else {
            getEditor().setIndeterminate(false);
            getEditor().setSelected(value);
        }
    }
}
