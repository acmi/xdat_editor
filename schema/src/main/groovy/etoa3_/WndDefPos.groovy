package etoa3_

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic

@DefaultIO
@CompileStatic
class WndDefPos implements IOEntity {
    String wnd
    Alignment alignment = Alignment.NONE
    int x
    int y
    boolean moveParent = false
    int width
    int height

    @Override
    String toString() {
        return wnd
    }
}
