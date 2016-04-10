package etoa3_

import acmi.l2.clientmod.util.Description
import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.StringValue
import acmi.l2.clientmod.l2resources.Tex
import acmi.l2.clientmod.util.defaultio.DefaultIO
import acmi.l2.clientmod.util.defaultio.RGBA
import groovy.transform.CompileStatic
import groovyx.javafx.beans.FXBindable
import javafx.scene.paint.Color

@FXBindable
@DefaultIO
@CompileStatic
class Texture extends DefaultProperty {
    @Tex
    String file
    @Tex
    String alphaMask
    TextureCtrlType type = TextureCtrlType.Normal
    TextureLayer layer = TextureLayer.Normal
    float u
    float v
    float uSize
    float vSize
    @Description("0..255")
    int alpha
    Boolean isAnimTex
    AutoRotateType autoRotate = AutoRotateType.None
    float maskLayer
    @RGBA
    Color colorModify = Color.WHITE

    enum TextureCtrlType {
        Stretch,
        Normal,
        Tile,
        Draggable,
        Control,
        Mask
    }

    enum TextureLayer {
        None,
        Normal,
        Background
    }

    enum AutoRotateType implements StringValue {
        None,
        Camera,
        Pawn
    }

    // @formatter:off
    @Deprecated String getUnk100() { file }
    @Deprecated void setUnk100(String unk100) { this.file = unk100 }

    @Deprecated String getUnk101() { alphaMask }
    @Deprecated void setUnk101(String unk101) { this.alphaMask = unk101 }

    @Deprecated int getUnk102() { type.ordinal() }
    @Deprecated void setUnk102(int unk102) { this.type = TextureCtrlType.values()[unk102] }

    @Deprecated int getUnk103() { layer.ordinal() }
    @Deprecated void setUnk103(int unk103) { this.layer = TextureLayer.values()[unk103] }

    @Deprecated int getUnk104() { Float.floatToIntBits(u) }
    @Deprecated void setUnk104(int unk104) { this.u = Float.intBitsToFloat(unk104) }

    @Deprecated float getUnk105() { v }
    @Deprecated void setUnk105(float unk105) { this.v = unk105 }

    @Deprecated float getUnk106() { uSize }
    @Deprecated void setUnk106(float unk106) { this.uSize = unk106 }

    @Deprecated float getUnk107() { vSize }
    @Deprecated void setUnk107(float unk107) { this.vSize = unk107 }

    @Deprecated int getUnk108() { alpha }
    @Deprecated void setUnk108(int unk108) { this.alpha = unk108 }

    @Deprecated int getUnk109() { IOUtil.boolToInt(isAnimTex) }
    @Deprecated void setUnk109(int unk109) { this.isAnimTex = IOUtil.intToBool(unk109) }

    @Deprecated String getUnk110() { autoRotate.name() }
    @Deprecated void setUnk110(String unk110) { this.autoRotate = AutoRotateType.valueOf(unk110) }

    @Deprecated float getUnk111() { maskLayer }
    @Deprecated void setUnk111(float unk111) { this.maskLayer = unk111 }

    @Deprecated int getUnk112() { IOUtil.colorToRGBA(colorModify) }
    @Deprecated void setUnk112(int unk112) { this.colorModify = IOUtil.intToRGBA(unk112) }
    // @formatter:on
}
