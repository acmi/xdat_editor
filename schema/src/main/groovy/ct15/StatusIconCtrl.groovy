package ct15

import acmi.l2.clientmod.util.IOUtil

class StatusIconCtrl extends DefaultProperty {
    boolean noClip
    boolean noTooltip

    @Override
    StatusIconCtrl read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            noClip = input.readBoolean()
            noTooltip = input.readBoolean()
        }

        this
    }

    @Override
    StatusIconCtrl write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeBoolean(noClip)
            output.writeBoolean(noTooltip)
        }

        this
    }

    @Deprecated boolean getUnk100() { noClip }
    @Deprecated void setUnk100(boolean unk100) { this.noClip = unk100 }

    @Deprecated boolean getUnk101() { noTooltip }
    @Deprecated void setUnk101(boolean unk101) { this.noTooltip = unk101 }
}
