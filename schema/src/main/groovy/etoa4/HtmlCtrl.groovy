package etoa4

import acmi.l2.clientmod.util.Description
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
@CompileStatic
class HtmlCtrl extends DefaultProperty {
    @Description("''/'Normal'/'Help'/'BBS'")
    String viewType

    // @formatter:off
    @Deprecated String getType() { viewType }
    @Deprecated void setType(String type) { this.viewType = type }
    // @formatter:on
}
