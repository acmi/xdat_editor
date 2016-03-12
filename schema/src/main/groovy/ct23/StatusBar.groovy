package ct23

import acmi.l2.clientmod.util.IOUtil

class StatusBar extends DefaultProperty {
    String title
    int barWidth
    int barHeight
    String foreTex
    String foreLeftTex
    String foreRightTex
    String backTex
    String backLeftTex
    String backRightTex
    String regenLeftTex
    String regenTex
    String regenRightTex
    String warnTex
    String warnLeftTex
    String warnRightTex
    String gaugeFontTextureName
    int gaugeFontSizeX
    int gaugeFontSizeY

    @Override
    StatusBar read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            title = input.readString()
            barWidth = input.readInt()
            barHeight = input.readInt()
            foreTex = input.readString()
            foreLeftTex = input.readString()
            foreRightTex = input.readString()
            backTex = input.readString()
            backLeftTex = input.readString()
            backRightTex = input.readString()
            regenLeftTex = input.readString()
            regenTex = input.readString()
            regenRightTex = input.readString()
            warnTex = input.readString()
            warnLeftTex = input.readString()
            warnRightTex = input.readString()
            gaugeFontTextureName = input.readString()
            gaugeFontSizeX = input.readInt()
            gaugeFontSizeY = input.readInt()
        }

        this
    }

    @Override
    StatusBar write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeString(title)
            output.writeInt(barWidth)
            output.writeInt(barHeight)
            output.writeString(foreTex)
            output.writeString(foreLeftTex)
            output.writeString(foreRightTex)
            output.writeString(backTex)
            output.writeString(backLeftTex)
            output.writeString(backRightTex)
            output.writeString(regenLeftTex)
            output.writeString(regenTex)
            output.writeString(regenRightTex)
            output.writeString(warnTex)
            output.writeString(warnLeftTex)
            output.writeString(warnRightTex)
            output.writeString(gaugeFontTextureName)
            output.writeInt(gaugeFontSizeX)
            output.writeInt(gaugeFontSizeY)
        }

        this
    }

    @Deprecated String getUnk100() { title }
    @Deprecated void setUnk100(String unk100) { this.title = unk100 }

    @Deprecated int getUnk101() { barWidth }
    @Deprecated void setUnk101(int unk101) { this.barWidth = unk101 }

    @Deprecated int getUnk102() { barHeight }
    @Deprecated void setUnk102(int unk102) { this.barHeight = unk102 }

    @Deprecated String getUnk103() { foreTex }
    @Deprecated void setUnk103(String unk103) { this.foreTex = unk103 }

    @Deprecated String getUnk104() { foreLeftTex }
    @Deprecated void setUnk104(String unk104) { this.foreLeftTex = unk104 }

    @Deprecated String getUnk105() { foreRightTex }
    @Deprecated void setUnk105(String unk105) { this.foreRightTex = unk105 }

    @Deprecated String getUnk106() { backTex }
    @Deprecated void setUnk106(String unk106) { this.backTex = unk106 }

    @Deprecated String getUnk107() { backLeftTex }
    @Deprecated void setUnk107(String unk107) { this.backLeftTex = unk107 }

    @Deprecated String getUnk108() { backRightTex }
    @Deprecated void setUnk108(String unk108) { this.backRightTex = unk108 }

    @Deprecated String getUnk109() { regenLeftTex }
    @Deprecated void setUnk109(String unk109) { this.regenLeftTex = unk109 }

    @Deprecated String getUnk110() { regenTex }
    @Deprecated void setUnk110(String unk110) { this.regenTex = unk110 }

    @Deprecated String getUnk111() { regenRightTex }
    @Deprecated void setUnk111(String unk111) { this.regenRightTex = unk111 }

    @Deprecated String getUnk112() { warnTex }
    @Deprecated void setUnk112(String unk112) { this.warnTex = unk112 }

    @Deprecated String getUnk113() { warnLeftTex }
    @Deprecated void setUnk113(String unk113) { this.warnLeftTex = unk113 }

    @Deprecated String getUnk114() { warnRightTex }
    @Deprecated void setUnk114(String unk114) { this.warnRightTex = unk114 }

    @Deprecated String getUnk117() { gaugeFontTextureName }
    @Deprecated void setUnk117(String unk117) { this.gaugeFontTextureName = unk117 }

    @Deprecated int getUnk118() { gaugeFontSizeX }
    @Deprecated void setUnk118(int unk118) { this.gaugeFontSizeX = unk118 }

    @Deprecated int getUnk119() { gaugeFontSizeY }
    @Deprecated void setUnk119(int unk119) { this.gaugeFontSizeY = unk119 }
}
