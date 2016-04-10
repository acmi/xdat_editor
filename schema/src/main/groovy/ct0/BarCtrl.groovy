package ct0

import acmi.l2.clientmod.l2resources.Tex
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
@CompileStatic
class BarCtrl extends DefaultProperty {
    @Tex
    String foreTexture = 'undefined'
    @Tex
    String backTexture = 'undefined'
    int uSize
    int vSize

    // @formatter:off
    @Deprecated String getTex() { foreTexture }
    @Deprecated void setTex(String tex) { this.foreTexture = tex }

    @Deprecated String getTexBack() { backTexture }
    @Deprecated void setTexBack(String texBack) { this.backTexture = texBack }

    @Deprecated int getUnk106() { uSize }
    @Deprecated void setUnk106(int unk106) { this.uSize = unk106 }

    @Deprecated int getUnk107() { vSize }
    @Deprecated void setUnk107(int unk107) { this.vSize = unk107 }
    // @formatter:on
}
