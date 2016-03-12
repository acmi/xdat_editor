package ct22

import acmi.l2.clientmod.util.IOUtil

class RadioButton extends DefaultProperty {
    int sysstring
    int radioGroupID
    int isChecked

    @Override
    RadioButton read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            sysstring = input.readInt()
            radioGroupID = input.readInt()
            isChecked = input.readInt()
        }

        this
    }

    @Override
    RadioButton write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeInt(sysstring)
            output.writeInt(radioGroupID)
            output.writeInt(isChecked)
        }

        this
    }

    @Deprecated int getUnk100() { sysstring }
    @Deprecated void setUnk100(int unk100) { this.sysstring = unk100 }

    @Deprecated int getUnk101() { radioGroupID }
    @Deprecated void setUnk101(int unk101) { this.radioGroupID = unk101 }

    @Deprecated int getUnk102() { isChecked }
    @Deprecated void setUnk102(int unk102) { this.isChecked = unk102 }
}
