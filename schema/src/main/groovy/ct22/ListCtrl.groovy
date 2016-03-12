package ct22

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.Type

class ListCtrl extends DefaultProperty {
    int unk100
    int unk101
    int unk102
    int unk103
    int unk104
    @Type(ListElement.class)
    List<ListElement> values = []

    static class ListElement implements IOEntity {
        int textStringId
        int width
        boolean unk108
        boolean unk109
        boolean unk110

        @Override
        ListElement read(InputStream input) {
            use(IOUtil) {
                textStringId = input.readInt()
                width = input.readInt()
                unk108 = input.readBoolean()
                unk109 = input.readBoolean()
                unk110 = input.readBoolean()
            }
            this
        }

        @Override
        ListElement write(OutputStream output) {
            use(IOUtil) {
                output.writeInt(textStringId)
                output.writeInt(width)
                output.writeBoolean(unk108)
                output.writeBoolean(unk109)
                output.writeBoolean(unk110)
            }
            this
        }

        @Override
        String toString() {
            return getClass().simpleName
        }
    }

    @Override
    ListCtrl read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            unk100 = input.readInt()
            unk101 = input.readInt()
            unk102 = input.readInt()
            unk103 = input.readInt()
            unk104 = input.readInt()
            int count = input.readInt()
            for (int i = 0; i < count; i++)
                values.add(new ListElement().read(input))
        }

        this
    }

    @Override
    ListCtrl write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeInt(unk100)
            output.writeInt(unk101)
            output.writeInt(unk102)
            output.writeInt(unk103)
            output.writeInt(unk104)
            output.writeInt(values.size())
            for (ListElement listElement : values)
                listElement.write(output)
        }

        this
    }
}
