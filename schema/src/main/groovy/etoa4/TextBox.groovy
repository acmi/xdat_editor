package etoa4

import acmi.l2.clientmod.l2resources.Sysstr
import acmi.l2.clientmod.l2resources.Tex
import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.IntValue
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import groovyx.javafx.beans.FXBindable
import javafx.scene.paint.Color

@FXBindable
@DefaultIO
@CompileStatic
class TextBox extends DefaultProperty {
    String text = 'undefined'
    TextAlign textAlign = TextAlign.Undefined
    TextVAlign textVAlign = TextVAlign.Undefined
    FontType fontType = FontType.Normal
    @Tex
    String backTex = 'undefined'
    @Sysstr
    int sysstring = -9999
    int systemMsg = -9999
    Color textColor = new Color(0.0, 0.0, 0.0, 0.0)
    Boolean emoticon
    Boolean autosize

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

    enum FontType implements IntValue{
        Normal(-1),
        SpecialDigitSmall(0),
        SpecialDigitLarge(1),
        SpecialDigitXLarge(2);

        final int value

        FontType(int value) {this.value = value}

        @Override
        int intValue() { value }

        static FontType valueOf(int val){
            Optional.ofNullable(values().find { it.value == val })
                    .orElseThrow({ new IllegalArgumentException("No ${getClass().simpleName} constant with value=$val") })
        }
    }

    // @formatter:off
    @Deprecated String getUnk100() { text }
    @Deprecated void setUnk100(String unk100) { this.text = unk100 }

    @Deprecated int getUnk101() { textAlign.ordinal() }
    @Deprecated void setUnk101(int unk101) { this.textAlign = TextAlign.values()[unk101] }

    @Deprecated int getUnk102() { textVAlign.ordinal() }
    @Deprecated void setUnk102(int unk102) { this.textVAlign = TextVAlign.values()[unk102] }

    @Deprecated int getUnk103() { fontType.intValue() }
    @Deprecated void setUnk103(int unk103) { this.fontType = FontType.valueOf(unk103) }

    @Deprecated String getUnk104() { backTex }
    @Deprecated void setUnk104(String unk104) { this.backTex = unk104 }

    @Deprecated int getUnk105() { sysstring }
    @Deprecated void setUnk105(int unk105) { this.sysstring = unk105 }

    @Deprecated int getUnk106() { systemMsg }
    @Deprecated void setUnk106(int unk106) { this.systemMsg = unk106 }

    @Deprecated Color getUnk107() { textColor }
    @Deprecated void setUnk107(Color unk107) { this.textColor = unk107 }

    @Deprecated boolean getUnk108() { emoticon }
    @Deprecated void setUnk108(boolean unk108) { this.emoticon = unk108 }

    @Deprecated int getUnk109() { IOUtil.boolToInt(autosize) }
    @Deprecated void setUnk109(int unk109) { this.autosize = IOUtil.intToBool(unk109) }
    // @formatter:on
}
