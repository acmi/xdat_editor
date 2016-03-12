package ct15

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.Type

class RadarMapCtrl extends DefaultProperty {
    String unk100
    float unk101
    @Type(RadarMapElement.class)
    List<RadarMapElement> unk102 = []

    static class RadarMapElement implements IOEntity {
        String unk103
        String unk104
        String unk105
        String unk106
        int unk107
        int unk108
        int unk109

        @Override
        RadarMapElement read(InputStream input) {
            use(IOUtil) {
                unk103 = input.readString()
                unk104 = input.readString()
                unk105 = input.readString()
                unk106 = input.readString()
                unk107 = input.readInt()
                unk108 = input.readInt()
                unk109 = input.readInt()
            }
            this
        }

        @Override
        RadarMapElement write(OutputStream output) {
            use(IOUtil) {
                output.writeString(unk103)
                output.writeString(unk104)
                output.writeString(unk105)
                output.writeString(unk106)
                output.writeInt(unk107)
                output.writeInt(unk108)
                output.writeInt(unk109)
            }
            this
        }

        @Override
        String toString() {
            return getClass().simpleName
        }
    }

    @Override
    RadarMapCtrl read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            unk100 = input.readString()
            unk101 = input.readFloat()
            int count = input.readInt()
            for (int i = 0; i < count; i++)
                unk102.add(new RadarMapElement().read(input))
        }

        this
    }

    @Override
    RadarMapCtrl write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeString(unk100)
            output.writeFloat(unk101)
            output.writeInt(unk102.size())
            for (RadarMapElement radarMapElement : unk102)
                radarMapElement.write(output)
        }

        this
    }
}
