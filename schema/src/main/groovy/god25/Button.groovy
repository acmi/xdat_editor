package god25

import acmi.l2.clientmod.util.Description
import acmi.l2.clientmod.util.IOUtil

class Button extends DefaultProperty {
    String normalTex = 'undefined'
    String pushedTex = 'undefined'
    String highlightTex = 'undefined'
    @Description('undefined')
    String dropTex = 'undefined'
    int buttonName = -9999
    String buttonNameText
    @Description('-1')
    int noHighlight = -1
    @Description('-1')
    int defaultSoundOn = -1
    @Description('-9999/5000')
    int disableTime = -9999

    @Override
    Button read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            normalTex = input.readString()
            pushedTex = input.readString()
            highlightTex = input.readString()
            dropTex = input.readString()
            buttonName = input.readInt()
            buttonNameText = input.readString()
            noHighlight = input.readInt()
            defaultSoundOn = input.readInt()
            disableTime = input.readInt()
        }

        this
    }

    @Override
    Button write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeString(normalTex)
            output.writeString(pushedTex)
            output.writeString(highlightTex)
            output.writeString(dropTex)
            output.writeInt(buttonName)
            output.writeString(buttonNameText)
            output.writeInt(noHighlight)
            output.writeInt(defaultSoundOn)
            output.writeInt(disableTime)
        }

        this
    }

    @Deprecated String getTexture() { normalTex }
    @Deprecated void setTexture(String texture) { this.normalTex = texture }

    @Deprecated String getTextureDown() { pushedTex }
    @Deprecated void setTextureDown(String textureDown) { this.pushedTex = textureDown }

    @Deprecated String getTextureOver() { highlightTex }
    @Deprecated void setTextureOver(String textureOver) { this.highlightTex = textureOver }

    @Deprecated String getUnk103() { dropTex }
    @Deprecated void setUnk103(String unk103) { this.dropTex = unk103 }

    @Deprecated int getTextStringId() { buttonName }
    @Deprecated void setTextStringId(int textStringId) { this.buttonName = textStringId }

    @Deprecated String getTextStringValue() { buttonNameText }
    @Deprecated void setTextStringValue(String textStringValue) { this.buttonNameText = textStringValue }

    @Deprecated int getUnk106() { noHighlight }
    @Deprecated void setUnk106(int unk106) { this.noHighlight = unk106 }

    @Deprecated int getUnk107() { defaultSoundOn }
    @Deprecated void setUnk107(int unk107) { this.defaultSoundOn = unk107 }

    @Deprecated int getUnk108() { disableTime }
    @Deprecated void setUnk108(int unk108) { this.disableTime = unk108 }
}
