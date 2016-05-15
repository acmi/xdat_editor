package etoa4

import acmi.l2.clientmod.l2resources.Sysstr
import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.Type
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
class ComboBox extends DefaultProperty {
    @Type(ComboBoxElement.class)
    List<ComboBoxElement> values = []

    @FXBindable
    @DefaultIO
    static class ComboBoxElement implements IOEntity {
        @Sysstr
        int sysString = -9999
        int systemMsg = -9999
        String text = 'undefined'
        int reserved = -9999

        @Override
        String toString() {
            return getClass().simpleName
        }

        // @formatter:off
        @Deprecated int getTextStringId() { sysString }
        @Deprecated void setTextStringId(int textStringId) { this.sysString = textStringId }

        @Deprecated int getUnk102() { systemMsg }
        @Deprecated void setUnk102(int unk102) { this.systemMsg = unk102 }

        @Deprecated String getTextString() { text }
        @Deprecated void setTextString(String textString) { this.text = textString }

        @Deprecated int getUnk104() { reserved }
        @Deprecated void setUnk104(int unk104) { this.reserved = unk104 }
        // @formatter:on
    }
}
