package acmi.l2.clientmod.util;

import javafx.scene.layout.Region;

public interface ComponentFactory<T extends Region> {
    T create();

    void initProperties(T component);

    default T getComponent() {
        T component = create();
        initProperties(component);
        return component;
    }
}
