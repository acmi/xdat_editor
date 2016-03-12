package god25

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.SubclassManager
import acmi.l2.clientmod.util.Type

class XDAT implements IOEntity {
    @Type(Shortcut.class)
    List<Shortcut> shortcuts = []
    @Type(Window.class)
    List<Window> windows = []
    @Type(WndDefPos.class)
    List<WndDefPos> wndDefPos = []
    @Type(Font)
    List<Font> fonts = []
    @Type(Style)
    List<Style> styles = []
    @Type(Unk3.class)
    List<Unk3> chatColors = []
    private ByteArrayOutputStream unk = new ByteArrayOutputStream()

    @Override
    XDAT read(InputStream input) throws IOException {
        use(IOUtil) {
            shortcuts = input.readList(Shortcut)
            int count = input.readInt()
            for (int i = 0; i < count; i++)
                windows.add(new Window().read(input))
            input.readInt()
            wndDefPos = input.readList(WndDefPos)
            fonts = input.readList(Font)
            styles = input.readList(Style)
            chatColors = input.readList(Unk3)
            int b
            while ((b = input.read()) >= 0)
                unk.write(b)
        }
        this
    }

    @Override
    XDAT write(OutputStream output) throws IOException {
        use(IOUtil) {
            output.writeList(shortcuts)
            output.writeInt(windows.size())
            for (Window window : windows)
                output.writeIOEntity(window)
            output.writeInt(1)
            output.writeList(wndDefPos)
            output.writeList(fonts)
            output.writeList(styles)
            output.writeList(chatColors)
            unk.writeTo(output)
        }
        this
    }

    static {
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
