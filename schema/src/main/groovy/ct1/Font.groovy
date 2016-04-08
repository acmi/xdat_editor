package ct1

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.Type
import acmi.l2.clientmod.util.defaultio.DefaultIO
import javafx.scene.paint.Color

@DefaultIO
class Font implements IOEntity {
    String defaultFontName
    Color defaultColor = new Color(0.0, 0.0, 0.0, 0.0)
    @Type(FontData.class)
    List<FontData> fonts = []

    static class FontData implements IOEntity {
        String name
        String file
        String location = 'windows'
        int size
        int index
        boolean indexOn = false
        boolean shadow = false
        int shadow_x
        int shadow_y
        transient int unused01 //shadow_x
        transient int unused02 //shadow_y
        boolean stroke = false
        boolean stroke_large = false
        transient int unused11 //stroke_large
        int lineGap
        int underlineOffset

        @Override
        FontData read(InputStream input) {
            use(IOUtil) {
                name = input.readString()
                file = input.readString()
                location = input.readString()
                size = input.readInt()
                index = input.readInt()
                indexOn = input.readInt()
                shadow = input.readBoolean()
                if (shadow) {
                    shadow_x = input.readInt()
                    shadow_y = input.readInt()
                } else {
                    unused01 = input.readInt()
                    unused02 = input.readInt()
                }
                stroke = input.readBoolean()
                if (stroke) {
                    stroke_large = input.readBoolean()
                } else {
                    unused11 = input.readInt()
                }
                lineGap = input.readInt()
                underlineOffset = input.readInt()
            }
            this
        }

        @Override
        FontData write(OutputStream output) {
            use(IOUtil) {
                output.writeString(name)
                output.writeString(file)
                output.writeString(location)
                output.writeInt(size)
                output.writeInt(index)
                output.writeBoolean(indexOn)
                output.writeBoolean(shadow)
                if (shadow) {
                    output.writeInt(shadow_x)
                    output.writeInt(shadow_y)
                } else {
                    output.writeInt(unused01)
                    output.writeInt(unused02)
                }
                output.writeBoolean(stroke)
                if (stroke) {
                    output.writeBoolean(stroke_large)
                } else {
                    output.writeInt(unused11)
                }
                output.writeInt(lineGap)
                output.writeInt(underlineOffset)
            }
            this
        }

        @Override
        String toString() {
            return name
        }
    }

    @Override
    String toString() {
        return "FontData"
    }
}
