package ct26

import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
@CompileStatic
class MultiEdit extends DefaultProperty {
    int maxRow
    int showRow

    @Deprecated
    int getUnk100() { maxRow }

    @Deprecated
    void setUnk100(int unk100) { this.maxRow = unk100 }

    @Deprecated
    int getUnk101() { showRow }

    @Deprecated
    void setUnk101(int unk101) { this.showRow = unk101 }
}
