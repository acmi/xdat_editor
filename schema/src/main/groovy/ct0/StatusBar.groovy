package ct0

import acmi.l2.clientmod.util.IOUtil

class StatusBar extends DefaultProperty {
    String title
    int textureWidth
    int textureHeight
    String foreTexture
    String backTexture
    String warnTexture
    String regenTexture

    @Override
    StatusBar read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            title = input.readString()
            textureWidth = input.readInt()
            textureHeight = input.readInt()
            foreTexture = input.readString()
            backTexture = input.readString()
            warnTexture = input.readString()
            regenTexture = input.readString()
        }

        this
    }

    @Override
    StatusBar write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeString(title)
            output.writeInt(textureWidth)
            output.writeInt(textureHeight)
            output.writeString(foreTexture)
            output.writeString(backTexture)
            output.writeString(warnTexture)
            output.writeString(regenTexture)
        }

        this
    }

    @Deprecated String getUnk100() { title }
    @Deprecated void setUnk100(String unk100) { this.title = unk100 }

    @Deprecated int getUnk101() { textureWidth }
    @Deprecated void setUnk101(int unk101) { this.textureWidth = unk101 }

    @Deprecated int getUnk102() { textureHeight }
    @Deprecated void setUnk102(int unk102) { this.textureHeight = unk102 }

    @Deprecated String getUnk103() { foreTexture }
    @Deprecated void setUnk103(String unk103) { this.foreTexture = unk103 }

    @Deprecated String getUnk104() { backTexture }
    @Deprecated void setUnk104(String unk104) { this.backTexture = unk104 }

    @Deprecated String getUnk105() { warnTexture }
    @Deprecated void setUnk105(String unk105) { this.warnTexture = unk105 }

    @Deprecated String getUnk106() { regenTexture }
    @Deprecated void setUnk106(String unk106) { this.regenTexture = unk106 }
}
