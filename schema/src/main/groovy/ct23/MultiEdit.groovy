package ct23

import acmi.l2.clientmod.util.IOUtil

class MultiEdit extends DefaultProperty {
    int unk100
    int unk101

    @Override
    MultiEdit read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            unk100 = input.readInt()
            unk101 = input.readInt()
        }

        this
    }

    @Override
    MultiEdit write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeInt(unk100)
            output.writeInt(unk101)
        }

        this
    }
}
