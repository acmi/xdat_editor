package ct0

import acmi.l2.clientmod.l2resources.Tex
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
@CompileStatic
class Progress extends DefaultProperty {
    @Tex
    String backTexture
    @Tex
    String barTexture
    int gap

    // @formatter:off
    @Deprecated String getUnk100() { backTexture }
    @Deprecated void setUnk100(String unk100) { this.backTexture = unk100 }

    @Deprecated String getUnk101() { barTexture }
    @Deprecated void setUnk101(String unk101) { this.barTexture = unk101 }

    @Deprecated int getUnk102() { gap }
    @Deprecated void setUnk102(int unk102) { this.gap = unk102 }
    // @formatter:on
}
