package ct26

import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
@CompileStatic
class MinimapCtrl extends DefaultProperty {
    boolean showTime = true
    boolean showTown = true
    boolean showGrid = true
    boolean showMyLocMark = true
    boolean showMyLocText = true
    boolean showSSQText = true

    // @formatter:off
    @Deprecated boolean getUnk100() { showTime }
    @Deprecated void setUnk100(boolean unk100) { this.showTime = unk100 }

    @Deprecated boolean getUnk101() { showTown }
    @Deprecated void setUnk101(boolean unk101) { this.showTown = unk101 }

    @Deprecated boolean getUnk102() { showGrid }
    @Deprecated void setUnk102(boolean unk102) { this.showGrid = unk102 }

    @Deprecated boolean getUnk103() { showMyLocMark }
    @Deprecated void setUnk103(boolean unk103) { this.showMyLocMark = unk103 }

    @Deprecated boolean getUnk104() { showMyLocText }
    @Deprecated void setUnk104(boolean unk104) { this.showMyLocText = unk104 }

    @Deprecated boolean getUnk105() { showSSQText }
    @Deprecated void setUnk105(boolean unk105) { this.showSSQText = unk105 }
    // @formatter:on
}
