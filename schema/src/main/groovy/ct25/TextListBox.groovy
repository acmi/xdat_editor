package ct25

import acmi.l2.clientmod.util.IOUtil

class TextListBox extends DefaultProperty {
    int maxRow
    int showRow
    int lineGap
    int isShowScroll

    @Override
    TextListBox read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            maxRow = input.readInt()
            showRow = input.readInt()
            lineGap = input.readInt()
            isShowScroll = input.readInt()
        }

        this
    }

    @Override
    TextListBox write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeInt(maxRow)
            output.writeInt(showRow)
            output.writeInt(lineGap)
            output.writeInt(isShowScroll)
        }

        this
    }

    @Deprecated int getUnk100() { maxRow }
    @Deprecated void setUnk100(int unk100) { this.maxRow = unk100 }

    @Deprecated int getUnk101() { showRow }
    @Deprecated void setUnk101(int unk101) { this.showRow = unk101 }

    @Deprecated int getUnk102() { lineGap }
    @Deprecated void setUnk102(int unk102) { this.lineGap = unk102 }

    @Deprecated int getUnk103() { isShowScroll }
    @Deprecated void setUnk103(int unk103) { this.isShowScroll = unk103 }
}
