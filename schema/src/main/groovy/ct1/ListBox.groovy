package ct1

import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic

@DefaultIO
@CompileStatic
class ListBox extends DefaultProperty {
    int maxRow
    int showRow
    int showLastLine

    // @formatter:off
    @Deprecated int getUnk100() { maxRow }
    @Deprecated void setUnk100(int unk100) { this.maxRow = unk100 }

    @Deprecated int getUnk101() { showRow }
    @Deprecated void setUnk101(int unk101) { this.showRow = unk101 }

    @Deprecated int getUnk102() { showLastLine }
    @Deprecated void setUnk102(int unk102) { this.showLastLine = unk102 }
    // @formatter:on
}
