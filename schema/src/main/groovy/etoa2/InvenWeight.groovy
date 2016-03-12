package etoa2

import acmi.l2.clientmod.util.IOUtil

class InvenWeight extends DefaultProperty {
    String target
    int textureWidth
    int textureHeight
    String textureStepLeft
    String textureStepMid
    String textureStepRight
    String textureWarnLeft
    String textureWarnMid
    String textureWarnRight
    String textureAddedLeft
    String textureAddedMid
    String textureAddedRight
    String textureBackLeft
    String textureBackMid
    String textureBackRight
    String gaugeText
    int fontWidth
    int fontHeight

    @Override
    InvenWeight read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            target = input.readString()
            textureWidth = input.readInt()
            textureHeight = input.readInt()
            textureStepLeft = input.readString()
            textureStepMid = input.readString()
            textureStepRight = input.readString()
            textureWarnLeft = input.readString()
            textureWarnMid = input.readString()
            textureWarnRight = input.readString()
            textureAddedLeft = input.readString()
            textureAddedMid = input.readString()
            textureAddedRight = input.readString()
            textureBackLeft = input.readString()
            textureBackMid = input.readString()
            textureBackRight = input.readString()
            gaugeText = input.readString()
            fontWidth = input.readInt()
            fontHeight = input.readInt()
        }

        this
    }

    @Override
    InvenWeight write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeString(target)
            output.writeInt(textureWidth)
            output.writeInt(textureHeight)
            output.writeString(textureStepLeft)
            output.writeString(textureStepMid)
            output.writeString(textureStepRight)
            output.writeString(textureWarnLeft)
            output.writeString(textureWarnMid)
            output.writeString(textureWarnRight)
            output.writeString(textureAddedLeft)
            output.writeString(textureAddedMid)
            output.writeString(textureAddedRight)
            output.writeString(textureBackLeft)
            output.writeString(textureBackMid)
            output.writeString(textureBackRight)
            output.writeString(gaugeText)
            output.writeInt(fontWidth)
            output.writeInt(fontHeight)
        }

        this
    }

    @Deprecated String getUnk100() { target }
    @Deprecated void setUnk100(String unk100) { this.target = unk100 }

    @Deprecated int getUnk101() { textureWidth }
    @Deprecated void setUnk101(int unk101) { this.textureWidth = unk101 }

    @Deprecated int getUnk102() { textureHeight }
    @Deprecated void setUnk102(int unk102) { this.textureHeight = unk102 }

    @Deprecated String getUnk103() { textureStepLeft }
    @Deprecated void setUnk103(String unk103) { this.textureStepLeft = unk103 }

    @Deprecated String getUnk104() { textureStepMid }
    @Deprecated void setUnk104(String unk104) { this.textureStepMid = unk104 }

    @Deprecated String getUnk105() { textureStepRight }
    @Deprecated void setUnk105(String unk105) { this.textureStepRight = unk105 }

    @Deprecated String getUnk106() { textureWarnLeft }
    @Deprecated void setUnk106(String unk106) { this.textureWarnLeft = unk106 }

    @Deprecated String getUnk107() { textureWarnMid }
    @Deprecated void setUnk107(String unk107) { this.textureWarnMid = unk107 }

    @Deprecated String getUnk108() { textureWarnRight }
    @Deprecated void setUnk108(String unk108) { this.textureWarnRight = unk108 }

    @Deprecated String getUnk109() { textureAddedLeft }
    @Deprecated void setUnk109(String unk109) { this.textureAddedLeft = unk109 }

    @Deprecated String getUnk110() { textureAddedMid }
    @Deprecated void setUnk110(String unk110) { this.textureAddedMid = unk110 }

    @Deprecated String getUnk111() { textureAddedRight }
    @Deprecated void setUnk111(String unk111) { this.textureAddedRight = unk111 }

    @Deprecated String getUnk112() { textureBackLeft }
    @Deprecated void setUnk112(String unk112) { this.textureBackLeft = unk112 }

    @Deprecated String getUnk113() { textureBackMid }
    @Deprecated void setUnk113(String unk113) { this.textureBackMid = unk113 }

    @Deprecated String getUnk114() { textureBackRight }
    @Deprecated void setUnk114(String unk114) { this.textureBackRight = unk114 }

    @Deprecated String getUnk115() { gaugeText }
    @Deprecated void setUnk115(String unk115) { this.gaugeText = unk115 }

    @Deprecated int getUnk116() { fontWidth }
    @Deprecated void setUnk116(int unk116) { this.fontWidth = unk116 }

    @Deprecated int getUnk117() { fontHeight }
    @Deprecated void setUnk117(int unk117) { this.fontHeight = unk117 }
}
