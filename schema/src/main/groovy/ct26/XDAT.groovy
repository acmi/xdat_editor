package ct26

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.SubclassManager
import acmi.l2.clientmod.util.Type

class XDAT implements IOEntity {
    @Type(Shortcut)
    List<Shortcut> shortcuts = []
    @Type(Window)
    List<Window> windows = []
    @Type(WndDefPos)
    List<WndDefPos> wndDefPos = []
    @Type(Font)
    List<Font> fonts = []
    @Type(Style)
    List<Style> styles = []
    @Type(ChatChannelDefinition)
    List<ChatChannelDefinition> chatChannel = []
    private byte[] tail

    @Override
    XDAT read(InputStream input) {
        use(IOUtil) {
            shortcuts = input.readList(Shortcut)
            int count = input.readInt()
            for (int i = 0; i < count; i++)
                windows.add(new Window().read(input))
            input.readInt()
            wndDefPos = input.readList(WndDefPos)
            fonts = input.readList(Font)
            styles = input.readList(Style)
            chatChannel = input.readList(ChatChannelDefinition)
            try {
                byte[] tmp = new byte[20]
                new DataInputStream(input).readFully(tmp)
                tail = tmp
            } catch (EOFException ignore) {
            }
        }

        this
    }

    @Override
    XDAT write(OutputStream output) {
        use(IOUtil) {
            output.writeList(shortcuts)
            output.writeInt(windows.size())
            for (Window window : windows)
                output.writeIOEntity(window)
            output.writeInt(1)
            output.writeList(wndDefPos)
            output.writeList(fonts)
            output.writeList(styles)
            output.writeList(chatChannel)
            if (tail != null)
                output.write(tail)
        }

        this
    }

    static {
        SubclassManager.instance.registerClass(BarCtrl)
        SubclassManager.instance.registerClass(Button)
        SubclassManager.instance.registerClass(CharacterViewportWindow)
        SubclassManager.instance.registerClass(ChatWindow)
        SubclassManager.instance.registerClass(CheckBox)
        SubclassManager.instance.registerClass(ComboBox)
        SubclassManager.instance.registerClass(DrawPanel)
        SubclassManager.instance.registerClass(EditBox)
        SubclassManager.instance.registerClass(EffectButton)
        SubclassManager.instance.registerClass(FishViewportWindow)
        SubclassManager.instance.registerClass(FlashCtrl)
        SubclassManager.instance.registerClass(HtmlCtrl)
        SubclassManager.instance.registerClass(InvenWeight)
        SubclassManager.instance.registerClass(ItemWindow)
        SubclassManager.instance.registerClass(ListBox)
        SubclassManager.instance.registerClass(ListCtrl)
        SubclassManager.instance.registerClass(MinimapCtrl)
        SubclassManager.instance.registerClass(MoviePlayerCtrl)
        SubclassManager.instance.registerClass(MultiEdit)
        SubclassManager.instance.registerClass(MultiSellItemInfo)
        SubclassManager.instance.registerClass(MultiSellNeededItem)
        SubclassManager.instance.registerClass(NameCtrl)
        SubclassManager.instance.registerClass(Progress)
        SubclassManager.instance.registerClass(PropertyController)
        SubclassManager.instance.registerClass(Radar)
        SubclassManager.instance.registerClass(RadarMapCtrl)
        SubclassManager.instance.registerClass(RadioButton)
        //SubclassManager.instance.registerClass(SceneCameraCtrl)
        //SubclassManager.instance.registerClass(SceneMusicCtrl)
        //SubclassManager.instance.registerClass(SceneNpcCtrl)
        //SubclassManager.instance.registerClass(ScenePcCtrl)
        //SubclassManager.instance.registerClass(SceneScreenCtrl)
        SubclassManager.instance.registerClass(ScrollArea)
        SubclassManager.instance.registerClass(ShortcutItemWindow)
        SubclassManager.instance.registerClass(SliderCtrl)
        SubclassManager.instance.registerClass(StatusBar)
        SubclassManager.instance.registerClass(StatusIconCtrl)
        SubclassManager.instance.registerClass(Tab)
        SubclassManager.instance.registerClass(TextBox)
        SubclassManager.instance.registerClass(TextListBox)
        SubclassManager.instance.registerClass(Texture)
        SubclassManager.instance.registerClass(TreeCtrl)
        SubclassManager.instance.registerClass(Window)
    }
}
