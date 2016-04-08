package ct1

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.Type
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic

@DefaultIO
@CompileStatic
class ListCtrl extends DefaultProperty {
    int unk100
    int unk101
    int unk102
    int unk103
    int unk104
    @Type(ListElement.class)
    List<ListElement> values = []

    @DefaultIO
    static class ListElement implements IOEntity {
        int textStringId
        int width
        boolean unk108
        boolean unk109
        boolean unk110

        @Override
        String toString() {
            return getClass().simpleName
        }
    }
}
