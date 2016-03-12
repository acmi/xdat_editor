package etoa2_3

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.Type

class MinimapCtrl extends DefaultProperty {
    boolean showTime
    boolean showTown
    boolean showGrid
    boolean showMyLocMark
    boolean showMyLocText
    boolean showSSQText
    @Type(MinimapCtrlUnk.class)
    List<MinimapCtrlUnk> unk106 = []

    static class MinimapCtrlUnk implements IOEntity {
        int unk1
        String unk2
        String unk3
        String unk4
        int unk5
        int unk6
        int unk7
        int unk8
        int unk9

        @Override
        MinimapCtrlUnk read(InputStream input) throws IOException {
            use(IOUtil) {
                unk1 = input.readInt()
                unk2 = input.readString()
                unk3 = input.readString()
                unk4 = input.readString()
                unk5 = input.readInt()
                unk6 = input.readInt()
                unk7 = input.readInt()
                unk8 = input.readInt()
                unk9 = input.readInt()
            }
            this
        }

        @Override
        MinimapCtrlUnk write(OutputStream output) throws IOException {
            use(IOUtil) {
                output.writeInt(unk1)
                output.writeString(unk2)
                output.writeString(unk3)
                output.writeString(unk4)
                output.writeInt(unk5)
                output.writeInt(unk6)
                output.writeInt(unk7)
                output.writeInt(unk8)
                output.writeInt(unk9)
            }
            this
        }
    }

    @Override
    MinimapCtrl read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            showTime = input.readBoolean()
            showTown = input.readBoolean()
            showGrid = input.readBoolean()
            showMyLocMark = input.readBoolean()
            showMyLocText = input.readBoolean()
            showSSQText = input.readBoolean()
            int count = input.readInt()
            for (int i = 0; i < count; i++)
                unk106.add(new MinimapCtrlUnk().read(input))
        }

        this
    }

    @Override
    MinimapCtrl write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeBoolean(showTime)
            output.writeBoolean(showTown)
            output.writeBoolean(showGrid)
            output.writeBoolean(showMyLocMark)
            output.writeBoolean(showMyLocText)
            output.writeBoolean(showSSQText)
            output.writeInt(unk106.size())
            for (MinimapCtrlUnk m : unk106)
                m.write(output)
        }

        this
    }

    @Deprecated boolean getUnk100() { showTime }
    @Deprecated void setUnk100(boolean unk100) { this.showTime = unk100 }

    @Deprecated boolean getUnk101() { showTown }
    @Deprecated void setUnk101(boolean unk101) { this.showTown = unk101 }

    @Deprecated boolean getUnk102() { showGrid }
    @Deprecated void setUnk102(boolean unk102) { this.showGrid = unk102 }

    @Deprecated boolean getUnk103() { showMyLocMark }
    @Deprecated void setUnk103(boolean unk103) { this.showMyLocMark = unk103 }

    @Deprecated boolean getUnk104() { showMyLocText }
    @Deprecated void setUnk104(boolean unk104) { this.showMyLocText = unk104 }

    @Deprecated boolean getUnk105() { showSSQText }
    @Deprecated void setUnk105(boolean unk105) { this.showSSQText = unk105 }
}
