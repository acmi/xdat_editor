package ct1

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.Type
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic

@DefaultIO
@CompileStatic
class RadarMapCtrl extends DefaultProperty {
    String unk100
    float unk101
    @Type(RadarMapElement.class)
    List<RadarMapElement> unk102 = []

    @DefaultIO
    static class RadarMapElement implements IOEntity {
        String unk103
        String unk104
        String unk105
        String unk106
        int unk107
        int unk108
        int unk109

        @Override
        String toString() {
            return getClass().simpleName
        }
    }
}
