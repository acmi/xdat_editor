package ct22

import acmi.l2.clientmod.util.Description
import acmi.l2.clientmod.util.IOUtil
import javafx.scene.paint.Color

class Texture extends DefaultProperty {
    String file
    TextureCtrlType type = TextureCtrlType.Stretch
    TextureLayer layer = TextureLayer.None
    float u
    float v
    float uSize
    float vSize
    @Description("0..255")
    int alpha
    int isAnimTex
    AutoRotateType autoRotate = AutoRotateType.None
    float maskLayer

    @Override
    Texture read(InputStream input) {
        super.read(input)

        use(IOUtil, Texture) {
            file = input.readString()
            type = TextureCtrlType.values()[input.readInt()]
            layer = TextureLayer.values()[input.readInt()]
            u = input.readFloat()
            v = input.readFloat()
            uSize = input.readFloat()
            vSize = input.readFloat()
            alpha = input.readInt()
            isAnimTex = input.readInt()
            autoRotate = AutoRotateType.values()[input.readInt()]
            maskLayer = input.readFloat()
        }

        this
    }

    @Override
    Texture write(OutputStream output) {
        super.write(output)

        use(IOUtil, Texture) {
            output.writeString(file)
            output.writeInt(type.ordinal())
            output.writeInt(layer.ordinal())
            output.writeFloat(u)
            output.writeFloat(v)
            output.writeFloat(uSize)
            output.writeFloat(vSize)
            output.writeInt(alpha)
            output.writeInt(isAnimTex)
            output.writeInt(autoRotate.ordinal())
            output.writeFloat(maskLayer)
        }

        this
    }

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

    enum AutoRotateType{
        None,
        Camera,
        Pawn
    }

    @Deprecated String getUnk100() { file }
    @Deprecated void setUnk100(String unk100) { this.file = unk100 }

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

    @Deprecated int getUnk109() { isAnimTex }
    @Deprecated void setUnk109(int unk109) { this.isAnimTex = unk109 }

    @Deprecated int getUnk110() { autoRotate.ordinal() }
    @Deprecated void setUnk110(int unk110) { this.autoRotate = AutoRotateType.values()[unk110] }

    @Deprecated float getUnk111() { maskLayer }
    @Deprecated void setUnk111(float unk111) { this.maskLayer = unk111 }
}
