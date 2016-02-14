package god3

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.SubclassManager
import acmi.l2.clientmod.util.Type

class XDAT implements IOEntity {
    @Type(Shortcut.class)
    final List<Shortcut> shortcuts = []
    @Type(Window.class)
    final List<Window> windows = []
    @Type(WndDefPos.class)
    final List<WndDefPos> wndDefPos = []
    @Type(Unk2.class)
    final List<Unk2> unk2 = []
    @Type(Font.class)
    final List<Font> fonts = []
    @Type(Unk3.class)
    final List<Unk3> chatColors = []
    private ByteArrayOutputStream unk = new ByteArrayOutputStream()

    @Override
    XDAT read(InputStream input) throws IOException {
        use(IOUtil) {
            int count = input.readInt()
            for (int i = 0; i < count; i++)
                shortcuts.add(new Shortcut().read(input))
            count = input.readInt()
            for (int i = 0; i < count; i++)
                windows.add(new Window().read(input))
            input.readInt()
            count = input.readInt()
            for (int i = 0; i < count; i++)
                wndDefPos.add(new WndDefPos().read(input))
            count = input.readInt()
            for (int i = 0; i < count; i++)
                unk2.add(new Unk2().read(input))
            count = input.readInt()
            for (int i = 0; i < count; i++)
                fonts.add(new Font().read(input))
            input.readInt()
            count = input.readInt()
            for (int i = 0; i < count; i++)
                chatColors.add(new Unk3().read(input))
            int b
            while ((b = input.read()) >= 0)
                unk.write(b)
        }
        this
    }

    @Override
    XDAT write(OutputStream output) throws IOException {
        use(IOUtil) {
            output.writeInt(shortcuts.size())
            for (Shortcut shortcut : shortcuts)
                shortcut.write(output)
            output.writeInt(windows.size())
            for (Window window : windows)
                window.write(output)
            output.writeInt(1)
            output.writeInt(wndDefPos.size())
            for (WndDefPos unk11 : wndDefPos)
                unk11.write(output)
            output.writeInt(unk2.size())
            for (Unk2 unk21 : unk2)
                unk21.write(output)
            output.writeInt(fonts.size())
            for (Font font : fonts)
                font.write(output)
            output.writeInt(0)
            output.writeInt(chatColors.size())
            for (Unk3 unk31 : chatColors)
                unk31.write(output)
            unk.writeTo(output)
        }
        this
    }

    static {
        SubclassManager.instance.registerClass(BarCtrl.class)
        SubclassManager.instance.registerClass(BarCtrl.class)
        SubclassManager.instance.registerClass(Button.class)
        SubclassManager.instance.registerClass(CharacterViewportWindow.class)
        SubclassManager.instance.registerClass(ChatWindow.class)
        SubclassManager.instance.registerClass(CheckBox.class)
        SubclassManager.instance.registerClass(ComboBox.class)
        SubclassManager.instance.registerClass(DrawPanel.class)
        SubclassManager.instance.registerClass(EditBox.class)
        SubclassManager.instance.registerClass(EffectButton.class)
        SubclassManager.instance.registerClass(FishViewportWindow.class)
        SubclassManager.instance.registerClass(HtmlCtrl.class)
        SubclassManager.instance.registerClass(InvenWeight.class)
        SubclassManager.instance.registerClass(ItemWindow.class)
        SubclassManager.instance.registerClass(ListBox.class)
        SubclassManager.instance.registerClass(ListCtrl.class)
        SubclassManager.instance.registerClass(MinimapCtrl.class)
        SubclassManager.instance.registerClass(MultiEdit.class)
        SubclassManager.instance.registerClass(MultiSellItemInfo.class)
        SubclassManager.instance.registerClass(MultiSellNeededItem.class)
        SubclassManager.instance.registerClass(NameCtrl.class)
        SubclassManager.instance.registerClass(Progress.class)
        SubclassManager.instance.registerClass(PropertyController.class)
        SubclassManager.instance.registerClass(RadarMapCtrl.class)
        SubclassManager.instance.registerClass(RadioButton.class)
        SubclassManager.instance.registerClass(ScrollArea.class)
        SubclassManager.instance.registerClass(ShortcutItemWindow.class)
        SubclassManager.instance.registerClass(SliderCtrl.class)
        SubclassManager.instance.registerClass(StatusBar.class)
        SubclassManager.instance.registerClass(StatusIconCtrl.class)
        SubclassManager.instance.registerClass(Tab.class)
        SubclassManager.instance.registerClass(TextBox.class)
        SubclassManager.instance.registerClass(TextListBox.class)
        SubclassManager.instance.registerClass(Texture.class)
        SubclassManager.instance.registerClass(TreeCtrl.class)
        SubclassManager.instance.registerClass(WebBrowserWnd.class)
        SubclassManager.instance.registerClass(Window.class)
    }
}
