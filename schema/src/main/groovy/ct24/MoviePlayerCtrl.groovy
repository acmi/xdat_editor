package ct24

import acmi.l2.clientmod.util.IOUtil

class MoviePlayerCtrl extends DefaultProperty {
    String unk100
    int unk101

    @Override
    MoviePlayerCtrl read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            unk100 = input.readString()
            unk101 = input.readInt()
        }

        this
    }

    @Override
    MoviePlayerCtrl write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeString(unk100)
            output.writeInt(unk101)
        }

        this
    }
}
