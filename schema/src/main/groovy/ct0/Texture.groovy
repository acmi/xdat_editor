package ct0

import acmi.l2.clientmod.util.Description
import acmi.l2.clientmod.util.IOUtil

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

    @Override
    Texture read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            file = input.readString()
            type = TextureCtrlType.values()[input.readInt()]
            layer = TextureLayer.values()[input.readInt()]
            u = input.readFloat()
            v = input.readFloat()
            uSize = input.readFloat()
            vSize = input.readFloat()
            alpha = input.readInt()
            isAnimTex = input.readInt()
        }

        this
    }

    @Override
    Texture write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeString(file)
            output.writeInt(type.ordinal())
            output.writeInt(layer.ordinal())
            output.writeFloat(u)
            output.writeFloat(v)
            output.writeFloat(uSize)
            output.writeFloat(vSize)
            output.writeInt(alpha)
            output.writeInt(isAnimTex)
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

    @Deprecated String getUnk100() { file }
    @Deprecated void setUnk100(String unk100) { this.file = unk100 }

    @Deprecated int getUnk101() { type.ordinal() }
    @Deprecated void setUnk101(int unk101) { this.type = TextureCtrlType.values()[unk101] }

    @Deprecated int getUnk102() { layer.ordinal() }
    @Deprecated void setUnk102(int unk102) { this.layer = TextureLayer.values()[unk102] }

    @Deprecated int getUnk103() { Float.floatToIntBits(u) }
    @Deprecated void setUnk103(int unk103) { this.u = Float.intBitsToFloat(unk103) }

    @Deprecated int getUnk104() { Float.floatToIntBits(v) }
    @Deprecated void setUnk104(int unk104) { this.v = Float.intBitsToFloat(unk104) }

    @Deprecated float getUnk105() { uSize }
    @Deprecated void setUnk105(float unk105) { this.uSize = unk105 }

    @Deprecated float getUnk106() { vSize }
    @Deprecated void setUnk106(float unk106) { this.vSize = unk106 }

    @Deprecated int getUnk107() { alpha }
    @Deprecated void setUnk107(int unk107) { this.alpha = unk107 }

    @Deprecated int getUnk108() { isAnimTex }
    @Deprecated void setUnk108(int unk108) { this.isAnimTex = unk108 }
}
