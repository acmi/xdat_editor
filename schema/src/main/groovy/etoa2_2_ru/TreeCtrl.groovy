package etoa2_2_ru

import acmi.l2.clientmod.util.IOUtil

class TreeCtrl extends DefaultProperty {
    boolean saveExpandedNode
    int multiExpand

    @Override
    TreeCtrl read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            saveExpandedNode = input.readBoolean()
            multiExpand = input.readInt()
        }

        this
    }

    @Override
    TreeCtrl write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeBoolean(saveExpandedNode)
            output.writeInt(multiExpand)
        }

        this
    }

    @Deprecated boolean getUnk100() { saveExpandedNode }
    @Deprecated void setUnk100(boolean unk100) { this.saveExpandedNode = unk100 }

    @Deprecated int getUnk101() { multiExpand }
    @Deprecated void setUnk101(int unk101) { this.multiExpand = unk101 }
}
