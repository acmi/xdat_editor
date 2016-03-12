package ct24

import acmi.l2.clientmod.util.IOUtil

class ShortcutItemWindow extends DefaultProperty {
    boolean alwaysShowOutline
    boolean useReservedShortcut

    @Override
    ShortcutItemWindow read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            alwaysShowOutline = input.readBoolean()
            useReservedShortcut = input.readBoolean()
        }

        this
    }

    @Override
    ShortcutItemWindow write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeBoolean(alwaysShowOutline)
            output.writeBoolean(useReservedShortcut)
        }

        this
    }

    @Deprecated boolean getUnk100() { alwaysShowOutline }
    @Deprecated void setUnk100(boolean unk100) { this.alwaysShowOutline = unk100 }

    @Deprecated  boolean getUnk101() { useReservedShortcut }
    @Deprecated void setUnk101(boolean unk101) { this.useReservedShortcut = unk101 }
}
