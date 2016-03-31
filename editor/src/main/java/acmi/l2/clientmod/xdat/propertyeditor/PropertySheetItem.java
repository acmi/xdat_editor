package acmi.l2.clientmod.xdat.propertyeditor;

import javafx.beans.value.ObservableValue;
import org.controlsfx.control.PropertySheet;

public abstract class PropertySheetItem implements ObservableValue<Object>, PropertySheet.Item {
    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
