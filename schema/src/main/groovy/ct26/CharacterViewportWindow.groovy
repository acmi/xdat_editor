package ct26

import acmi.l2.clientmod.l2resources.Tex
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
@CompileStatic
class CharacterViewportWindow extends DefaultProperty {
    float characterScale = 1f
    int characterOffsetX = -2
    int characterOffsetY = -6
    int cameraDistMax = 300
    int cameraDistMin = 250
    int defaultCameraPitch = -800
    int defaultCameraYaw = 34000
    int zoomRate = 3
    int rotationRate = 700
    @Tex
    String backgroundTex
    int npcID

    // @formatter:off
    @Deprecated float getUnk100() { return characterScale }
    @Deprecated void setUnk100(float unk100) { this.characterScale = unk100 }

    @Deprecated int getUnk101() { return characterOffsetX }
    @Deprecated void setUnk101(int unk101) { this.characterOffsetX = unk101 }

    @Deprecated int getUnk102() { return characterOffsetY }
    @Deprecated void setUnk102(int unk102) { this.characterOffsetY = unk102 }

    @Deprecated int getUnk103() { return cameraDistMax }
    @Deprecated void setUnk103(int unk103) { this.cameraDistMax = unk103 }

    @Deprecated int getUnk104() { return cameraDistMin }
    @Deprecated void setUnk104(int unk104) { this.cameraDistMin = unk104 }

    @Deprecated int getUnk105() { return defaultCameraPitch }
    @Deprecated void setUnk105(int unk105) { this.defaultCameraPitch = unk105 }

    @Deprecated int getUnk106() { return defaultCameraYaw }
    @Deprecated void setUnk106(int unk106) { this.defaultCameraYaw = unk106 }

    @Deprecated int getUnk107() { return zoomRate }
    @Deprecated void setUnk107(int unk107) { this.zoomRate = unk107 }

    @Deprecated int getUnk108() { return rotationRate }
    @Deprecated void setUnk108(int unk108) { this.rotationRate = unk108 }

    @Deprecated String getUnk109() { return backgroundTex }
    @Deprecated void setUnk109(String unk109) { this.backgroundTex = unk109 }

    @Deprecated int getUnk110() { return npcID }
    @Deprecated void setUnk110(int unk110) { this.npcID = unk110 }
    // @formatter:on
}
