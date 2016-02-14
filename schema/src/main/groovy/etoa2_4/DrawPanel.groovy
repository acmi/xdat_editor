package etoa2_4

import acmi.l2.clientmod.util.IOUtil

class DrawPanel extends BaseUI {
    int unk100

    @Override
    BaseUI read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            unk100 = input.readInt()
        }

        this
    }

    @Override
    BaseUI write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeInt(unk100)
        }

        this
    }
}
