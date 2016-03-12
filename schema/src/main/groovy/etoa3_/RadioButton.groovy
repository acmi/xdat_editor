package etoa3_

import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.Sysstr
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic

@DefaultIO
@CompileStatic
class RadioButton extends DefaultProperty {
    @Sysstr
    int sysstring
    int radioGroupID
    Boolean isChecked

    @Deprecated int getUnk100() { sysstring }
    @Deprecated void setUnk100(int unk100) { this.sysstring = unk100 }

    @Deprecated int getUnk101() { radioGroupID }
    @Deprecated void setUnk101(int unk101) { this.radioGroupID = unk101 }

    @Deprecated int getUnk102() { IOUtil.boolToInt(isChecked) }
    @Deprecated void setUnk102(int unk102) { this.isChecked = IOUtil.intToBool(unk102) }
}
