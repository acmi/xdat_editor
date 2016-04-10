package ct0

import acmi.l2.clientmod.l2resources.Tex
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
@CompileStatic
class EffectButton extends DefaultProperty {
    int type
    @Tex
    String normalTex = 'undefined'
    @Tex
    String pushedTex = 'undefined'
    @Tex
    String highlightTex = 'undefined'
    @Tex
    String effectTex1 = 'undefined'
    @Tex
    String effectTex2 = 'undefined'

    // @formatter:off
    @Deprecated int getUnk100() { type }
    @Deprecated void setUnk100(int unk100) { this.type = unk100 }

    @Deprecated String getTex() { normalTex }
    @Deprecated void setTex(String tex) { this.normalTex = tex }

    @Deprecated String getTexDown() { pushedTex }
    @Deprecated void setTexDown(String texDown) { this.pushedTex = texDown }

    @Deprecated String getTexOver() { highlightTex }
    @Deprecated void setTexOver(String texOver) { this.highlightTex = texOver }

    @Deprecated String getTexEffect1() { effectTex1 }
    @Deprecated void setTexEffect1(String texEffect1) { this.effectTex1 = texEffect1 }

    @Deprecated String getTexEffect2() { effectTex2 }
    @Deprecated void setTexEffect2(String texEffect2) { this.effectTex2 = texEffect2 }
    // @formatter:on
}
