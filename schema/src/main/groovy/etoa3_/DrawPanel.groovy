package etoa3_

import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic

@DefaultIO
@CompileStatic
class DrawPanel extends DefaultProperty {
    int autoSize

    @Deprecated int getUnk100() { autoSize }
    @Deprecated void setUnk100(int unk100) { this.autoSize = unk100 }
}
