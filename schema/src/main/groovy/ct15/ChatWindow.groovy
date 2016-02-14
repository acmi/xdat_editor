package ct15

import acmi.l2.clientmod.util.Description
import acmi.l2.clientmod.util.IOUtil

class ChatWindow extends BaseUI {
    @Description('Vertical space between text lines')
    int lineGap
    @Description('-9999')
    int nextLineOffsetX = -9999

    @Override
    ChatWindow read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            lineGap = input.readInt()
            nextLineOffsetX = input.readInt()
        }

        this
    }

    @Override
    ChatWindow write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeInt(lineGap)
            output.writeInt(nextLineOffsetX)
        }

        this
    }

    @Deprecated int getSpacing() { lineGap }
    @Deprecated void setSpacing(int spacing) { this.lineGap = spacing }

    @Deprecated int getUnk101() { nextLineOffsetX }
    @Deprecated void setUnk101(int unk101) { this.nextLineOffsetX = unk101 }
}
