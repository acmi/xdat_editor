package ct0

import acmi.l2.clientmod.util.IOUtil

class Progress extends BaseUI {
    String backTexture
    String barTexture
    int gap

    @Override
    Progress read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            backTexture = input.readString()
            barTexture = input.readString()
            gap = input.readInt()
        }

        this
    }

    @Override
    Progress write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeString(backTexture)
            output.writeString(barTexture)
            output.writeInt(gap)
        }

        this
    }

    @Deprecated String getUnk100() { backTexture }
    @Deprecated void setUnk100(String unk100) { this.backTexture = unk100 }

    @Deprecated String getUnk101() { barTexture }
    @Deprecated void setUnk101(String unk101) { this.barTexture = unk101 }

    @Deprecated int getUnk102() { gap }
    @Deprecated void setUnk102(int unk102) { this.gap = unk102 }
}
