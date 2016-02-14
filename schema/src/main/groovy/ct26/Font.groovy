package ct26

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.IOUtil

class Font implements IOEntity {
    String name
    String file
    String location
    int size
    int unk4
    int unk5
    boolean shadow
    int shadow_x
    int shadow_y
    boolean stroke
    boolean stroke_large
    int unk11
    int unk12

    @Override
    Font read(InputStream input) {
        use(IOUtil) {
            name = input.readString()
            file = input.readString()
            location = input.readString()
            size = input.readInt()
            unk4 = input.readInt()
            unk5 = input.readInt()
            shadow = input.readBoolean()
            shadow_x = input.readInt()
            shadow_y = input.readInt()
            stroke = input.readBoolean()
            stroke_large = input.readBoolean()
            unk11 = input.readInt()
            unk12 = input.readInt()
        }
        this
    }

    @Override
    Font write(OutputStream output) {
        use(IOUtil) {
            output.writeString(name)
            output.writeString(file)
            output.writeString(location)
            output.writeInt(size)
            output.writeInt(unk4)
            output.writeInt(unk5)
            output.writeBoolean(shadow)
            output.writeInt(shadow_x)
            output.writeInt(shadow_y)
            output.writeBoolean(stroke)
            output.writeBoolean(stroke_large)
            output.writeInt(unk11)
            output.writeInt(unk12)
        }
        this
    }

    @Override
    String toString() {
        return name
    }
}
