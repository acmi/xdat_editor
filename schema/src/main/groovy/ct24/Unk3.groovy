package ct24

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.IOUtil
import javafx.scene.paint.Color

class Unk3 implements IOEntity {
    ChatType chatType = ChatType.NORMAL
    Color chatColor = new Color(0.0, 0.0, 0.0, 0.0)

    @Override
    Unk3 read(InputStream input) {
        use(IOUtil) {
            chatType = ChatType.values()[input.readInt()]
            chatColor = input.readColor()
        }
        this
    }

    @Override
    Unk3 write(OutputStream output) {
        use(IOUtil) {
            output.writeInt(chatType.ordinal())
            output.writeColor(chatColor)
        }
        this
    }

    @Override
    String toString() {
        chatType.name()
    }

    enum ChatType {
        NORMAL,
        SHOUT,
        TELL,
        PARTY,
        CLAN,
        SYSTEM,
        USER_PET,
        GM_PET,
        MARKET,
        ALLIANCE,
        ANNOUNCE,
        CUSTOM,
        L2_FRIEND,
        MSN_CHAT,
        PARTY_ROOM_CHAT,
        COMMANDER_CHAT,
        INTER_PARTYMASTER_CHAT,
        HERO,
        CRITICAL_ANNOUNCE,
        SCREEN_ANNOUNCE,
        DOMINIONWAR
    }
}
