package etoa2_3

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.IOUtil

class WndDefPos implements IOEntity {
    String wnd
    Alignment alignment = Alignment.NONE
    int x
    int y
    boolean moveParent //двигает весь интерфейс
    int width
    int height

    @Override
    WndDefPos read(InputStream input) {
        use(IOUtil) {
            wnd = input.readString()
            alignment = Alignment.values()[input.readInt()]
            x = input.readInt()
            y = input.readInt()
            moveParent = input.readBoolean()
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
            output.writeBoolean(moveParent)
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
