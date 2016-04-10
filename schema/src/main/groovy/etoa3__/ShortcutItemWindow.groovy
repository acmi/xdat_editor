package etoa3__

import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
@CompileStatic
class ShortcutItemWindow extends DefaultProperty {
    Boolean alwaysShowOutline = false
    Boolean useReservedShortcut = false

    // @formatter:off
    @Deprecated boolean getUnk100() { alwaysShowOutline }
    @Deprecated void setUnk100(boolean unk100) { this.alwaysShowOutline = unk100 }

    @Deprecated  boolean getUnk101() { useReservedShortcut }
    @Deprecated void setUnk101(boolean unk101) { this.useReservedShortcut = unk101 }
    // @formatter:on
}
