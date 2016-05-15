package etoa4

import acmi.l2.clientmod.l2resources.Tex
import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.Type
import acmi.l2.clientmod.util.defaultio.DefaultIO
import javafx.scene.paint.Color

@DefaultIO
class HeadDisplayDefinition implements IOEntity {
    @Type(HeadDisplayElement.class)
    List<HeadDisplayElement> headDisplayElements = []
    HeadDisplayLayout headDisplayLayout

    @DefaultIO
    static class HeadDisplayElement implements IOEntity {
        int typeName
        @Type(Element.class)
        List<Element> elements = []

        @Override
        String toString() { typeName }

        static class Element implements IOEntity {
            int reserved
            DrawType drawType = DrawType.TextType
            @Tex
            String texName = ""
            int u1
            int v1
            int u2
            int v2
            int systemMsg
            Color color

            @Override
            Element read(InputStream input) throws IOException {
                use(IOUtil) {
                    reserved = input.readInt()
                    drawType = input.readEnum(DrawType)
                    if (drawType == DrawType.ImageType) {
                        texName = input.readString()
                        u1 = input.readInt()
                        v1 = input.readInt()
                        u2 = input.readInt()
                        v2 = input.readInt()
                    } else {
                        systemMsg = input.readInt()
                        color = input.readColor()
                    }
                }
                this
            }

            @Override
            Element write(OutputStream output) throws IOException {
                use(IOUtil) {
                    output.writeInt(reserved)
                    output.writeEnum(drawType)
                    if (drawType == DrawType.ImageType) {
                        output.writeString(texName)
                        output.writeInt(u1)
                        output.writeInt(v1)
                        output.writeInt(u2)
                        output.writeInt(v2)
                    } else {
                        output.writeInt(systemMsg)
                        output.writeColor(color)
                    }
                }
                this
            }

            @Override
            String toString() { "Element" }

            static enum DrawType {
                TextType,
                ImageType
            }
        }
    }

    @DefaultIO
    static class HeadDisplayLayout implements IOEntity {
        int offsetX
        int offsetY
        int startingPoint
        @Type(HeadDisplayCell.class)
        List<HeadDisplayCell> cells = []

        @Override
        String toString() { "Layout" }

        @DefaultIO
        static class HeadDisplayCell implements IOEntity {
            int maxHeight
            @Type(Row.class)
            List<Row> rows = []

            @Override
            String toString() { "Cell" }

            @DefaultIO
            static class Row implements IOEntity {
                int typeName
                int reservedName
                int width
                int height
                int offsetX
                int offsetY

                @Override
                String toString() { "Row[$typeName]" }
            }
        }
    }

    @Override
    String toString() { HeadDisplayDefinition.simpleName }
}
