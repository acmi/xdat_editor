package god25

import acmi.l2.clientmod.util.IOUtil

class CharacterViewportWindow extends DefaultProperty {
    float unk100
    int unk101
    int unk102
    int unk103
    int unk104
    int unk105
    int unk106
    int unk107
    int unk108
    String unk109
    int unk110

    @Override
    CharacterViewportWindow read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            unk100 = input.readFloat()
            unk101 = input.readInt()
            unk102 = input.readInt()
            unk103 = input.readInt()
            unk104 = input.readInt()
            unk105 = input.readInt()
            unk106 = input.readInt()
            unk107 = input.readInt()
            unk108 = input.readInt()
            unk109 = input.readString()
            unk110 = input.readInt()
        }

        this
    }

    @Override
    CharacterViewportWindow write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeFloat(unk100)
            output.writeInt(unk101)
            output.writeInt(unk102)
            output.writeInt(unk103)
            output.writeInt(unk104)
            output.writeInt(unk105)
            output.writeInt(unk106)
            output.writeInt(unk107)
            output.writeInt(unk108)
            output.writeString(unk109)
            output.writeInt(unk110)
        }

        this
    }
}
