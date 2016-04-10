package ct0

import acmi.l2.clientmod.l2resources.Tex
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
@CompileStatic
class StatusBar extends DefaultProperty {
    String title
    int textureWidth
    int textureHeight
    @Tex
    String foreTexture
    @Tex
    String backTexture
    @Tex
    String warnTexture
    @Tex
    String regenTexture

    // @formatter:off
    @Deprecated String getUnk100() { title }
    @Deprecated void setUnk100(String unk100) { this.title = unk100 }

    @Deprecated int getUnk101() { textureWidth }
    @Deprecated void setUnk101(int unk101) { this.textureWidth = unk101 }

    @Deprecated int getUnk102() { textureHeight }
    @Deprecated void setUnk102(int unk102) { this.textureHeight = unk102 }

    @Deprecated String getUnk103() { foreTexture }
    @Deprecated void setUnk103(String unk103) { this.foreTexture = unk103 }

    @Deprecated String getUnk104() { backTexture }
    @Deprecated void setUnk104(String unk104) { this.backTexture = unk104 }

    @Deprecated String getUnk105() { warnTexture }
    @Deprecated void setUnk105(String unk105) { this.warnTexture = unk105 }

    @Deprecated String getUnk106() { regenTexture }
    @Deprecated void setUnk106(String unk106) { this.regenTexture = unk106 }
    // @formatter:on
}
