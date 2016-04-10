package etoa3_

import acmi.l2.clientmod.util.Type
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
@CompileStatic
class ScrollArea extends DefaultProperty implements Iterable<DefaultProperty> {
    int areaHeight
    @Type(DefaultProperty.class)
    List<DefaultProperty> children = []

    @Override
    Iterator<DefaultProperty> iterator() {
        children.iterator()
    }

    // @formatter:off
    @Deprecated int getUnk100() { areaHeight }
    @Deprecated void setUnk100(int unk100) { this.areaHeight = unk100 }
    // @formatter:on
}
