package god3

import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.Type

class ScrollArea extends BaseUI implements Iterable<BaseUI> {
    int areaHeight
    @Type(BaseUI.class)
    List<BaseUI> children = []

    @Override
    Iterator<BaseUI> iterator() {
        children.iterator()
    }

    @Override
    ScrollArea read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            areaHeight = input.readInt()
            int count = input.readInt()
            for (int i = 0; i < count; i++)
                children.add(input.readObject(ScrollArea.class.package.name, getClass().classLoader) as BaseUI)
        }

        this
    }

    @Override
    ScrollArea write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeInt(areaHeight)
            output.writeInt(children.size())
            for (BaseUI baseUI : children)
                output.writeObject(baseUI)
        }

        this
    }

    @Deprecated int getUnk100() { areaHeight }
    @Deprecated void setUnk100(int unk100) { this.areaHeight = unk100 }
}
