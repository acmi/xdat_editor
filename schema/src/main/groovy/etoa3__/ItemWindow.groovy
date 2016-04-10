package etoa3__

import acmi.l2.clientmod.l2resources.Tex
import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.StringValue
import acmi.l2.clientmod.util.Type
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
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
    @Tex
    String backgroundItemTex = 'undefined'
    int selectedItemWidth = -9999
    int selectedItemHeight = -9999
    @Tex
    String selectedItemTex = 'undefined'
    int unselectedItemWidth = -9999
    int unselectedItemHeight = -9999
    @Tex
    String unselectedItemTex = 'undefined'
    int newToggleItemOffSetX = -9999
    int newToggleItemOffSetY = -9999
    int newToggleItemWidth = -9999
    int newToggleItemHeight = -9999
    @Tex
    String newToggleItemTex = 'undefined'
    int blankItemWidth = -9999
    int blankItemHeight = -9999
    @Tex
    String blankItemTex = 'undefined'
    int blankItemDependency = 0
    Boolean noSelectItem
    Boolean noItemDrag
    Boolean buttonClick
    Boolean useCoolTime
    Boolean noScroll
    Boolean showIconFrame
    Boolean simpleTooltip
    @Tex
    String outLineUp = 'l2ui_ch3.InventoryWnd.inventory_outline'
    @Tex
    String outLineDown = 'l2ui_ch3.InventoryWnd.inventory_outline_down'
    int buttonTypePrevButtonPosX = -9999
    int buttonTypePrevButtonPosY = -9999
    int buttonTypeNextButtonPosX = -9999
    int buttonTypeNextButtonPosY = -9999
    @Type(ItemWindowInner.class)
    List<ItemWindowInner> expandItem = []

    @FXBindable
    @DefaultIO
    static class ItemWindowInner implements IOEntity {
        int width
        int height
        int num
        @Tex
        String texture

        @Override
        String toString() {
            getClass().simpleName
        }

        // @formatter:off
        @Deprecated int getUnk135() { width }
        @Deprecated void setUnk135(int unk135) { this.width = unk135 }

        @Deprecated int getUnk136() { height }
        @Deprecated void setUnk136(int unk136) { this.height = unk136 }

        @Deprecated int getUnk137() { num }
        @Deprecated void setUnk137(int unk137) { this.num = unk137 }

        @Deprecated String getUnk138() { texture }
        @Deprecated void setUnk138(String unk138) { this.texture = unk138 }
        // @formatter:on
    }

    enum ItemWindowType implements StringValue{
        ScrollType,
        SideButtonType,
        UpDownButtonType
    }

    // @formatter:off
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

    @Deprecated int getUnk1211() { blankItemDependency }
    @Deprecated void setUnk1211(int unk1211) { this.blankItemDependency = unk1211 }

    @Deprecated int getUnk122() { IOUtil.boolToInt(noSelectItem) }
    @Deprecated void setUnk122(int unk122) { this.noSelectItem = IOUtil.intToBool(unk122) }

    @Deprecated int getUnk123() { IOUtil.boolToInt(noItemDrag) }
    @Deprecated void setUnk123(int unk123) { this.noItemDrag = IOUtil.intToBool(unk123) }

    @Deprecated int getUnk124() { IOUtil.boolToInt(buttonClick) }
    @Deprecated void setUnk124(int unk124) { this.buttonClick = IOUtil.intToBool(unk124) }

    @Deprecated int getUnk125() { IOUtil.boolToInt(useCoolTime) }
    @Deprecated void setUnk125(int unk125) { this.useCoolTime = IOUtil.intToBool(unk125) }

    @Deprecated int getUnk126() { IOUtil.boolToInt(noScroll) }
    @Deprecated void setUnk126(int unk126) { this.noScroll = IOUtil.intToBool(unk126) }

    @Deprecated int getUnk127() { IOUtil.boolToInt(showIconFrame) }
    @Deprecated void setUnk127(int unk127) { this.showIconFrame = IOUtil.intToBool(unk127) }

    @Deprecated int getUnk127_() { IOUtil.boolToInt(simpleTooltip) }
    @Deprecated void setUnk127_(int unk127_) { this.simpleTooltip = IOUtil.intToBool(unk127_) }

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

    @Deprecated List<ItemWindowInner> getUnk134() { expandItem }
    @Deprecated void setUnk134(List<ItemWindowInner> unk134){ this.expandItem = unk134 }
    // @formatter:on
}
