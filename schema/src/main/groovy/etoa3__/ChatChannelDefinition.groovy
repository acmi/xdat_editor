package etoa3__

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import javafx.scene.paint.Color

@DefaultIO
@CompileStatic
class ChatChannelDefinition implements IOEntity {
    ChatType chatType = ChatType.NORMAL
    Color chatColor = new Color(0.0, 0.0, 0.0, 0.0)

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
        DOMINIONWAR,
        MPCC_ROOM,
        NPC_NORMAL,
        NPC_SHOUT,
        FRIEND_ANNOUNCE,
        WORLD
    }
}
