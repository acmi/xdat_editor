package god3

import acmi.l2.clientmod.util.IOUtil

class ListBox extends DefaultProperty {
    int maxRow
    int showRow
    int showLastLine

    @Override
    ListBox read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            maxRow = input.readInt()
            showRow = input.readInt()
            showLastLine = input.readInt()
        }

        this
    }

    @Override
    ListBox write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeInt(maxRow)
            output.writeInt(showRow)
            output.writeInt(showLastLine)
        }

        this
    }

    @Deprecated int getUnk100() { maxRow }
    @Deprecated void setUnk100(int unk100) { this.maxRow = unk100 }

    @Deprecated int getUnk101() { showRow }
    @Deprecated void setUnk101(int unk101) { this.showRow = unk101 }

    @Deprecated int getUnk102() { showLastLine }
    @Deprecated void setUnk102(int unk102) { this.showLastLine = unk102 }
}
