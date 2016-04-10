package ct0

import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
@CompileStatic
class EditBox extends DefaultProperty {
    Type type = Type.NORMAL
    int maxLength
    int showCursor = -1
    boolean chatMarkOn

    enum Type {
        NORMAL,
        CHAT,
        PASSWORD,
        COUNT,    //number with delimiters
        NUMBER,
        UNK5,
        TYPE6,
    }

    // @formatter:off
    @Deprecated int getUnk102() { showCursor }
    @Deprecated void setUnk102(int unk102) { this.showCursor = unk102 }

    @Deprecated boolean getUnk103() { chatMarkOn }
    @Deprecated void setUnk103(boolean unk103) { this.chatMarkOn = unk103 }
    // @formatter:on
}
