package etoa4

import acmi.l2.clientmod.l2resources.Tex
import acmi.l2.clientmod.util.IntValue
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
@CompileStatic
class EffectButton extends DefaultProperty {
    Type type = Type.TUTORIAL
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
    @Tex
    String disableTex = 'undefined'

    enum Type implements IntValue{
        TUTORIAL(-1),
        NORMAL(0),
        QUEST(1),
        MAIL(2),
        NOTYPE(3);

        final int value

        Type(int value) {this.value = value}

        @Override
        int intValue() { value }

        static Type valueOf(int val){
            Optional.ofNullable(values().find { it.value == val })
                    .orElseThrow({ new IllegalArgumentException("No ${getClass().simpleName} constant with value=$val") })
        }
    }
}
