package etoa4

import acmi.l2.clientmod.l2resources.Tex
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
@CompileStatic
class Progress extends DefaultProperty {
    String unk100
    @Tex
    String backLeftTexture
    @Tex
    String backTexture
    @Tex
    String backRightTexture
    @Tex
    String barLeftTexture
    @Tex
    String barTexture
    @Tex
    String barRightTexture
    int gap
    int textureSize
    ProgressBarType type = ProgressBarType.None

    enum ProgressBarType {
        None,
        RightLeft,
        LeftRight,
        TopBottom,
        BottomTop,
    }

    // @formatter:off
    @Deprecated String getUnk101() { backLeftTexture }
    @Deprecated void setUnk101(String unk101) { this.backLeftTexture = unk101 }

    @Deprecated String getUnk102() { backTexture }
    @Deprecated void setUnk102(String unk102) { this.backTexture = unk102 }

    @Deprecated String getUnk103() { backRightTexture }
    @Deprecated void setUnk103(String unk103) { this.backRightTexture = unk103 }

    @Deprecated String getUnk104() { barLeftTexture }
    @Deprecated void setUnk104(String unk104) { this.barLeftTexture = unk104 }

    @Deprecated String getUnk105() { barTexture }
    @Deprecated void setUnk105(String unk105) { this.barTexture = unk105 }

    @Deprecated String getUnk106() { barRightTexture }
    @Deprecated void setUnk106(String unk106) { this.barRightTexture = unk106 }

    @Deprecated int getUnk107() { gap }
    @Deprecated void setUnk107(int unk107) { this.gap = unk107 }

    @Deprecated int getUnk108() { textureSize }
    @Deprecated void setUnk108(int unk108) { this.textureSize = unk108 }

    @Deprecated int getUnk109() { type.ordinal() }
    @Deprecated void setUnk109(int unk109) { this.type = ProgressBarType.values()[unk109] }
    // @formatter:on
}
