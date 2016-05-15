package etoa4

import acmi.l2.clientmod.l2resources.Tex
import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.Type
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
class MinimapCtrl extends DefaultProperty {
    boolean showTime = true
    boolean showTown = true
    boolean showGrid = true
    boolean showMyLocMark = true
    boolean showMyLocText = true
    boolean showSSQText = true
    @Type(MiniMapCtrlIconType.class)
    List<MiniMapCtrlIconType> miniMapCtrlIconType = []

    @FXBindable
    @DefaultIO
    static class MiniMapCtrlIconType implements IOEntity {
        int typeName
        @Tex
        String normalTexName
        @Tex
        String pushedTexName
        @Tex
        String overTexName
        int width
        int height
        int offsetX
        int offsetY
        int priority

        // @formatter:off
        @Deprecated int getUnk1() { typeName }
        @Deprecated void setUnk1(int unk1) { this.typeName = unk1 }

        @Deprecated String getUnk2() { normalTexName }
        @Deprecated void setUnk2(String unk2) { this.normalTexName = unk2 }

        @Deprecated String getUnk3() { pushedTexName }
        @Deprecated void setUnk3(String unk3) { this.pushedTexName = unk3 }

        @Deprecated String getUnk4() { overTexName }
        @Deprecated void setUnk4(String unk4) { this.overTexName = unk4 }

        @Deprecated int getUnk5() { width }
        @Deprecated void setUnk5(int unk5) { this.width = unk5 }

        @Deprecated int getUnk6() { height }
        @Deprecated void setUnk6(int unk6) { this.height = unk6 }

        @Deprecated int getUnk7() { offsetX }
        @Deprecated void setUnk7(int unk7) { this.offsetX = unk7 }

        @Deprecated int getUnk8() { offsetY }
        @Deprecated void setUnk8(int unk8) { this.offsetY = unk8 }

        @Deprecated int getUnk9() { priority }
        @Deprecated void setUnk9(int unk9) { this.priority = unk9 }
        // @formatter:on
    }

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

    @Deprecated List<MiniMapCtrlIconType> getUnk106() { miniMapCtrlIconType }
    @Deprecated void setUnk106(List<MiniMapCtrlIconType> unk106) { this.miniMapCtrlIconType = unk106 }
    // @formatter:on
}
