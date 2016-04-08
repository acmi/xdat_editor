package ct1

import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic

@DefaultIO
@CompileStatic
class ShortcutItemWindow extends DefaultProperty {
    boolean alwaysShowOutline

    // @formatter:off
    @Deprecated boolean getUnk100() { alwaysShowOutline }
    @Deprecated void setUnk100(boolean unk100) { this.alwaysShowOutline = unk100 }
    // @formatter:on
}
