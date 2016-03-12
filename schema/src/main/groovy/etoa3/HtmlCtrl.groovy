package etoa3

import acmi.l2.clientmod.util.Description
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic

@DefaultIO
@CompileStatic
class HtmlCtrl extends DefaultProperty {
    @Description("''/'Normal'/'Help'/'BBS'")
    String viewType

    @Deprecated String getType() { return viewType }
    @Deprecated void setType(String type) { this.viewType = type }
}
