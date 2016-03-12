package etoa2_2_ru

import acmi.l2.clientmod.util.Description
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic

@DefaultIO
@CompileStatic
class ChatWindow extends DefaultProperty {
    @Description('Vertical space between text lines')
    int lineGap
    @Description('-9999')
    int nextLineOffsetX = -9999

    @Deprecated int getSpacing() { lineGap }
    @Deprecated void setSpacing(int spacing) { this.lineGap = spacing }

    @Deprecated int getUnk101() { nextLineOffsetX }
    @Deprecated void setUnk101(int unk101) { this.nextLineOffsetX = unk101 }
}
