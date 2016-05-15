package etoa4

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import javafx.scene.paint.Color

@DefaultIO
@CompileStatic
class Style implements IOEntity {
    String name = "Style@${String.format("%08x", hashCode())}"
    String fontName
    String line
    Color color = new Color(0.0, 0.0, 0.0, 0.0)

    @Override
    String toString() { name }
}
