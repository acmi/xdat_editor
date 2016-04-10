package ct26

import acmi.l2.clientmod.l2resources.Sysstr
import acmi.l2.clientmod.l2resources.Tex
import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.Type
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
class Tab extends DefaultProperty {
    @Type(TabElement.class)
    List<TabElement> tabs = []

    @FXBindable
    @DefaultIO
    static class TabElement implements IOEntity {
        @Sysstr
        int buttonName = -9999
        String buttonNameText = "undefined"
        String target = "undefined"
        int width
        int height
        @Tex
        String normalTex = "undefined"
        @Tex
        String pushedTex = "undefined"
        boolean movable
        int gap
        @Sysstr
        int tooltip = -1
        int noHighlight = -1

        @Override
        String toString() {
            getClass().simpleName
        }

        // @formatter:off
        @Deprecated int getTitleStringId() { buttonName }
        @Deprecated void setTitleStringId(int titleStringId) { this.buttonName = titleStringId }

        @Deprecated String getTitleStringValue() { buttonNameText }
        @Deprecated void setTitleStringValue(String titleStringValue) { this.buttonNameText = titleStringValue }

        @Deprecated String getContextCtrl() { target }
        @Deprecated void setContextCtrl(String contextCtrl) { this.target = contextCtrl }

        @Deprecated int getHeadWidth() { width }
        @Deprecated void setHeadWidth(int headWidth) { this.width = headWidth }

        @Deprecated int getHeadHeight() { height }
        @Deprecated void setHeadHeight(int headHeight) { this.height = headHeight }

        @Deprecated String getTextureUnselected() { normalTex }
        @Deprecated void setTextureUnselected(String textureUnselected) { this.normalTex = textureUnselected }

        @Deprecated String getTextureSelected() { pushedTex }
        @Deprecated void setTextureSelected(String textureSelected) { this.pushedTex = textureSelected }

        @Deprecated boolean getFloatable() { movable }
        @Deprecated void setFloatable(boolean floatable) { this.movable = floatable }

        @Deprecated int getSpaceAfter() { gap }
        @Deprecated void setSpaceAfter(int spaceAfter) { this.gap = spaceAfter }

        @Deprecated int getUnk110() { tooltip }
        @Deprecated void setUnk110(int unk110) { this.tooltip = unk110 }

        @Deprecated int getUnk111() { noHighlight }
        @Deprecated void setUnk111(int unk111) { this.noHighlight = unk111 }
        // @formatter:on
    }
}
