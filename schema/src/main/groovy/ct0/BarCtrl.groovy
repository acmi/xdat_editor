package ct0

import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.Tex

class BarCtrl extends DefaultProperty {
    @Tex
    String foreTexture = 'undefined'
    @Tex
    String backTexture = 'undefined'
    int uSize
    int vSize

    @Override
    BarCtrl read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            foreTexture = input.readString()
            backTexture = input.readString()
            uSize = input.readInt()
            vSize = input.readInt()
        }

        this
    }

    @Override
    BarCtrl write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeString(foreTexture)
            output.writeString(backTexture)
            output.writeInt(uSize)
            output.writeInt(vSize)
        }

        this
    }

    @Deprecated String getTex() { foreTexture }
    @Deprecated void setTex(String tex) { this.foreTexture = tex }

    @Deprecated String getTexBack() { backTexture }
    @Deprecated void setTexBack(String texBack) { this.backTexture = texBack }

    @Deprecated int getUnk106() { uSize }
    @Deprecated void setUnk106(int unk106) { this.uSize = unk106 }

    @Deprecated int getUnk107() { vSize }
    @Deprecated void setUnk107(int unk107) { this.vSize = unk107 }
}
