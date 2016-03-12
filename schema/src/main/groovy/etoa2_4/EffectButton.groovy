package etoa2_4

import acmi.l2.clientmod.util.IOUtil

class EffectButton extends DefaultProperty {
    int unk100
    String tex = 'undefined'
    String texDown = 'undefined'
    String texOver = 'undefined'
    String texEffect1 = 'undefined'
    String texEffect2 = 'undefined'
    String unk101 = 'undefined'

    @Override
    EffectButton read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            unk100 = input.readInt()
            tex = input.readString()
            texDown = input.readString()
            texOver = input.readString()
            texEffect1 = input.readString()
            texEffect2 = input.readString()
            unk101 = input.readString()
        }

        this
    }

    @Override
    EffectButton write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeInt(unk100)
            output.writeString(tex)
            output.writeString(texDown)
            output.writeString(texOver)
            output.writeString(texEffect1)
            output.writeString(texEffect2)
            output.writeString(unk101)
        }

        this
    }
}
