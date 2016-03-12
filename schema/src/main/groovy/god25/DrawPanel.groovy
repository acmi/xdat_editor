package god25

import acmi.l2.clientmod.util.IOUtil

class DrawPanel extends DefaultProperty {
    int unk100

    @Override
    DrawPanel read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            unk100 = input.readInt()
        }

        this
    }

    @Override
    DrawPanel write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeInt(unk100)
        }

        this
    }
}
