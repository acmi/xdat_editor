package etoa2

import acmi.l2.clientmod.util.IOUtil

class StatusIconCtrl extends DefaultProperty {
    boolean noClip
    boolean noTooltip
    int align

    @Override
    StatusIconCtrl read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            noClip = input.readBoolean()
            noTooltip = input.readBoolean()
            align = input.readInt()
        }

        this
    }

    @Override
    StatusIconCtrl write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeBoolean(noClip)
            output.writeBoolean(noTooltip)
            output.writeInt(align)
        }

        this
    }

    @Deprecated boolean getUnk100() { noClip }
    @Deprecated void setUnk100(boolean unk100) { this.noClip = unk100 }

    @Deprecated boolean getUnk101() { noTooltip }
    @Deprecated void setUnk101(boolean unk101) { this.noTooltip = unk101 }

    @Deprecated boolean getUnk102() { align > 0 }
    @Deprecated void setUnk102(boolean unk102) { this.align = unk102 ? 1 : 0 }
}
