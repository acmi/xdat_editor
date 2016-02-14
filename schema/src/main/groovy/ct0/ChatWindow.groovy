package ct0

import acmi.l2.clientmod.util.Description
import acmi.l2.clientmod.util.IOUtil

class ChatWindow extends BaseUI {
    @Description('Vertical space between text lines')
    int lineGap

    @Override
    ChatWindow read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            lineGap = input.readInt()
        }

        this
    }

    @Override
    ChatWindow write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeInt(lineGap)
        }

        this
    }

    @Deprecated int getSpacing() { lineGap }
    @Deprecated void setSpacing(int spacing) { this.lineGap = spacing }
}
