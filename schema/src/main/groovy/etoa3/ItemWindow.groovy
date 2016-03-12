package etoa3

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.Type

class ItemWindow extends DefaultProperty {
    String unk100
    int cols
    int rows
    int unk103
    int cellWidth
    int cellHeight
    int spacing_horizontal
    int spacing_vertical
    int unk108
    int unk109
    int unk110
    int unk111
    String unk112
    int unk113
    int unk114
    String unk115
    int unk116
    int unk117
    String unk118
    int unk119
    int unk120
    String unk121
    int unk1211
    int unk122
    int unk123
    int unk124
    int unk125
    int unk126
    int unk127
    int unk127_
    String unk128
    String unk129
    int unk130
    int unk131
    int unk132
    int unk133
    @Type(ItemWindowInner.class)
    List<ItemWindowInner> unk134 = []

    static class ItemWindowInner implements IOEntity {
        int unk135
        int unk136
        int unk137
        String unk138

        @Override
        ItemWindowInner read(InputStream input) {
            use(IOUtil) {
                unk135 = input.readInt()
                unk136 = input.readInt()
                unk137 = input.readInt()
                unk138 = input.readString()
            }
            this
        }

        @Override
        ItemWindowInner write(OutputStream output) {
            use(IOUtil) {
                output.writeInt(unk135)
                output.writeInt(unk136)
                output.writeInt(unk137)
                output.writeString(unk138)
            }
            this
        }

        @Override
        String toString() {
            return getClass().simpleName
        }
    }

    @Override
    ItemWindow read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            unk100 = input.readString()
            cols = input.readInt()
            rows = input.readInt()
            unk103 = input.readInt()
            cellWidth = input.readInt()
            cellHeight = input.readInt()
            spacing_horizontal = input.readInt()
            spacing_vertical = input.readInt()
            unk108 = input.readInt()
            unk109 = input.readInt()
            unk110 = input.readInt()
            unk111 = input.readInt()
            unk112 = input.readString()
            unk113 = input.readInt()
            unk114 = input.readInt()
            unk115 = input.readString()
            unk116 = input.readInt()
            unk117 = input.readInt()
            unk118 = input.readString()
            unk119 = input.readInt()
            unk120 = input.readInt()
            unk121 = input.readString()
            unk1211 = input.readInt()
            unk122 = input.readInt()
            unk123 = input.readInt()
            unk124 = input.readInt()
            unk125 = input.readInt()
            unk126 = input.readInt()
            unk127 = input.readInt()
            unk127_ = input.readInt()
            unk128 = input.readString()
            unk129 = input.readString()
            unk130 = input.readInt()
            unk131 = input.readInt()
            unk132 = input.readInt()
            unk133 = input.readInt()
            int count = input.readInt()
            for (int i = 0; i < count; i++)
                unk134.add(new ItemWindowInner().read(input))
        }

        this
    }

    @Override
    ItemWindow write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeString(unk100)
            output.writeInt(cols)
            output.writeInt(rows)
            output.writeInt(unk103)
            output.writeInt(cellWidth)
            output.writeInt(cellHeight)
            output.writeInt(spacing_horizontal)
            output.writeInt(spacing_vertical)
            output.writeInt(unk108)
            output.writeInt(unk109)
            output.writeInt(unk110)
            output.writeInt(unk111)
            output.writeString(unk112)
            output.writeInt(unk113)
            output.writeInt(unk114)
            output.writeString(unk115)
            output.writeInt(unk116)
            output.writeInt(unk117)
            output.writeString(unk118)
            output.writeInt(unk119)
            output.writeInt(unk120)
            output.writeString(unk121)
            output.writeInt(unk1211)
            output.writeInt(unk122)
            output.writeInt(unk123)
            output.writeInt(unk124)
            output.writeInt(unk125)
            output.writeInt(unk126)
            output.writeInt(unk127)
            output.writeInt(unk127_)
            output.writeString(unk128)
            output.writeString(unk129)
            output.writeInt(unk130)
            output.writeInt(unk131)
            output.writeInt(unk132)
            output.writeInt(unk133)
            output.writeInt(unk134.size())
            for (ItemWindowInner itemWindowInner : unk134)
                itemWindowInner.write(output)
        }

        this
    }
}
