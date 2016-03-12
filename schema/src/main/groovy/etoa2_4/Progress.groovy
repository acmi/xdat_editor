package etoa2_4

import acmi.l2.clientmod.util.IOUtil

class Progress extends DefaultProperty {
    String unk100
    String backLeftTexture
    String backTexture
    String backRightTexture
    String barLeftTexture
    String barTexture
    String barRightTexture
    int gap
    int textureSize
    ProgressBarType type = ProgressBarType.None

    @Override
    Progress read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            unk100 = input.readString()
            backLeftTexture = input.readString()
            backTexture = input.readString()
            backRightTexture = input.readString()
            barLeftTexture = input.readString()
            barTexture = input.readString()
            barRightTexture = input.readString()
            gap = input.readInt()
            textureSize = input.readInt()
            type = ProgressBarType.values()[input.readInt()]
        }

        this
    }

    @Override
    Progress write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeString(unk100)
            output.writeString(backLeftTexture)
            output.writeString(backTexture)
            output.writeString(backRightTexture)
            output.writeString(barLeftTexture)
            output.writeString(barTexture)
            output.writeString(barRightTexture)
            output.writeInt(gap)
            output.writeInt(textureSize)
            output.writeInt(type.ordinal())
        }

        this
    }

    enum ProgressBarType {
        None,
        RightLeft,
        LeftRight,
        TopBottom,
        BottomTop,
    }

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
}
