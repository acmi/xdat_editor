package ct15

import acmi.l2.clientmod.util.IOUtil

class EffectButton extends DefaultProperty {
    int type
    String normalTex = 'undefined'
    String pushedTex = 'undefined'
    String highlightTex = 'undefined'
    String effectTex1 = 'undefined'
    String effectTex2 = 'undefined'

    @Override
    EffectButton read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            type = input.readInt()
            normalTex = input.readString()
            pushedTex = input.readString()
            highlightTex = input.readString()
            effectTex1 = input.readString()
            effectTex2 = input.readString()
        }

        this
    }

    @Override
    EffectButton write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeInt(type)
            output.writeString(normalTex)
            output.writeString(pushedTex)
            output.writeString(highlightTex)
            output.writeString(effectTex1)
            output.writeString(effectTex2)
        }

        this
    }

    @Deprecated int getUnk100() { type }
    @Deprecated void setUnk100(int unk100) { this.type = unk100 }

    @Deprecated String getTex() { normalTex }
    @Deprecated void setTex(String tex) { this.normalTex = tex }

    @Deprecated String getTexDown() { pushedTex }
    @Deprecated void setTexDown(String texDown) { this.pushedTex = texDown }

    @Deprecated String getTexOver() { highlightTex }
    @Deprecated void setTexOver(String texOver) { this.highlightTex = texOver }

    @Deprecated String getTexEffect1() { effectTex1 }
    @Deprecated void setTexEffect1(String texEffect1) { this.effectTex1 = texEffect1 }

    @Deprecated String getTexEffect2() { effectTex2 }
    @Deprecated void setTexEffect2(String texEffect2) { this.effectTex2 = texEffect2 }
}
