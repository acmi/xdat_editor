package etoa3__

import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
@CompileStatic
class DrawPanel extends DefaultProperty {
    int autoSize

    // @formatter:off
    @Deprecated int getUnk100() { autoSize }
    @Deprecated void setUnk100(int unk100) { this.autoSize = unk100 }
    // @formatter:on
}
