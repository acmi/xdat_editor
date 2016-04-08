package ct1

import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.Type

class ScrollArea extends DefaultProperty implements Iterable<DefaultProperty> {
    int areaHeight
    @Type(DefaultProperty.class)
    List<DefaultProperty> children = []

    @Override
    Iterator<DefaultProperty> iterator() {
        children.iterator()
    }

    @Override
    ScrollArea read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            areaHeight = input.readInt()
            int count = input.readInt()
            for (int i = 0; i < count; i++)
                children.add(input.readUIEntity(ScrollArea.class.package.name, getClass().classLoader) as DefaultProperty)
        }

        this
    }

    @Override
    ScrollArea write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeInt(areaHeight)
            output.writeInt(children.size())
            for (DefaultProperty DefaultProperty : children)
                output.writeUIEntity(DefaultProperty)
        }

        this
    }

    // @formatter:off
    @Deprecated int getUnk100() { areaHeight }
    @Deprecated void setUnk100(int unk100) { this.areaHeight = unk100 }
    // @formatter:on
}
