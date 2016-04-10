package ct0

import acmi.l2.clientmod.l2resources.Tex
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
@CompileStatic
class ItemWindow extends DefaultProperty {
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
    int noSelectItem = -1
    int noItemDrag = -1
    int buttonClick = -1
    int useCoolTime = -1
    int noScroll = -1
    @Tex
    String outLineUp = 'l2ui_ch3.InventoryWnd.inventory_outline'
    @Tex
    String outLineDown = 'l2ui_ch3.InventoryWnd.inventory_outline_down'

    // @formatter:off
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

    @Deprecated String getUnk128() { outLineUp }
    @Deprecated void setUnk128(String unk128) { this.outLineUp = unk128 }

    @Deprecated String getUnk129() { outLineDown }
    @Deprecated void setUnk129(String unk129) { this.outLineDown = unk129 }
    // @formatter:on
}
