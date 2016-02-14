package etoa3_

import acmi.l2.clientmod.util.IOUtil

class EditBox extends BaseUI {
    Type type = Type.NORMAL
    int maxLength
    int showCursor = -1
    boolean unk103
    int offsetX = -9999
    boolean candidateBoxShowUpPos = true
    int unk106 = 0
    int unk107 = 0

    @Override
    EditBox read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            type = Type.values()[input.readInt()]
            maxLength = input.readInt()
            showCursor = input.readInt()
            unk103 = input.readBoolean()
            offsetX = input.readInt()
            candidateBoxShowUpPos = input.readBoolean()
            unk106 = input.readInt()
            unk107 = input.readInt()
        }

        this
    }

    @Override
    EditBox write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeInt(type.ordinal())
            output.writeInt(maxLength)
            output.writeInt(showCursor)
            output.writeBoolean(unk103)
            output.writeInt(offsetX)
            output.writeBoolean(candidateBoxShowUpPos)
            output.writeInt(unk106)
            output.writeInt(unk107)
        }

        this
    }

    enum Type {
        NORMAL,
        CHAT,
        PASSWORD,
        COUNT,    //number with delimiters
        NUMBER,
        UNK5,
        TYPE6,
    }
}
