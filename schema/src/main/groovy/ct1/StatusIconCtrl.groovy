package ct1

import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic

@DefaultIO
@CompileStatic
class StatusIconCtrl extends DefaultProperty {
    boolean noClip
    boolean noTooltip

    // @formatter:off
    @Deprecated boolean getUnk100() { noClip }
    @Deprecated void setUnk100(boolean unk100) { this.noClip = unk100 }

    @Deprecated boolean getUnk101() { noTooltip }
    @Deprecated void setUnk101(boolean unk101) { this.noTooltip = unk101 }
    // @formatter:on
}
