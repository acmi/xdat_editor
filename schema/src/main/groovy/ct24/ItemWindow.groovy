package ct24

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.Type

class ItemWindow extends DefaultProperty {
    ItemWindowType wndType = ItemWindowType.ScrollType
    int col
    int row
    int maxItemNum
    int iconWidth
    int iconHeight
    int gapX = -9999
    int gapY = -9999
    int offsetX = -9999
    int offsetY = -9999
    int backgroundItemWidth = -9999
    int backgroundItemHeight = -9999
    String backgroundItemTex = 'undefined'
    int selectedItemWidth = -9999
    int selectedItemHeight = -9999
    String selectedItemTex = 'undefined'
    int unselectedItemWidth = -9999
    int unselectedItemHeight = -9999
    String unselectedItemTex = 'undefined'
    int blankItemWidth = -9999
    int blankItemHeight = -9999
    String blankItemTex = 'undefined'
    int noSelectItem = -1
    int noItemDrag = -1
    int buttonClick = -1
    int useCoolTime = -1
    int noScroll = -1
    int showIconFrame = -1
    String outLineUp = 'l2ui_ch3.InventoryWnd.inventory_outline'
    String outLineDown = 'l2ui_ch3.InventoryWnd.inventory_outline_down'
    int buttonTypePrevButtonPosX = -9999
    int buttonTypePrevButtonPosY = -9999
    int buttonTypeNextButtonPosX = -9999
    int buttonTypeNextButtonPosY = -9999
    @Type(ItemWindowInner.class)
    List<ItemWindowInner> unk134 = []

    static class ItemWindowInner implements IOEntity {
        int unk135
        int unk136
        int unk137
        String unk138

        @Override
        ItemWindowInner read(InputStream input) {
            use(IOUtil) {
                unk135 = input.readInt()
                unk136 = input.readInt()
                unk137 = input.readInt()
                unk138 = input.readString()
            }
            this
        }

        @Override
        ItemWindowInner write(OutputStream output) {
            use(IOUtil) {
                output.writeInt(unk135)
                output.writeInt(unk136)
                output.writeInt(unk137)
                output.writeString(unk138)
            }
            this
        }

        @Override
        String toString() {
            return getClass().simpleName
        }
    }

    @Override
    ItemWindow read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            wndType = ItemWindowType.valueOf(input.readString())
            col = input.readInt()
            row = input.readInt()
            maxItemNum = input.readInt()
            iconWidth = input.readInt()
            iconHeight = input.readInt()
            gapX = input.readInt()
            gapY = input.readInt()
            offsetX = input.readInt()
            offsetY = input.readInt()
            backgroundItemWidth = input.readInt()
            backgroundItemHeight = input.readInt()
            backgroundItemTex = input.readString()
            selectedItemWidth = input.readInt()
            selectedItemHeight = input.readInt()
            selectedItemTex = input.readString()
            unselectedItemWidth = input.readInt()
            unselectedItemHeight = input.readInt()
            unselectedItemTex = input.readString()
            blankItemWidth = input.readInt()
            blankItemHeight = input.readInt()
            blankItemTex = input.readString()
            noSelectItem = input.readInt()
            noItemDrag = input.readInt()
            buttonClick = input.readInt()
            useCoolTime = input.readInt()
            noScroll = input.readInt()
            showIconFrame = input.readInt()
            outLineUp = input.readString()
            outLineDown = input.readString()
            buttonTypePrevButtonPosX = input.readInt()
            buttonTypePrevButtonPosY = input.readInt()
            buttonTypeNextButtonPosX = input.readInt()
            buttonTypeNextButtonPosY = input.readInt()
            int count = input.readInt()
            for (int i = 0; i < count; i++)
                unk134.add(new ItemWindowInner().read(input))
        }

        this
    }

    @Override
    ItemWindow write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeString(wndType.name())
            output.writeInt(col)
            output.writeInt(row)
            output.writeInt(maxItemNum)
            output.writeInt(iconWidth)
            output.writeInt(iconHeight)
            output.writeInt(gapX)
            output.writeInt(gapY)
            output.writeInt(offsetX)
            output.writeInt(offsetY)
            output.writeInt(backgroundItemWidth)
            output.writeInt(backgroundItemHeight)
            output.writeString(backgroundItemTex)
            output.writeInt(selectedItemWidth)
            output.writeInt(selectedItemHeight)
            output.writeString(selectedItemTex)
            output.writeInt(unselectedItemWidth)
            output.writeInt(unselectedItemHeight)
            output.writeString(unselectedItemTex)
            output.writeInt(blankItemWidth)
            output.writeInt(blankItemHeight)
            output.writeString(blankItemTex)
            output.writeInt(noSelectItem)
            output.writeInt(noItemDrag)
            output.writeInt(buttonClick)
            output.writeInt(useCoolTime)
            output.writeInt(noScroll)
            output.writeInt(showIconFrame)
            output.writeString(outLineUp)
            output.writeString(outLineDown)
            output.writeInt(buttonTypePrevButtonPosX)
            output.writeInt(buttonTypePrevButtonPosY)
            output.writeInt(buttonTypeNextButtonPosX)
            output.writeInt(buttonTypeNextButtonPosY)
            output.writeInt(unk134.size())
            for (ItemWindowInner itemWindowInner : unk134)
                itemWindowInner.write(output)
        }

        this
    }

    enum ItemWindowType {
        ScrollType,
        SideButtonType,
        UpDownButtonType
    }

    @Deprecated String getUnk100() { wndType.name() }
    @Deprecated void setUnk100(String unk100) { this.wndType = ItemWindowType.valueOf(unk100) }

    @Deprecated int getCols() { col }
    @Deprecated void setCols(int cols) { this.col = cols }

    @Deprecated int getRows() { row }
    @Deprecated void setRows(int rows) { this.row = rows }

    @Deprecated int getUnk103() { maxItemNum }
    @Deprecated void setUnk103(int unk103) { this.maxItemNum = unk103 }

    @Deprecated int getCellWidth() { iconWidth }
    @Deprecated void setCellWidth(int cellWidth) { this.iconWidth = cellWidth }

    @Deprecated int getCellHeight() { iconHeight }
    @Deprecated void setCellHeight(int cellHeight) { this.iconHeight = cellHeight }

    @Deprecated int getSpacing_horizontal() { gapX }
    @Deprecated void setSpacing_horizontal(int spacing_horizontal) { this.gapX = spacing_horizontal }

    @Deprecated int getSpacing_vertical() { gapY }
    @Deprecated void setSpacing_vertical(int spacing_vertical) { this.gapY = spacing_vertical }

    @Deprecated int getUnk108() { offsetX }
    @Deprecated void setUnk108(int unk108) { this.offsetX = unk108 }

    @Deprecated int getUnk109() { offsetY }
    @Deprecated void setUnk109(int unk109) { this.offsetY = unk109 }

    @Deprecated int getUnk110() { backgroundItemWidth }
    @Deprecated void setUnk110(int unk110) { this.backgroundItemWidth = unk110 }

    @Deprecated int getUnk111() { backgroundItemHeight }
    @Deprecated void setUnk111(int unk111) { this.backgroundItemHeight = unk111 }

    @Deprecated String getUnk112() { backgroundItemTex }
    @Deprecated void setUnk112(String unk112) { this.backgroundItemTex = unk112 }

    @Deprecated int getUnk113() { selectedItemWidth }
    @Deprecated void setUnk113(int unk113) { this.selectedItemWidth = unk113 }

    @Deprecated int getUnk114() { selectedItemHeight }
    @Deprecated void setUnk114(int unk114) { this.selectedItemHeight = unk114 }

    @Deprecated String getUnk115() { selectedItemTex }
    @Deprecated void setUnk115(String unk115) { this.selectedItemTex = unk115 }

    @Deprecated int getUnk116() { unselectedItemWidth }
    @Deprecated void setUnk116(int unk116) { this.unselectedItemWidth = unk116 }

    @Deprecated int getUnk117() { unselectedItemHeight }
    @Deprecated void setUnk117(int unk117) { this.unselectedItemHeight = unk117 }

    @Deprecated String getUnk118() { unselectedItemTex }
    @Deprecated void setUnk118(String unk118) { this.unselectedItemTex = unk118 }

    @Deprecated int getUnk119() { blankItemWidth }
    @Deprecated void setUnk119(int unk119) { this.blankItemWidth = unk119 }

    @Deprecated int getUnk120() { blankItemHeight }
    @Deprecated void setUnk120(int unk120) { this.blankItemHeight = unk120 }

    @Deprecated String getUnk121() { blankItemTex }
    @Deprecated void setUnk121(String unk121) { this.blankItemTex = unk121 }

    @Deprecated int getUnk122() { noSelectItem }
    @Deprecated void setUnk122(int unk122) { this.noSelectItem = unk122 }

    @Deprecated int getUnk123() { noItemDrag }
    @Deprecated void setUnk123(int unk123) { this.noItemDrag = unk123 }

    @Deprecated int getUnk124() { buttonClick }
    @Deprecated void setUnk124(int unk124) { this.buttonClick = unk124 }

    @Deprecated int getUnk125() { useCoolTime }
    @Deprecated void setUnk125(int unk125) { this.useCoolTime = unk125 }

    @Deprecated int getUnk126() { noScroll }
    @Deprecated void setUnk126(int unk126) { this.noScroll = unk126 }

    @Deprecated int getUnk127() { showIconFrame }
    @Deprecated void setUnk127(int unk127) { this.showIconFrame = unk127 }

    @Deprecated String getUnk128() { outLineUp }
    @Deprecated void setUnk128(String unk128) { this.outLineUp = unk128 }

    @Deprecated String getUnk129() { outLineDown }
    @Deprecated void setUnk129(String unk129) { this.outLineDown = unk129 }

    @Deprecated int getUnk130() { buttonTypePrevButtonPosX }
    @Deprecated void setUnk130(int unk130) { this.buttonTypePrevButtonPosX = unk130 }

    @Deprecated int getUnk131() { buttonTypePrevButtonPosY }
    @Deprecated void setUnk131(int unk131) { this.buttonTypePrevButtonPosY = unk131 }

    @Deprecated int getUnk132() { buttonTypeNextButtonPosX }
    @Deprecated void setUnk132(int unk132) { this.buttonTypeNextButtonPosX = unk132 }

    @Deprecated int getUnk133() { buttonTypeNextButtonPosY }
    @Deprecated void setUnk133(int unk133) { this.buttonTypeNextButtonPosY = unk133 }
}
