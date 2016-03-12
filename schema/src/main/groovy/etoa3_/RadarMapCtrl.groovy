package etoa3_

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.Tex
import acmi.l2.clientmod.util.Type
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic

@DefaultIO
@CompileStatic
class RadarMapCtrl extends DefaultProperty {
	String mapTextureName
	float layer
	@Type(RadarMapCtrlObjectType.class)
    List<RadarMapCtrlObjectType> radarMapCtrlObjectType = []

    @DefaultIO
	static class RadarMapCtrlObjectType implements IOEntity {
		String typeName
		@Tex
		String normalTexName
		@Tex
		String outsideTexName
		@Tex
		String pushedTexName
		@Tex
		String overTexName
		int width
		int height
		int top

        @Override
        String toString() {
            return typeName
        }
	}

    @Override
    String toString() {
        return mapTextureName
    }
}
