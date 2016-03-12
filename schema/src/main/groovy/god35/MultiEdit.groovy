package god35

import acmi.l2.clientmod.util.IOUtil

class MultiEdit extends DefaultProperty {
    int unk100
    int unk101
    int unk102

    @Override
    MultiEdit read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            unk100 = input.readInt()
            unk101 = input.readInt()
            unk102 = input.readInt()
        }

        this
    }

    @Override
    MultiEdit write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeInt(unk100)
            output.writeInt(unk101)
            output.writeInt(unk102)
        }

        this
    }
}
