package acmi.l2.clientmod.xdat.propertyeditor;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.controlsfx.property.editor.PropertyEditor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FieldProperty extends PropertySheetItem {
    private final Field field;
    private final String category;
    private final String description;
    private final Class<? extends PropertyEditor<?>> propertyEditorClass;
    private final List<InvalidationListener> invalidationListeners = new ArrayList<>();
    private final List<ChangeListener<? super Object>> changeListeners = new ArrayList<>();

    public FieldProperty(Field field, String category, String description, Class<? extends PropertyEditor<?>> propertyEditorClass) {
        this.field = field;
        this.category = category;
        this.description = description;
        this.propertyEditorClass = propertyEditorClass;
    }

    @Override
    public boolean isEditable() {
        return true;
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getName() {
        return field.getName();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Object getValue() {
        try {
            return field.get(getObject());
        } catch (ReflectiveOperationException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void setValue(Object newValue) {
        if (isEditable()) {
            try {
                Object oldValue = getValue();

                field.set(getObject(), newValue);

                if (!Objects.equals(oldValue, newValue)) {
                    for (InvalidationListener listener : invalidationListeners)
                        listener.invalidated(this);
                    for (ChangeListener<? super Object> listener : changeListeners)
                        listener.changed(this, oldValue, newValue);
                }
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException ignore) {
            }
        }
    }

    @Override
    public Optional<ObservableValue<?>> getObservableValue() {
        return Optional.empty();
    }

    @Override
    public Optional<Class<? extends PropertyEditor<?>>> getPropertyEditorClass() {
        return Optional.ofNullable(propertyEditorClass);
    }

    @Override
    public void addListener(ChangeListener<? super Object> listener) {
        changeListeners.add(listener);
    }

    @Override
    public void removeListener(ChangeListener<? super Object> listener) {
        changeListeners.remove(listener);
    }

    @Override
    public void addListener(InvalidationListener listener) {
        invalidationListeners.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        invalidationListeners.remove(listener);
    }
}
