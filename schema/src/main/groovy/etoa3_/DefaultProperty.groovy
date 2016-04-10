package etoa3_

import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.l2resources.Sysstr
import acmi.l2.clientmod.util.UIEntity
import groovyx.javafx.beans.FXBindable
import javafx.scene.paint.Color

@FXBindable
abstract class DefaultProperty implements UIEntity {
    String name
    String extendsName = "undefined"
    Boolean alwaysOnTop
    Boolean alwaysOnBack
    Boolean equalToParentSize
    String parentName
    String preOrder = "undefined"
    String postOrder = "undefined"
    Boolean anchored
    Boolean relativeDrag
    Boolean superChild
    boolean size = false
    boolean size_absolute_values = true
    String size_percent_window
    float size_percent_width
    float size_percent_height
    int size_absolute_width
    int size_absolute_height
    Boolean usePosition = false
    Alignment relativePoint = Alignment.NONE
    Alignment anchorPoint = Alignment.NONE
    String relativeTo
    float anchor_x
    float anchor_y
    boolean useFont = false
    String styleName = "undefined"
    String fontName = "undefined"
    Color fontColor = new Color(0.0, 0.0, 0.0, 0.0)
    FontLineType fontLine = FontLineType.EMPTY
    Boolean virtual
    int unk24 = 0
    String tooltipType = "undefined"
    @Sysstr
    int tooltipText = -9999
    int tooltipAppearanceTime = -9999
    int scrollBarOffsetX = -9999
    int scrollBarOffsetY = -9999
    int scrollBarOffsetHeight = -9999

    enum FontLineType {
        EMPTY,
        NONE,
        UNDERLINE,
        LINE_THROUGH

        static FontLineType valueOf(int val){
            Optional.ofNullable(values().find { it.ordinal() == val })
                    .orElseThrow({ new IllegalArgumentException("No ${getClass().simpleName} constant with value=$val") })
        }
    }

    @Override
    DefaultProperty read(InputStream input) {
        use(IOUtil) {
            name = input.readString()
            extendsName = input.readString()
            alwaysOnTop = input.readBoolean()
            alwaysOnBack = input.readBoolean()
            equalToParentSize = input.readBoolean()
            parentName = input.readString()
            preOrder = input.readString()
            postOrder = input.readString()
            anchored = input.readBoolean()
            relativeDrag = input.readBoolean()
            superChild = input.readBoolean()
            size = input.readBoolean()
            if (size) {
                size_absolute_values = input.readBoolean()
                if (!size_absolute_values) {
                    size_percent_window = input.readString()
                    size_percent_width = input.readFloat()
                    size_percent_height = input.readFloat()
                }
                size_absolute_width = input.readInt()
                size_absolute_height = input.readInt()
            }
            usePosition = input.readBoolean()
            if (usePosition) {
                relativePoint = input.readEnum(Alignment)
                anchorPoint = input.readEnum(Alignment)
                relativeTo = input.readString()
                anchor_x = input.readFloat()
                anchor_y = input.readFloat()
            }
            useFont = input.readBoolean()
            if (useFont) {
                styleName = input.readString()
                fontName = input.readString()
                fontColor = input.readColor()
                fontLine = input.readEnum(FontLineType)
            }
            virtual = input.readBoolean()
            unk24 = input.readInt()
            tooltipType = input.readString()
            tooltipText = input.readInt()
            tooltipAppearanceTime = input.readInt()
            scrollBarOffsetX = input.readInt()
            scrollBarOffsetY = input.readInt()
            scrollBarOffsetHeight = input.readInt()
        }

        this
    }

    @Override
    DefaultProperty write(OutputStream output) {
        use(IOUtil) {
            output.writeString(name)
            output.writeString(extendsName)
            output.writeBoolean(alwaysOnTop)
            output.writeBoolean(alwaysOnBack)
            output.writeBoolean(equalToParentSize)
            output.writeString(parentName)
            output.writeString(preOrder)
            output.writeString(postOrder)
            output.writeBoolean(anchored)
            output.writeBoolean(relativeDrag)
            output.writeBoolean(superChild)
            output.writeBoolean(size)
            if (size) {
                output.writeBoolean(size_absolute_values)
                if (!size_absolute_values) {
                    output.writeString(size_percent_window)
                    output.writeFloat(size_percent_width)
                    output.writeFloat(size_percent_height)
                }
                output.writeInt(size_absolute_width)
                output.writeInt(size_absolute_height)
            }
            output.writeBoolean(usePosition)
            if (usePosition) {
                output.writeEnum(relativePoint)
                output.writeEnum(anchorPoint)
                output.writeString(relativeTo)
                output.writeFloat(anchor_x)
                output.writeFloat(anchor_y)
            }
            output.writeBoolean(useFont)
            if (useFont) {
                output.writeString(styleName)
                output.writeString(fontName)
                output.writeColor(fontColor)
                output.writeEnum(fontLine)
            }
            output.writeBoolean(virtual)
            output.writeInt(unk24)
            output.writeString(tooltipType)
            output.writeInt(tooltipText)
            output.writeInt(tooltipAppearanceTime)
            output.writeInt(scrollBarOffsetX)
            output.writeInt(scrollBarOffsetY)
            output.writeInt(scrollBarOffsetHeight)
        }

        this
    }

    @Override
    String toString() {
        name + "[" + getClass().simpleName + "]"
    }

    @Deprecated String getSuperName() { extendsName }
    @Deprecated void setSuperName(String superName) { this.extendsName = superName }

    @Deprecated int getUnk2() { IOUtil.boolToInt(alwaysOnTop) }
    @Deprecated void setUnk2(int unk2) { this.alwaysOnTop = IOUtil.intToBool(unk2) }

    @Deprecated int getUnk3() { IOUtil.boolToInt(alwaysOnBack) }
    @Deprecated void setUnk3(int unk3) { this.alwaysOnBack = IOUtil.intToBool(unk3) }

    @Deprecated int getUnk31() { IOUtil.boolToInt(equalToParentSize) }
    @Deprecated void setUnk31(int unk31) { this.equalToParentSize = IOUtil.intToBool(unk31) }

    @Deprecated String getUnk4() { parentName }
    @Deprecated void setUnk4(String unk4) { this.parentName = unk4 }

    @Deprecated String getUnk5() { preOrder }
    @Deprecated void setUnk5(String unk5) { this.preOrder = unk5 }

    @Deprecated String getUnk6() { postOrder }
    @Deprecated void setUnk6(String unk6) { this.postOrder = unk6 }

    @Deprecated int getUnk6_() { IOUtil.boolToInt(anchored) }
    @Deprecated void setUnk6_(int unk6_) { this.anchored = IOUtil.intToBool(unk6_) }

    @Deprecated int getUnk7() { IOUtil.boolToInt(relativeDrag) }
    @Deprecated void setUnk7(int unk7) { this.relativeDrag = IOUtil.intToBool(unk7) }

    @Deprecated int getUnk8() { IOUtil.boolToInt(superChild) }
    @Deprecated void setUnk8(int unk8) { this.superChild = IOUtil.intToBool(unk8) }

    @Deprecated boolean getAnchor() { usePosition }
    @Deprecated void setAnchor(boolean anchor) { this.usePosition = anchor }

    @Deprecated Alignment getAnchor_parent() { relativePoint }
    @Deprecated void setAnchor_parent(Alignment anchor_parent) { this.relativePoint = anchor_parent }

    @Deprecated Alignment getAnchor_this() { anchorPoint }
    @Deprecated void setAnchor_this(Alignment anchor_this) { this.anchorPoint = anchor_this }

    @Deprecated String getAnchor_ctrl() { relativeTo }
    @Deprecated void setAnchor_ctrl(String anchor_ctrl) { this.relativeTo = anchor_ctrl }

    @Deprecated String getText_font_1() { styleName }
    @Deprecated void setText_font_1(String text_font_1) { this.styleName = text_font_1 }

    @Deprecated String getText_font_2() { fontName }
    @Deprecated void setText_font_2(String text_font_2) { this.fontName = text_font_2 }

    @Deprecated Color getText_color() { fontColor }
    @Deprecated void setText_color(Color text_color) { this.fontColor = text_color }

    @Deprecated int getText_unk_const_zero() { fontLine.ordinal() }
    @Deprecated void setText_unk_const_zero(int text_unk_const_zero) { this.fontLine = FontLineType.valueOf(text_unk_const_zero) }

    @Deprecated int getUnk23() { IOUtil.boolToInt(virtual) }
    @Deprecated void setUnk23(int unk23) { this.virtual = IOUtil.intToBool(unk23) }

    @Deprecated String getPopupType() { tooltipType }
    @Deprecated void setPopupType(String popupType) { this.tooltipType = popupType }

    @Deprecated int getPopupValue() { tooltipText }
    @Deprecated void setPopupValue(int popupValue) { this.tooltipText = popupValue }

    @Deprecated int getUnk27() { tooltipAppearanceTime }
    @Deprecated void setUnk27(int unk27) { this.tooltipAppearanceTime = unk27 }

    @Deprecated int getUnk28() { scrollBarOffsetX }
    @Deprecated void setUnk28(int unk28) { this.scrollBarOffsetX = unk28 }

    @Deprecated int getUnk29() { scrollBarOffsetY }
    @Deprecated void setUnk29(int unk29) { this.scrollBarOffsetY = unk29 }

    @Deprecated int getUnk30() { scrollBarOffsetHeight }
    @Deprecated void setUnk30(int unk30) { this.scrollBarOffsetHeight = unk30 }
}
