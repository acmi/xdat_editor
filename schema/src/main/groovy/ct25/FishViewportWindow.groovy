package ct25

import acmi.l2.clientmod.util.IOUtil

class FishViewportWindow extends DefaultProperty {
    String texBack
    String texClock
    String texFishHPBar
    String texFishHPBarBack
    String texFishFakeHPBarWarning
    String texFishingEffect
    String texIconPumping
    String texIconReeling

    @Override
    FishViewportWindow read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            texBack = input.readString()
            texClock = input.readString()
            texFishHPBar = input.readString()
            texFishHPBarBack = input.readString()
            texFishFakeHPBarWarning = input.readString()
            texFishingEffect = input.readString()
            texIconPumping = input.readString()
            texIconReeling = input.readString()
        }

        this
    }

    @Override
    FishViewportWindow write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeString(texBack)
            output.writeString(texClock)
            output.writeString(texFishHPBar)
            output.writeString(texFishHPBarBack)
            output.writeString(texFishFakeHPBarWarning)
            output.writeString(texFishingEffect)
            output.writeString(texIconPumping)
            output.writeString(texIconReeling)
        }

        this
    }
}
