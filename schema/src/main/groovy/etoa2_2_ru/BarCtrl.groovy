package etoa2_2_ru

import acmi.l2.clientmod.util.IOUtil

class BarCtrl extends BaseUI {
    String foreTexture = 'undefined'
    String foreLeftTexture = 'undefined'
    String foreRightTexture = 'undefined'
    String backTexture = 'undefined'
    String backLeftTexture = 'undefined'
    String backRightTexture = 'undefined'
    int uSize
    int vSize

    @Override
    BarCtrl read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            foreTexture = input.readString()
            foreLeftTexture = input.readString()
            foreRightTexture = input.readString()
            backTexture = input.readString()
            backLeftTexture = input.readString()
            backRightTexture = input.readString()
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
            output.writeString(foreLeftTexture)
            output.writeString(foreRightTexture)
            output.writeString(backTexture)
            output.writeString(backLeftTexture)
            output.writeString(backRightTexture)
            output.writeInt(uSize)
            output.writeInt(vSize)
        }

        this
    }

    @Deprecated String getTexCenter() { foreTexture }
    @Deprecated void setTexCenter(String texCenter) { this.foreTexture = texCenter }

    @Deprecated String getTextLeft() { foreLeftTexture }
    @Deprecated void setTextLeft(String textLeft) { this.foreLeftTexture = textLeft }

    @Deprecated String getTexRight() { foreRightTexture }
    @Deprecated void setTexRight(String texRight) { this.foreRightTexture = texRight }

    @Deprecated String getTexBGCenter() { backTexture }
    @Deprecated void setTexBGCenter(String texBGCenter) { this.backTexture = texBGCenter }

    @Deprecated String getTexBGLeft() { backLeftTexture }
    @Deprecated void setTexBGLeft(String texBGLeft) { this.backLeftTexture = texBGLeft }

    @Deprecated String getTexBGRight() { backRightTexture }
    @Deprecated void setTexBGRight(String texBGRight) { this.backRightTexture = texBGRight }

    @Deprecated int getUnk106() { uSize }
    @Deprecated void setUnk106(int unk106) { this.uSize = unk106 }

    @Deprecated int getUnk107() { vSize }
    @Deprecated void setUnk107(int unk107) { this.vSize = unk107 }
}
