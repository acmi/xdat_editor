package etoa2_2_ru

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.Type

class Window extends DefaultProperty implements Iterable<DefaultProperty> {
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
    int unk1151
    int unk116
    int unk117
    float unk118
    float unk119
    float unk120
    float unk121
    int unk122
    int unk123
    int unk124
    int unk125
    int unk126
    String unk127
    int unk128
    int unk129
    int unk130
    int unk131
    int unk132
    int unk133
    String unk134
    int unk135
    int unk136
    String unk137
    String unk138
    String unk139
    String unk140
    String unk141
    String unk142
    String unk143
    String unk144
    int unk145
    int unk146
    @Type(State.class)
    List<State> unk147 = []
    int unk149
    @Type(DefaultProperty.class)
    List<DefaultProperty> children = []

    @Override
    Iterator<DefaultProperty> iterator() {
        children.iterator()
    }

    static class State implements IOEntity {
        String unk148

        @Override
        State read(InputStream input) {
            use(IOUtil) {
                unk148 = input.readString()
            }
            this
        }

        @Override
        State write(OutputStream output) {
            use(IOUtil) {
                output.writeString(unk148)
            }
            this
        }

        @Override
        String toString() {
            return State.class.simpleName
        }
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
            unk1151 = input.readInt()
            unk116 = input.readInt()
            unk117 = input.readInt()
            unk118 = input.readFloat()
            unk119 = input.readFloat()
            unk120 = input.readFloat()
            unk121 = input.readFloat()
            unk122 = input.readInt()
            unk123 = input.readInt()
            unk124 = input.readInt()
            unk125 = input.readInt()
            unk126 = input.readInt()
            unk127 = input.readString()
            unk128 = input.readInt()
            unk129 = input.readInt()
            unk130 = input.readInt()
            unk131 = input.readInt()
            unk132 = input.readInt()
            unk133 = input.readInt()
            unk134 = input.readString()
            unk135 = input.readInt()
            unk136 = input.readInt()
            unk137 = input.readString()
            unk138 = input.readString()
            unk139 = input.readString()
            unk140 = input.readString()
            unk141 = input.readString()
            unk142 = input.readString()
            unk143 = input.readString()
            unk144 = input.readString()
            unk145 = input.readInt()
            unk146 = input.readInt()
            int count = input.readCompactInt()
            for (int i = 0; i < count; i++)
                unk147.add(new State().read(input))
            unk149 = input.readInt()
            count = input.readInt()
            for (int i = 0; i < count; i++)
                children.add(input.readUIEntity(Window.class.package.name, getClass().classLoader) as DefaultProperty)
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
            output.writeInt(unk1151)
            output.writeInt(unk116)
            output.writeInt(unk117)
            output.writeFloat(unk118)
            output.writeFloat(unk119)
            output.writeFloat(unk120)
            output.writeFloat(unk121)
            output.writeInt(unk122)
            output.writeInt(unk123)
            output.writeInt(unk124)
            output.writeInt(unk125)
            output.writeInt(unk126)
            output.writeString(unk127)
            output.writeInt(unk128)
            output.writeInt(unk129)
            output.writeInt(unk130)
            output.writeInt(unk131)
            output.writeInt(unk132)
            output.writeInt(unk133)
            output.writeString(unk134)
            output.writeInt(unk135)
            output.writeInt(unk136)
            output.writeString(unk137)
            output.writeString(unk138)
            output.writeString(unk139)
            output.writeString(unk140)
            output.writeString(unk141)
            output.writeString(unk142)
            output.writeString(unk143)
            output.writeString(unk144)
            output.writeInt(unk145)
            output.writeInt(unk146)
            output.writeCompactInt(unk147.size())
            for (State state : unk147)
                state.write(output)
            output.writeInt(unk149)
            output.writeInt(children.size())
            for (DefaultProperty DefaultProperty : children)
                output.writeUIEntity(DefaultProperty)
        }

        this
    }
}
