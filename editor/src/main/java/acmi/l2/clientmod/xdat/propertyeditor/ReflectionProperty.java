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

import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.editor.PropertyEditor;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class ReflectionProperty implements ObservableValue<Object>, PropertySheet.Item {
    private ObjectProperty<Object> object = new SimpleObjectProperty<>(this, "object");
    private Field field;
    private String category;
    private String description;
    private Class<? extends PropertyEditor<?>> propertyEditorClass;

    private Collection<InvalidationListener> invalidationListeners = new HashSet<>();
    private Collection<ChangeListener<Object>> changeListeners = new HashSet<>();

    private Object currentValue;

    public ReflectionProperty(Field field, String category, String description, Class<? extends PropertyEditor<?>> propertyEditorClass) {
        this.field = field;
        this.category = category;
        this.description = description;
        this.propertyEditorClass = propertyEditorClass;

        objectProperty().addListener(observable -> removeListeners());
    }

    public Object getObject() {
        return object.get();
    }

    public ObjectProperty<Object> objectProperty() {
        return object;
    }

    public void setObject(Object object) {
        this.object.set(object);
    }

    @Override
    public Object getValue() {
        try {
            return field.get(getObject());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setValue(Object o) {
        try {
            field.set(getObject(), o);

            fireValueChangedEvent();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ObservableValue<? extends Object>> getObservableValue() {
        return Optional.of(this);
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }

    @Override
    public String getName() {
        return field.getName();
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Optional<Class<? extends PropertyEditor<?>>> getPropertyEditorClass() {
        return Optional.ofNullable(propertyEditorClass);
    }

    private void fireValueChangedEvent() {
        for (InvalidationListener il : invalidationListeners) {
            try {
                il.invalidated(this);
            } catch (Exception e) {
                Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
            }
        }
        if (changeListeners.size() > 0) {
            Object oldValue = currentValue;
            currentValue = getValue();
            final boolean changed = (currentValue == null) ? (oldValue != null) : !currentValue.equals(oldValue);
            if (changed) {
                for (ChangeListener<Object> cl : changeListeners) {
                    try {
                        cl.changed(this, oldValue, currentValue);
                    } catch (Exception e) {
                        Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                    }
                }
            }
        }
    }

    @Override
    public void addListener(ChangeListener<? super Object> listener) {
        if (listener != null)
            changeListeners.add(listener);
    }

    @Override
    public void removeListener(ChangeListener<? super Object> listener) {
        changeListeners.remove(listener);
    }

    @Override
    public void addListener(InvalidationListener listener) {
        if (listener != null)
            invalidationListeners.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        invalidationListeners.remove(listener);
    }

    public void removeListeners() {
        changeListeners.clear();
        invalidationListeners.clear();
    }
}
