package ct22

import acmi.l2.clientmod.util.UIEntity
import acmi.l2.clientmod.util.IOUtil
import javafx.scene.paint.Color

abstract class DefaultProperty implements UIEntity {
    String name
    String superName = "undefined"
    int unk2 = -1
    int unk3 = -1
    String unk4
    String unk5 = "undefined"
    String unk6 = "undefined"
    int unk7 = -1
    boolean size = false
    boolean size_absolute_values = true
    float size_percent_width
    float size_percent_height
    int size_absolute_width
    int size_absolute_height
    boolean anchor = false
    Alignment anchor_parent = Alignment.NONE
    Alignment anchor_this = Alignment.NONE
    String anchor_ctrl
    float anchor_x
    float anchor_y
    boolean useFont = false
    String text_font_1 = "undefined"
    String text_font_2 = "undefined"
    Color text_color = new Color(0.0, 0.0, 0.0, 0.0)
    int text_unk_const_zero = 0
    int unk23 = -1
    int unk24 = 0
    String popupType = "undefined"
    int popupValue = -9999
    int unk27 = -9999
    int unk28 = -9999
    int unk29 = -9999

    @Override
    DefaultProperty read(InputStream input) {
        use(IOUtil) {
            name = input.readString()
            superName = input.readString()
            unk2 = input.readInt()
            unk3 = input.readInt()
            unk4 = input.readString()
            unk5 = input.readString()
            unk6 = input.readString()
            unk7 = input.readInt()
            size = input.readBoolean()
            if (size) {
                size_absolute_values = input.readBoolean()
                if (!size_absolute_values) {
                    input.readCompactInt()                 //TODO unk empty array
                    size_percent_width = input.readFloat()
                    size_percent_height = input.readFloat()
                }
                size_absolute_width = input.readInt()
                size_absolute_height = input.readInt()
            }
            anchor = input.readBoolean()
            if (anchor) {
                anchor_parent = Alignment.values()[input.readInt()]
                anchor_this = Alignment.values()[input.readInt()]
                anchor_ctrl = input.readString()
                anchor_x = input.readFloat()
                anchor_y = input.readFloat()
            }
            useFont = input.readBoolean()
            if (useFont) {
                text_font_1 = input.readString()
                text_font_2 = input.readString()
                text_color = input.readColor()
                text_unk_const_zero = input.readInt()
            }
            unk23 = input.readInt()
            unk24 = input.readInt()
            popupType = input.readString()
            popupValue = input.readInt()
            unk27 = input.readInt()
            unk28 = input.readInt()
            unk29 = input.readInt()
        }

        this
    }

    @Override
    DefaultProperty write(OutputStream output) {
        use(IOUtil) {
            output.writeString(name)
            output.writeString(superName)
            output.writeInt(unk2)
            output.writeInt(unk3)
            output.writeString(unk4)
            output.writeString(unk5)
            output.writeString(unk6)
            output.writeInt(unk7)
            output.writeBoolean(size)
            if (size) {
                output.writeBoolean(size_absolute_values)
                if (!size_absolute_values) {
                    output.writeCompactInt(0)                    //TODO unk empty array
                    output.writeFloat(size_percent_width)
                    output.writeFloat(size_percent_height)
                }
                output.writeInt(size_absolute_width)
                output.writeInt(size_absolute_height)
            }
            output.writeBoolean(anchor)
            if (anchor) {
                output.writeInt(anchor_parent.ordinal())
                output.writeInt(anchor_this.ordinal())
                output.writeString(anchor_ctrl)
                output.writeFloat(anchor_x)
                output.writeFloat(anchor_y)
            }
            output.writeBoolean(useFont)
            if (useFont) {
                output.writeString(text_font_1)
                output.writeString(text_font_2)
                output.writeColor(text_color)
                output.writeInt(text_unk_const_zero)
            }
            output.writeInt(unk23)
            output.writeInt(unk24)
            output.writeString(popupType)
            output.writeInt(popupValue)
            output.writeInt(unk27)
            output.writeInt(unk28)
            output.writeInt(unk29)
        }

        this
    }

    @Override
    String toString() {
        name + "[" + getClass().simpleName + "]"
    }
}
