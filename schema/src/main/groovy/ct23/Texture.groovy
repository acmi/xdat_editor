package ct23

import acmi.l2.clientmod.util.Description
import acmi.l2.clientmod.util.IOUtil
import javafx.scene.paint.Color

class Texture extends DefaultProperty {
    String file
    String alphaMask
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
            alphaMask = input.readString()
            type = TextureCtrlType.values()[input.readInt()]
            layer = TextureLayer.values()[input.readInt()]
            u = input.readFloat()
            v = input.readFloat()
            uSize = input.readFloat()
            vSize = input.readFloat()
            alpha = input.readInt()
            isAnimTex = input.readInt()
            autoRotate = AutoRotateType.valueOf(input.readString())
            maskLayer = input.readFloat()
        }

        this
    }

    @Override
    Texture write(OutputStream output) {
        super.write(output)

        use(IOUtil, Texture) {
            output.writeString(file)
            output.writeString(alphaMask)
            output.writeInt(type.ordinal())
            output.writeInt(layer.ordinal())
            output.writeFloat(u)
            output.writeFloat(v)
            output.writeFloat(uSize)
            output.writeFloat(vSize)
            output.writeInt(alpha)
            output.writeInt(isAnimTex)
            output.writeString(autoRotate.name())
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

    enum AutoRotateType {
        None,
        Camera,
        Pawn
    }

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

    @Deprecated int getUnk109() { isAnimTex }
    @Deprecated void setUnk109(int unk109) { this.isAnimTex = unk109 }

    @Deprecated String getUnk110() { autoRotate.name() }
    @Deprecated void setUnk110(String unk110) { this.autoRotate = AutoRotateType.valueOf(unk110) }

    @Deprecated float getUnk111() { maskLayer }
    @Deprecated void setUnk111(float unk111) { this.maskLayer = unk111 }
}
