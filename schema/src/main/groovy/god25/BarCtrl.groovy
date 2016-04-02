package god25

import acmi.l2.clientmod.l2resources.Tex
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic

@DefaultIO
@CompileStatic
class BarCtrl extends DefaultProperty {
    @Tex
    String foreTexture = 'undefined'
    @Tex
    String foreLeftTexture = 'undefined'
    @Tex
    String foreRightTexture = 'undefined'
    @Tex
    String backTexture = 'undefined'
    @Tex
    String backLeftTexture = 'undefined'
    @Tex
    String backRightTexture = 'undefined'
    int uSize
    int vSize

    @Deprecated String getTexCenter() { foreTexture }
    @Deprecated void setTexCenter(String texCenter) { this.foreTexture = texCenter }

    @Deprecated String getTextLeft() { foreLeftTexture }
    @Deprecated void setTextLeft(String textLeft) { this.foreLeftTexture = textLeft }

    @Deprecated String getTexRight() { foreRightTexture }
    @Deprecated void setTexRight(String texRight) { this.foreRightTexture = texRight }

    @Deprecated String getTexBGCenter() { backTexture }
    @Deprecated void setTexBGCenter(String texBGCenter) { this.backTexture = texBGCenter }

    @Deprecated String getTexBGLeft() { backLeftTexture }
    @Deprecated void setTexBGLeft(String texBGLeft) { this.backLeftTexture = texBGLeft }

    @Deprecated String getTexBGRight() { backRightTexture }
    @Deprecated void setTexBGRight(String texBGRight) { this.backRightTexture = texBGRight }

    @Deprecated int getUnk106() { uSize }
    @Deprecated void setUnk106(int unk106) { this.uSize = unk106 }

    @Deprecated int getUnk107() { vSize }
    @Deprecated void setUnk107(int unk107) { this.vSize = unk107 }
}
