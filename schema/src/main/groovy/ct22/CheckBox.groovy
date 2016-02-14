package ct22

import acmi.l2.clientmod.util.IOUtil

class CheckBox extends BaseUI {
    int titleIndex = -1
    String titleText = 'undefined'
    int checked = -1
    int leftAligned = -1
    int maxWidth = -9999
    String checkTexture = 'undefined'
    String unCheckTexture = 'undefined'
    String disableTexture = 'undefined'
    String disableCheckTexture = 'undefined'

    @Override
    CheckBox read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            titleIndex = input.readInt()
            titleText = input.readString()
            checked = input.readInt()
            leftAligned = input.readInt()
            maxWidth = input.readInt()
            checkTexture = input.readString()
            unCheckTexture = input.readString()
            disableTexture = input.readString()
            disableCheckTexture = input.readString()
        }

        this
    }

    @Override
    CheckBox write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeInt(titleIndex)
            output.writeString(titleText)
            output.writeInt(checked)
            output.writeInt(leftAligned)
            output.writeInt(maxWidth)
            output.writeString(checkTexture)
            output.writeString(unCheckTexture)
            output.writeString(disableTexture)
            output.writeString(disableCheckTexture)
        }

        this
    }

    @Deprecated int getLabelStringId() { titleIndex }
    @Deprecated void setLabelStringId(int labelStringId) { this.titleIndex = labelStringId }

    @Deprecated String getLabelString() { titleText }
    @Deprecated void setLabelString(String labelString) { this.titleText = labelString }

    @Deprecated int getUnk102() { checked }
    @Deprecated void setUnk102(int unk102) { this.checked = unk102 }

    @Deprecated int getUnk103() { leftAligned }
    @Deprecated void setUnk103(int unk103) { this.leftAligned = unk103 }

    @Deprecated int getUnk104() { maxWidth }
    @Deprecated void setUnk104(int unk104) { this.maxWidth = unk104 }

    @Deprecated String getTexChecked() { checkTexture }
    @Deprecated void setTexChecked(String texChecked) { this.checkTexture = texChecked }

    @Deprecated String getTex() { unCheckTexture }
    @Deprecated void setTex(String tex) { this.unCheckTexture = tex }

    @Deprecated String getTexUnable() { disableTexture }
    @Deprecated void setTexUnable(String texUnable) { this.disableTexture = texUnable }

    @Deprecated String getTexCheckedUnable() { disableCheckTexture }
    @Deprecated void setTexCheckedUnable(String texCheckedUnable) { this.disableCheckTexture = texCheckedUnable }
}
