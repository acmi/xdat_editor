package ct26

import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
@CompileStatic
class EditBox extends DefaultProperty {
    Type type = Type.NORMAL
    int maxLength
    Boolean showCursor
    Boolean chatMarkOn
    int offsetX = -9999
    Boolean candidateBoxShowUpPos
    AutoCompletionType autoCompletionType = AutoCompletionType.NotUsed

    enum Type {
        NORMAL,
        CHAT,
        PASSWORD,
        NUMBER,
        DATE,
        TIME,
        ID
    }

    enum AutoCompletionType {
        NotUsed,
        Normal,
        PostBox;

        static AutoCompletionType valueOf(int val) {
            Optional.ofNullable(values().find { it.ordinal() == val })
                    .orElseThrow({
                new IllegalArgumentException("No ${getClass().simpleName} constant with value=$val")
            })
        }
    }

    // @formatter:off
    @Deprecated int getUnk102() { IOUtil.boolToInt(showCursor) }
    @Deprecated void setUnk102(int unk102) { this.showCursor = IOUtil.intToBool(unk102) }

    @Deprecated boolean getUnk103() { chatMarkOn }
    @Deprecated void setUnk103(boolean unk103) { this.chatMarkOn = unk103 }

    @Deprecated int getUnk104() { offsetX }
    @Deprecated void setUnk104(int unk104) { this.offsetX = unk104 }

    @Deprecated boolean getUnk105() { candidateBoxShowUpPos }
    @Deprecated void setUnk105(boolean unk105) { this.candidateBoxShowUpPos = unk105 }

    @Deprecated int getUnk106() { autoCompletionType.ordinal() }
    @Deprecated void setUnk106(int unk106) { this.autoCompletionType = AutoCompletionType.valueOf(unk106) }
    // @formatter:on
}
