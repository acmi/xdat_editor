package ct0

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.IOUtil

class WndDefPos implements IOEntity {
    String wnd
    Alignment alignment = Alignment.NONE
    int x
    int y
    int unk4
    int width
    int height

    @Override
    WndDefPos read(InputStream input) {
        use(IOUtil) {
            wnd = input.readString()
            alignment = Alignment.values()[input.readInt()]
            x = input.readInt()
            y = input.readInt()
            unk4 = input.readInt()
            width = input.readInt()
            height = input.readInt()
        }

        this
    }

    @Override
    WndDefPos write(OutputStream output) {
        use(IOUtil) {
            output.writeString(wnd)
            output.writeInt(alignment.ordinal())
            output.writeInt(x)
            output.writeInt(y)
            output.writeInt(unk4)
            output.writeInt(width)
            output.writeInt(height)
        }

        this
    }

    @Override
    String toString() {
        return wnd
    }
}
