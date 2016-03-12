package ct15

import acmi.l2.clientmod.util.IOUtil

class ShortcutItemWindow extends DefaultProperty {
    boolean alwaysShowOutline

    @Override
    ShortcutItemWindow read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            alwaysShowOutline = input.readBoolean()
        }

        this
    }

    @Override
    ShortcutItemWindow write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeBoolean(alwaysShowOutline)
        }

        this
    }

    @Deprecated boolean getUnk100() { alwaysShowOutline }
    @Deprecated void setUnk100(boolean unk100) { this.alwaysShowOutline = unk100 }
}
