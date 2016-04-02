package etoa2_3

import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.l2resources.Sysstr
import acmi.l2.clientmod.l2resources.Tex
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic

@DefaultIO
@CompileStatic
class CheckBox extends DefaultProperty {
    @Sysstr
    int titleIndex = -1
    String titleText = 'undefined'
    Boolean checked
    Boolean leftAligned
    int maxWidth = -9999
    @Tex
    String checkTexture = 'undefined'
    @Tex
    String unCheckTexture = 'undefined'
    @Tex
    String disableTexture = 'undefined'
    @Tex
    String disableCheckTexture = 'undefined'

    @Deprecated int getLabelStringId() { titleIndex }
    @Deprecated void setLabelStringId(int labelStringId) { this.titleIndex = labelStringId }

    @Deprecated String getLabelString() { titleText }
    @Deprecated void setLabelString(String labelString) { this.titleText = labelString }

    @Deprecated int getUnk102() { IOUtil.boolToInt(checked) }
    @Deprecated void setUnk102(int unk102) { this.checked = IOUtil.intToBool(unk102) }

    @Deprecated int getUnk103() { IOUtil.boolToInt(leftAligned) }
    @Deprecated void setUnk103(int unk103) { this.leftAligned = IOUtil.intToBool(unk103) }

    @Deprecated int getUnk104() { maxWidth }
    @Deprecated void setUnk104(int unk104) { this.maxWidth = unk104 }

    @Deprecated String getTexChecked() { checkTexture }
    @Deprecated void setTexChecked(String texChecked) { this.checkTexture = texChecked }

    @Deprecated String getTex() { unCheckTexture }
    @Deprecated void setTex(String tex) { this.unCheckTexture = tex }

    @Deprecated String getTexUnable() { disableTexture }
    @Deprecated void setTexUnable(String texUnable) { this.disableTexture = texUnable }

    @Deprecated String getTexCheckedUnable() { disableCheckTexture }
    @Deprecated void setTexCheckedUnable(String texCheckedUnable) { this.disableCheckTexture = texCheckedUnable }
}
