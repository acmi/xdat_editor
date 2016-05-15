package etoa4

import acmi.l2.clientmod.l2resources.Sysstr
import acmi.l2.clientmod.l2resources.Tex
import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.Type
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
class StatusBar extends DefaultProperty {
    String title
    @Sysstr
    int titleIndex
    int barWidth
    int barHeight
    @Tex
    String foreTex
    @Tex
    String foreLeftTex
    @Tex
    String foreRightTex
    @Tex
    String backTex
    @Tex
    String backLeftTex
    @Tex
    String backRightTex
    @Tex
    String regenLeftTex
    @Tex
    String regenTex
    @Tex
    String regenRightTex
    @Tex
    String warnTex
    @Tex
    String warnLeftTex
    @Tex
    String warnRightTex
    String unit
    int drawPoint
    @Tex
    String gaugeFontTextureName
    int gaugeFontSizeX
    int gaugeFontSizeY
    @Type(ScaleMark.class)
    List<ScaleMark> unk120 = []

    @FXBindable
    @DefaultIO
    static class ScaleMark implements IOEntity {
        @Tex
        String markTexture
        int numberOfMarks
        int markWidth
        int markHeight

        // @formatter:off
        @Deprecated String getUnk1() { markTexture }
        @Deprecated void setUnk1(String unk1) { this.markTexture = unk1 }

        @Deprecated int getUnk2() { numberOfMarks }
        @Deprecated void setUnk2(int unk2) { this.numberOfMarks = unk2 }

        @Deprecated int getUnk3() { markWidth }
        @Deprecated void setUnk3(int unk3) { this.markWidth = unk3 }

        @Deprecated int getUnk4() { markHeight }
        @Deprecated void setUnk4(int unk4) { this.markHeight = unk4 }
        // @formatter:on
    }

    // @formatter:off
    @Deprecated String getUnk100() { title }
    @Deprecated void setUnk100(String unk100) { this.title = unk100 }

    @Deprecated int getUnk1001() { titleIndex }
    @Deprecated void setUnk1001(int unk1001) { this.titleIndex = unk1001 }

    @Deprecated int getUnk101() { barWidth }
    @Deprecated void setUnk101(int unk101) { this.barWidth = unk101 }

    @Deprecated int getUnk102() { barHeight }
    @Deprecated void setUnk102(int unk102) { this.barHeight = unk102 }

    @Deprecated String getUnk103() { foreTex }
    @Deprecated void setUnk103(String unk103) { this.foreTex = unk103 }

    @Deprecated String getUnk104() { foreLeftTex }
    @Deprecated void setUnk104(String unk104) { this.foreLeftTex = unk104 }

    @Deprecated String getUnk105() { foreRightTex }
    @Deprecated void setUnk105(String unk105) { this.foreRightTex = unk105 }

    @Deprecated String getUnk106() { backTex }
    @Deprecated void setUnk106(String unk106) { this.backTex = unk106 }

    @Deprecated String getUnk107() { backLeftTex }
    @Deprecated void setUnk107(String unk107) { this.backLeftTex = unk107 }

    @Deprecated String getUnk108() { backRightTex }
    @Deprecated void setUnk108(String unk108) { this.backRightTex = unk108 }

    @Deprecated String getUnk109() { regenLeftTex }
    @Deprecated void setUnk109(String unk109) { this.regenLeftTex = unk109 }

    @Deprecated String getUnk110() { regenTex }
    @Deprecated void setUnk110(String unk110) { this.regenTex = unk110 }

    @Deprecated String getUnk111() { regenRightTex }
    @Deprecated void setUnk111(String unk111) { this.regenRightTex = unk111 }

    @Deprecated String getUnk112() { warnTex }
    @Deprecated void setUnk112(String unk112) { this.warnTex = unk112 }

    @Deprecated String getUnk113() { warnLeftTex }
    @Deprecated void setUnk113(String unk113) { this.warnLeftTex = unk113 }

    @Deprecated String getUnk114() { warnRightTex }
    @Deprecated void setUnk114(String unk114) { this.warnRightTex = unk114 }

    @Deprecated String getUnk115() { unit }
    @Deprecated void setUnk115(String unk115) { this.unit = unk115 }

    @Deprecated int getUnk116() { drawPoint }
    @Deprecated void setUnk116(int unk116) { this.drawPoint = unk116 }

    @Deprecated String getUnk117() { gaugeFontTextureName }
    @Deprecated void setUnk117(String unk117) { this.gaugeFontTextureName = unk117 }

    @Deprecated int getUnk118() { gaugeFontSizeX }
    @Deprecated void setUnk118(int unk118) { this.gaugeFontSizeX = unk118 }

    @Deprecated int getUnk119() { gaugeFontSizeY }
    @Deprecated void setUnk119(int unk119) { this.gaugeFontSizeY = unk119 }
    // @formatter:on
}
