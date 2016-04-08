package ct1

import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import javafx.scene.paint.Color

@DefaultIO
@CompileStatic
class TextBox extends DefaultProperty {
    String text
    TextAlign textAlign = TextAlign.Undefined
    TextVAlign textVAlign = TextVAlign.Undefined
    int fontType
    String backTex
    int sysstring
    int systemMsg
    Color textColor = new Color(0.0, 0.0, 0.0, 0.0)
    int emoticon
    int autosize

    enum TextAlign {
        Undefined,
        Left,
        Center,
        Right,
        MacroIcon,
    }

    enum TextVAlign {
        Undefined,
        Top,
        Middle,
        Bottom,
    }

    // @formatter:off
    @Deprecated String getUnk100() { text }
    @Deprecated void setUnk100(String unk100) { this.text = unk100 }

    @Deprecated int getUnk101() { textAlign.ordinal() }
    @Deprecated void setUnk101(int unk101) { this.textAlign = TextAlign.values()[unk101] }

    @Deprecated int getUnk102() { textVAlign.ordinal() }
    @Deprecated void setUnk102(int unk102) { this.textVAlign = TextVAlign.values()[unk102] }

    @Deprecated int getUnk103() { fontType }
    @Deprecated void setUnk103(int unk103) { this.fontType = unk103 }

    @Deprecated String getUnk104() { backTex }
    @Deprecated void setUnk104(String unk104) { this.backTex = unk104 }

    @Deprecated int getUnk105() { sysstring }
    @Deprecated void setUnk105(int unk105) { this.sysstring = unk105 }

    @Deprecated int getUnk106() { systemMsg }
    @Deprecated void setUnk106(int unk106) { this.systemMsg = unk106 }

    @Deprecated Color getUnk107() { textColor }
    @Deprecated void setUnk107(Color unk107) { this.textColor = unk107 }

    @Deprecated boolean getUnk108() { emoticon > 0 }
    @Deprecated void setUnk108(boolean unk108) { this.emoticon = unk108 ? 1 : 0 }

    @Deprecated int getUnk109() { autosize }
    @Deprecated void setUnk109(int unk109) { this.autosize = unk109 }
    // @formatter:on
}
