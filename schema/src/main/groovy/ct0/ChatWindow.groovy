package ct0

import acmi.l2.clientmod.util.Description
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic

@DefaultIO
@CompileStatic
class ChatWindow extends DefaultProperty {
    @Description('Vertical space between text lines')
    int lineGap

    @Deprecated int getSpacing() { lineGap }
    @Deprecated void setSpacing(int spacing) { this.lineGap = spacing }
}
