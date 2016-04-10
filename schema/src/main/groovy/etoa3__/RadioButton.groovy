package etoa3__

import acmi.l2.clientmod.l2resources.Sysstr
import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
@CompileStatic
class RadioButton extends DefaultProperty {
    @Sysstr
    int sysstring
    int radioGroupID
    Boolean isChecked

    // @formatter:off
    @Deprecated int getUnk100() { sysstring }
    @Deprecated void setUnk100(int unk100) { this.sysstring = unk100 }

    @Deprecated int getUnk101() { radioGroupID }
    @Deprecated void setUnk101(int unk101) { this.radioGroupID = unk101 }

    @Deprecated int getUnk102() { IOUtil.boolToInt(isChecked) }
    @Deprecated void setUnk102(int unk102) { this.isChecked = IOUtil.intToBool(unk102) }
    // @formatter:on
}
