package ct15

import groovy.transform.CompileStatic

@CompileStatic
enum Alignment {
    NONE,
    TOP_LEFT,
    TOP_CENTER,
    TOP_RIGHT,
    CENTER_LEFT,
    CENTER,
    CENTER_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_CENTER,
    BOTTOM_RIGHT

    static Alignment valueOf(int val){
        Optional.ofNullable(values().find { it.ordinal() == val })
                .orElseThrow({ new IllegalArgumentException("No ${getClass().simpleName} constant with value=$val") })
    }
}
