package ct1

import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic

@DefaultIO
@CompileStatic
class RadioButton extends DefaultProperty {
    int sysstring
    int radioGroupID
    int isChecked

    // @formatter:off
    @Deprecated int getUnk100() { sysstring }
    @Deprecated void setUnk100(int unk100) { this.sysstring = unk100 }

    @Deprecated int getUnk101() { radioGroupID }
    @Deprecated void setUnk101(int unk101) { this.radioGroupID = unk101 }

    @Deprecated int getUnk102() { isChecked }
    @Deprecated void setUnk102(int unk102) { this.isChecked = unk102 }
    // @formatter:on
}
