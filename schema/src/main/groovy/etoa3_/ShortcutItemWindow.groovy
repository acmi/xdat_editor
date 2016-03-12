package etoa3_

import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic

@DefaultIO
@CompileStatic
class ShortcutItemWindow extends DefaultProperty {
    Boolean alwaysShowOutline = false
    Boolean useReservedShortcut = false

    @Deprecated boolean getUnk100() { alwaysShowOutline }
    @Deprecated void setUnk100(boolean unk100) { this.alwaysShowOutline = unk100 }

    @Deprecated  boolean getUnk101() { useReservedShortcut }
    @Deprecated void setUnk101(boolean unk101) { this.useReservedShortcut = unk101 }
}
