package ct0

import acmi.l2.clientmod.util.Type
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
@CompileStatic
class Window extends DefaultProperty implements Iterable<DefaultProperty> {
    String unk100
    String unk101
    String unk102
    String unk103
    int unk104
    int unk105
    int unk106
    int unk107
    int unk108
    int unk109
    int unk110
    int unk111
    int unk112
    int unk113
    int unk114
    int unk115
    int unk116
    int unk117
    int unk118
    int unk119
    float unk120
    float unk121
    int unk122
    int unk123
    int unk124
    int unk125
    int unk126
    int unk127
    int unk128
    String unk129
    int unk130
    int unk131
    int unk132
    int unk133
    int unk134
    int unk135
    String unk136
    int unk137
    int unk138
    String unk139
    @Type(DefaultProperty.class)
    List<DefaultProperty> children = []

    @Override
    Iterator<DefaultProperty> iterator() {
        children.iterator()
    }
}
