package ct0

import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.Type

class Window extends BaseUI implements Iterable<BaseUI> {
    String unk100
    String unk101
    String unk102
    String unk103
    int unk104
    int unk105
    int unk106
    int unk107
    int unk108
    int unk109
    int unk110
    int unk111
    int unk112
    int unk113
    int unk114
    int unk115
    int unk116
    int unk117
    int unk118
    int unk119
    float unk120
    float unk121
    int unk122
    int unk123
    int unk124
    int unk125
    int unk126
    int unk127
    int unk128
    String unk129
    int unk130
    int unk131
    int unk132
    int unk133
    int unk134
    int unk135
    String unk136
    int unk137
    int unk138
    String unk139
    @Type(BaseUI.class)
    List<BaseUI> children = []

    @Override
    Iterator<BaseUI> iterator() {
        children.iterator()
    }

    @Override
    Window read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            unk100 = input.readString()
            unk101 = input.readString()
            unk102 = input.readString()
            unk103 = input.readString()
            unk104 = input.readInt()
            unk105 = input.readInt()
            unk106 = input.readInt()
            unk107 = input.readInt()
            unk108 = input.readInt()
            unk109 = input.readInt()
            unk110 = input.readInt()
            unk111 = input.readInt()
            unk112 = input.readInt()
            unk113 = input.readInt()
            unk114 = input.readInt()
            unk115 = input.readInt()
            unk116 = input.readInt()
            unk117 = input.readInt()
            unk118 = input.readInt()
            unk119 = input.readInt()
            unk120 = input.readFloat()
            unk121 = input.readFloat()
            unk122 = input.readInt()
            unk123 = input.readInt()
            unk124 = input.readInt()
            unk125 = input.readInt()
            unk126 = input.readInt()
            unk127 = input.readInt()
            unk128 = input.readInt()
            unk129 = input.readString()
            unk130 = input.readInt()
            unk131 = input.readInt()
            unk132 = input.readInt()
            unk133 = input.readInt()
            unk134 = input.readInt()
            unk135 = input.readInt()
            unk136 = input.readString()
            unk137 = input.readInt()
            unk138 = input.readInt()
            unk139 = input.readString()
            int count = input.readInt()
            for (int i = 0; i < count; i++)
                children.add(input.readObject(Window.class.package.name, getClass().classLoader) as BaseUI)
        }

        this
    }

    @Override
    Window write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeString(unk100)
            output.writeString(unk101)
            output.writeString(unk102)
            output.writeString(unk103)
            output.writeInt(unk104)
            output.writeInt(unk105)
            output.writeInt(unk106)
            output.writeInt(unk107)
            output.writeInt(unk108)
            output.writeInt(unk109)
            output.writeInt(unk110)
            output.writeInt(unk111)
            output.writeInt(unk112)
            output.writeInt(unk113)
            output.writeInt(unk114)
            output.writeInt(unk115)
            output.writeInt(unk116)
            output.writeInt(unk117)
            output.writeInt(unk118)
            output.writeInt(unk119)
            output.writeFloat(unk120)
            output.writeFloat(unk121)
            output.writeInt(unk122)
            output.writeInt(unk123)
            output.writeInt(unk124)
            output.writeInt(unk125)
            output.writeInt(unk126)
            output.writeInt(unk127)
            output.writeInt(unk128)
            output.writeString(unk129)
            output.writeInt(unk130)
            output.writeInt(unk131)
            output.writeInt(unk132)
            output.writeInt(unk133)
            output.writeInt(unk134)
            output.writeInt(unk135)
            output.writeString(unk136)
            output.writeInt(unk137)
            output.writeInt(unk138)
            output.writeString(unk139)
            output.writeInt(children.size())
            for (BaseUI baseUI : children)
                output.writeObject(baseUI)
        }

        this
    }
}
