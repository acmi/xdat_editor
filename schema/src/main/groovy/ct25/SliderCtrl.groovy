package ct25

import acmi.l2.clientmod.util.IOUtil

class SliderCtrl extends DefaultProperty {
    int numOfTick
    int currTick
    int thumbBtnWidth
    int thumbBtnHeight
    String backTexture = 'L2UI_ch3.Button.slider_back'
    String disableBackTexture = 'L2UI_ch3.Button.slider_back'
    String thumbBtnNormalTexture = 'L2UI_ch3.Button.slider_cursor'
    String thumbBtnDownTexture = 'L2UI_ch3.Button.slider_cursor_down'
    int pushBtnWidth = -1
    int pushBtnHeight = -1
    int pushBtnAutoHitTime = 300
    int unk111 = 0
    String tickTexture = 'L2UI_ch3.Button.slider_mark'
    int unk113 = 1

    @Override
    SliderCtrl read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            numOfTick = input.readInt()
            currTick = input.readInt()
            thumbBtnWidth = input.readInt()
            thumbBtnHeight = input.readInt()
            backTexture = input.readString()
            disableBackTexture = input.readString()
            thumbBtnNormalTexture = input.readString()
            thumbBtnDownTexture = input.readString()
            pushBtnWidth = input.readInt()
            pushBtnHeight = input.readInt()
            pushBtnAutoHitTime = input.readInt()
            unk111 = input.readInt()
            tickTexture = input.readString()
            unk113 = input.readInt()
        }

        this
    }

    @Override
    SliderCtrl write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeInt(numOfTick)
            output.writeInt(currTick)
            output.writeInt(thumbBtnWidth)
            output.writeInt(thumbBtnHeight)
            output.writeString(backTexture)
            output.writeString(disableBackTexture)
            output.writeString(thumbBtnNormalTexture)
            output.writeString(thumbBtnDownTexture)
            output.writeInt(pushBtnWidth)
            output.writeInt(pushBtnHeight)
            output.writeInt(pushBtnAutoHitTime)
            output.writeInt(unk111)
            output.writeString(tickTexture)
            output.writeInt(unk113)
        }

        this
    }

    @Deprecated int getUnk100() { numOfTick }
    @Deprecated void setUnk100(int unk100) { this.numOfTick = unk100 }

    @Deprecated int getUnk101() { currTick }
    @Deprecated void setUnk101(int unk101) { this.currTick = unk101 }

    @Deprecated int getUnk102() { thumbBtnWidth }
    @Deprecated void setUnk102(int unk102) { this.thumbBtnWidth = unk102 }

    @Deprecated int getUnk103() { thumbBtnHeight }
    @Deprecated void setUnk103(int unk103) { this.thumbBtnHeight = unk103 }

    @Deprecated String getUnk104() { backTexture }
    @Deprecated void setUnk104(String unk104) { this.backTexture = unk104 }

    @Deprecated String getUnk105() { disableBackTexture }
    @Deprecated void setUnk105(String unk105) { this.disableBackTexture = unk105 }

    @Deprecated String getUnk106() { thumbBtnNormalTexture }
    @Deprecated void setUnk106(String unk106) { this.thumbBtnNormalTexture = unk106 }

    @Deprecated String getUnk107() { thumbBtnDownTexture }
    @Deprecated void setUnk107(String unk107) { this.thumbBtnDownTexture = unk107 }

    @Deprecated int getUnk108() { pushBtnWidth }
    @Deprecated void setUnk108(int unk108) { this.pushBtnWidth = unk108 }

    @Deprecated int getUnk109() { pushBtnHeight }
    @Deprecated void setUnk109(int unk109) { this.pushBtnHeight = unk109 }

    @Deprecated int getUnk110() { pushBtnAutoHitTime }
    @Deprecated void setUnk110(int unk110) { this.pushBtnAutoHitTime = unk110 }

    @Deprecated String getUnk112() { tickTexture }
    @Deprecated void setUnk112(String unk112) { this.tickTexture = unk112 }
}
