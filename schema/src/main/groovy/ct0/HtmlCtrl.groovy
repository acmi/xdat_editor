package ct0

import acmi.l2.clientmod.util.IOUtil

class HtmlCtrl extends BaseUI {
    String type

    @Override
    HtmlCtrl read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            type = input.readString()
        }

        this
    }

    @Override
    HtmlCtrl write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeString(type)
        }

        this
    }
}
