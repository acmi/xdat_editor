package ct1

import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic

@DefaultIO
@CompileStatic
class TextListBox extends DefaultProperty {
    int maxRow
    int showRow
    int lineGap
    int isShowScroll

    // @formatter:off
    @Deprecated int getUnk100() { maxRow }
    @Deprecated void setUnk100(int unk100) { this.maxRow = unk100 }

    @Deprecated int getUnk101() { showRow }
    @Deprecated void setUnk101(int unk101) { this.showRow = unk101 }

    @Deprecated int getUnk102() { lineGap }
    @Deprecated void setUnk102(int unk102) { this.lineGap = unk102 }

    @Deprecated int getUnk103() { isShowScroll }
    @Deprecated void setUnk103(int unk103) { this.isShowScroll = unk103 }
    // @formatter:on
}
