package etoa3_

import acmi.l2.clientmod.util.Description
import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.l2resources.Sysstr
import acmi.l2.clientmod.l2resources.Tex
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
@CompileStatic
class Button extends DefaultProperty {
    @Tex
    String normalTex = 'undefined'
    @Tex
    String pushedTex = 'undefined'
    @Tex
    String highlightTex = 'undefined'
    @Tex
    String dropTex = 'undefined'
    @Sysstr
    int buttonName = -9999
    String buttonNameText
    Boolean noHighlight
    Boolean defaultSoundOn
    @Description('-9999/5000')
    int disableTime = -9999
    @Tex
    String disableTex = 'undefined'

    // @formatter:off
    @Deprecated String getTexture() { normalTex }
    @Deprecated void setTexture(String texture) { this.normalTex = texture }

    @Deprecated String getTextureDown() { pushedTex }
    @Deprecated void setTextureDown(String textureDown) { this.pushedTex = textureDown }

    @Deprecated String getTextureOver() { highlightTex }
    @Deprecated void setTextureOver(String textureOver) { this.highlightTex = textureOver }

    @Deprecated String getUnk103() { dropTex }
    @Deprecated void setUnk103(String unk103) { this.dropTex = unk103 }

    @Deprecated int getTextStringId() { buttonName }
    @Deprecated void setTextStringId(int textStringId) { this.buttonName = textStringId }

    @Deprecated String getTextStringValue() { buttonNameText }
    @Deprecated void setTextStringValue(String textStringValue) { this.buttonNameText = textStringValue }

    @Deprecated int getUnk106() { IOUtil.boolToInt(noHighlight) }
    @Deprecated void setUnk106(int unk106) { this.noHighlight = IOUtil.intToBool(unk106) }

    @Deprecated int getUnk107() { IOUtil.boolToInt(defaultSoundOn) }
    @Deprecated void setUnk107(int unk107) { this.defaultSoundOn = IOUtil.intToBool(unk107) }

    @Deprecated int getUnk108() { disableTime }
    @Deprecated void setUnk108(int unk108) { this.disableTime = unk108 }

    @Deprecated String getUnk109() { disableTex }
    @Deprecated void setUnk109(String unk109) { this.disableTex = unk109 }
    // @formatter:on
}
