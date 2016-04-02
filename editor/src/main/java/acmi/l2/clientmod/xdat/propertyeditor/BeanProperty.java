package acmi.l2.clientmod.xdat.propertyeditor;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.controlsfx.property.editor.PropertyEditor;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Optional;

public class BeanProperty extends PropertySheetItem {
    private final PropertyDescriptor propertyDescriptor;
    private final String category;
    private final String description;
    private final Class<? extends PropertyEditor<?>> propertyEditorClass;
    private final Method readMethod;
    private final Method writeMethod;

    public BeanProperty(PropertyDescriptor propertyDescriptor, String category, String description, Class<? extends PropertyEditor<?>> propertyEditorClass) {
        this.propertyDescriptor = propertyDescriptor;
        this.category = category;
        this.description = description;
        this.propertyEditorClass = propertyEditorClass;
        this.readMethod = propertyDescriptor.getReadMethod();
        this.writeMethod = propertyDescriptor.getWriteMethod();
    }

    @Override
    public boolean isEditable() {
        return writeMethod != null;
    }

    @Override
    public Class<?> getType() {
        return propertyDescriptor.getPropertyType();
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getName() {
        return propertyDescriptor.getDisplayName();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Object getValue() {
        try {
            return readMethod.invoke(getObject());
        } catch (ReflectiveOperationException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void setValue(Object value) {
        if (isEditable()) {
            try {
                writeMethod.invoke(getObject(), value);
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException ignore) {
            }
        }
    }

    @Override
    public Optional<ObservableValue<?>> getObservableValue() {
        ObservableValue<?> observable = null;
        try {
            Object val = getObject().getClass()
                    .getMethod(propertyDescriptor.getName() + "Property")
                    .invoke(getObject());
            if (val != null && val instanceof ObservableValue) {
                observable = (ObservableValue) val;
            }
        } catch (ReflectiveOperationException | SecurityException | IllegalArgumentException ignore) {
        }
        return Optional.ofNullable(observable);
    }

    @Override
    public Optional<Class<? extends PropertyEditor<?>>> getPropertyEditorClass() {
        return Optional.ofNullable(propertyEditorClass);
    }

    @Override
    public void addListener(ChangeListener<? super Object> listener) {
        getObservableValue().ifPresent(observable -> observable.addListener(listener));
    }

    @Override
    public void removeListener(ChangeListener<? super Object> listener) {
        getObservableValue().ifPresent(observable -> observable.removeListener(listener));
    }

    @Override
    public void addListener(InvalidationListener listener) {
        getObservableValue().ifPresent(observable -> observable.addListener(listener));
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        getObservableValue().ifPresent(observable -> observable.removeListener(listener));
    }
}
