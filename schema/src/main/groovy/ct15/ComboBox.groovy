package ct15

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.Type

class ComboBox extends BaseUI {
    @Type(ComboBoxElement.class)
    List<ComboBoxElement> values = []

    static class ComboBoxElement implements IOEntity {
        int sysString = -9999
        int systemMsg = -9999
        String text = 'undefined'
        int reserved = -9999

        @Override
        ComboBoxElement read(InputStream input) {
            use(IOUtil) {
                sysString = input.readInt()
                systemMsg = input.readInt()
                text = input.readString()
                reserved = input.readInt()
            }
            this
        }

        @Override
        ComboBoxElement write(OutputStream output) {
            use(IOUtil) {
                output.writeInt(sysString)
                output.writeInt(systemMsg)
                output.writeString(text)
                output.writeInt(reserved)
            }
            this
        }

        @Override
        String toString() {
            return getClass().simpleName
        }

        @Deprecated int getTextStringId() { sysString }
        @Deprecated void setTextStringId(int textStringId) { this.sysString = textStringId }

        @Deprecated int getUnk102() { systemMsg }
        @Deprecated void setUnk102(int unk102) { this.systemMsg = unk102 }

        @Deprecated String getTextString() { text }
        @Deprecated void setTextString(String textString) { this.text = textString }

        @Deprecated int getUnk104() { reserved }
        @Deprecated void setUnk104(int unk104) { this.reserved = unk104 }
    }

    @Override
    ComboBox read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            int count = input.readInt()
            for (int i = 0; i < count; i++)
                values.add(new ComboBoxElement().read(input))
        }

        this
    }

    @Override
    ComboBox write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeInt(values.size())
            for (ComboBoxElement comboBoxElement : values)
                comboBoxElement.write(output)
        }

        this
    }
}
