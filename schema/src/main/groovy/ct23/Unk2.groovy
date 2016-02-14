package ct23

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.IOUtil
import javafx.scene.paint.Color

class Unk2 implements IOEntity {
    String unk0
    Color unk1 = new Color(0.0, 0.0, 0.0, 0.0)

    @Override
    Unk2 read(InputStream input) {
        use(IOUtil) {
            unk0 = input.readString()
            unk1 = input.readColor()
        }
        this
    }

    @Override
    Unk2 write(OutputStream output) {
        use(IOUtil) {
            output.writeString(unk0)
            output.writeColor(unk1)
        }
        this
    }

    @Override
    String toString() {
        return unk0
    }
}
