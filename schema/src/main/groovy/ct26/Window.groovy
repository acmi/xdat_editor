package ct26

import acmi.l2.clientmod.l2resources.Sysstr
import acmi.l2.clientmod.l2resources.Tex
import acmi.l2.clientmod.util.*
import groovyx.javafx.beans.FXBindable

@FXBindable
class Window extends DefaultProperty implements Iterable<DefaultProperty> {
    String parent
    @Tex
    String backTex
    String script
    String state
    Boolean frame
    Boolean iconable
    Boolean stuckable
    Boolean hidden
    Boolean alwaysFullAlpha
    Boolean savePosition
    Boolean saveSize
    @Sysstr
    int title = -9999
    Boolean resizeFrame
    FrameSizeType frameSize = FrameSizeType.None
    DirectionType frameDirection = DirectionType.None
    Boolean exitbutton
    Boolean draggable
    DirectionType resizeFrameDirection = DirectionType.None
    float resizeFrameX = -9999.0
    float resizeFrameY = -9999.0
    float resizeFrameWidth = -9999.0
    float resizeFrameHeight = -9999.0
    int resizeMaxIncrease
    DirectionType drawerDirection = DirectionType.None
    int offsetX
    int offsetY
    Boolean directionFixed
    @Hide
    int notUsed01 // offsetX
    @Hide
    int notUsed02 // offsetY
    @Hide
    int notUsed03 // directionFixed
    String ownerWindow
    AnimType showAnimType = AnimType.None
    AnimType hideAnimType = AnimType.None
    DirectionType showAnimDirection = DirectionType.None
    float showAnimSeconds
    DirectionType hideAnimDirection = DirectionType.None
    float hideAnimSeconds
    @Hide
    int notUsed11 // showAnimDirection
    @Hide
    int notUsed12 // showAnimSeconds
    @Hide
    int notUsed13 // hideAnimDirection
    @Hide
    int notUsed14 // hideAnimSeconds
    @Tex
    String iconName = 'undefined'
    int tooltipIdx = -9999
    Boolean hookKeyInput
    String workingConfiguration
    @Tex
    String leftTextureName = 'undefined'
    @Tex
    String midTextureName = 'undefined'
    @Tex
    String rightTextureName = 'undefined'
    @Tex
    String minimizeBtnTextureNormal = 'undefined'
    @Tex
    String minimizeBtnTexturePushed = 'undefined'
    @Tex
    String closeBtnTextureNormal = 'undefined'
    @Tex
    String closeBtnTexturePushed = 'undefined'
    int leftBackTextureWidth = -9999
    int midBackTextureWidth = -9999
    @Type(State.class)
    List<State> additionalState = []
    Boolean useParentClipRect
    @Type(DefaultProperty.class)
    List<DefaultProperty> children = []

    @Override
    Iterator<DefaultProperty> iterator() {
        children.iterator()
    }

    static class State implements IOEntity {
        String unk148

        @Override
        State read(InputStream input) {
            use(IOUtil) {
                unk148 = input.readString()
            }
            this
        }

        @Override
        State write(OutputStream output) {
            use(IOUtil) {
                output.writeString(unk148)
            }
            this
        }

        @Override
        String toString() {
            return State.class.simpleName
        }
    }

    enum FrameSizeType implements IntValue {
        None(-1),
        Big(0),
        Small(1);

        final int value

        FrameSizeType(int value) { this.value = value }

        @Override
        int intValue() { value }

        static FrameSizeType valueOf(int val) {
            Optional.ofNullable(values().find { it.value == val })
                    .orElseThrow({
                new IllegalArgumentException("No ${getClass().simpleName} constant with value=$val")
            })
        }
    }

    enum DirectionType {
        None,
        Left,
        Right,
        Top,
        Bottom,
        Free;

        static DirectionType valueOf(int val) {
            Optional.ofNullable(values().find { it.ordinal() == val })
                    .orElseThrow({
                new IllegalArgumentException("No ${getClass().simpleName} constant with value=$val")
            })
        }
    }

    enum AnimType implements IntValue {
        None(0),
        AlphaTransition(3),
        PositionTransition(4);

        final int value

        AnimType(int value) { this.value = value }

        @Override
        int intValue() { value }

        static AnimType valueOf(int val) {
            Optional.ofNullable(values().find { it.value == val })
                    .orElseThrow({
                new IllegalArgumentException("No ${getClass().simpleName} constant with value=$val")
            })
        }
    }

    @Override
    Window read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            parent = input.readString()
            backTex = input.readString()
            script = input.readString()
            state = input.readString()
            frame = input.readBoolean()
            iconable = input.readBoolean()
            stuckable = input.readBoolean()
            hidden = input.readBoolean()
            alwaysFullAlpha = input.readBoolean()
            savePosition = input.readBoolean()
            saveSize = input.readBoolean()
            title = input.readInt()
            resizeFrame = input.readBoolean()
            frameSize = input.readEnum(FrameSizeType)
            frameDirection = input.readEnum(DirectionType)
            exitbutton = input.readBoolean()
            draggable = input.readBoolean()
            resizeFrameDirection = input.readEnum(DirectionType)
            resizeFrameX = input.readFloat()
            resizeFrameY = input.readFloat()
            resizeFrameWidth = input.readFloat()
            resizeFrameHeight = input.readFloat()
            resizeMaxIncrease = input.readInt()
            drawerDirection = input.readEnum(DirectionType)
            if (drawerDirection.ordinal() > 0) {
                offsetX = input.readInt()
                offsetY = input.readInt()
                directionFixed = input.readBoolean()
            } else {
                notUsed01 = input.readInt()
                notUsed02 = input.readInt()
                notUsed03 = input.readInt()
            }
            ownerWindow = input.readString()
            showAnimType = input.readEnum(AnimType)
            hideAnimType = input.readEnum(AnimType)
            if (showAnimType.intValue() > 0) {
                showAnimDirection = input.readEnum(DirectionType)
                showAnimSeconds = input.readFloat()
            } else {
                notUsed11 = input.readInt()
                notUsed12 = input.readInt()
            }
            if (hideAnimType.intValue() > 0) {
                hideAnimDirection = input.readEnum(DirectionType)
                hideAnimSeconds = input.readFloat()
            } else {
                notUsed13 = input.readInt()
                notUsed14 = input.readInt()
            }
            iconName = input.readString()
            tooltipIdx = input.readInt()
            hookKeyInput = input.readBoolean()
            workingConfiguration = input.readString()
            leftTextureName = input.readString()
            midTextureName = input.readString()
            rightTextureName = input.readString()
            minimizeBtnTextureNormal = input.readString()
            minimizeBtnTexturePushed = input.readString()
            closeBtnTextureNormal = input.readString()
            closeBtnTexturePushed = input.readString()
            leftBackTextureWidth = input.readInt()
            midBackTextureWidth = input.readInt()
            additionalState = input.readList(State, ArrayLength.COMPACT_INT)
            useParentClipRect = input.readBoolean()
            children = input.readList(DefaultProperty)
        }

        this
    }

    @Override
    Window write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeString(parent)
            output.writeString(backTex)
            output.writeString(script)
            output.writeString(state)
            output.writeBoolean(frame)
            output.writeBoolean(iconable)
            output.writeBoolean(stuckable)
            output.writeBoolean(hidden)
            output.writeBoolean(alwaysFullAlpha)
            output.writeBoolean(savePosition)
            output.writeBoolean(saveSize)
            output.writeInt(title)
            output.writeBoolean(resizeFrame)
            output.writeEnum(frameSize)
            output.writeEnum(frameDirection)
            output.writeBoolean(exitbutton)
            output.writeBoolean(draggable)
            output.writeEnum(resizeFrameDirection)
            output.writeFloat(resizeFrameX)
            output.writeFloat(resizeFrameY)
            output.writeFloat(resizeFrameWidth)
            output.writeFloat(resizeFrameHeight)
            output.writeInt(resizeMaxIncrease)
            output.writeEnum(drawerDirection)
            if (drawerDirection.ordinal() > 0) {
                output.writeInt(offsetX)
                output.writeInt(offsetY)
                output.writeBoolean(directionFixed)
            } else {
                output.writeInt(notUsed01)
                output.writeInt(notUsed02)
                output.writeInt(notUsed03)
            }
            output.writeString(ownerWindow)
            output.writeEnum(showAnimType)
            output.writeEnum(hideAnimType)
            if (showAnimType.intValue() > 0) {
                output.writeEnum(showAnimDirection)
                output.writeFloat(showAnimSeconds)
            } else {
                output.writeInt(notUsed11)
                output.writeInt(notUsed12)
            }
            if (hideAnimType.intValue() > 0) {
                output.writeEnum(hideAnimDirection)
                output.writeFloat(hideAnimSeconds)
            } else {
                output.writeInt(notUsed13)
                output.writeInt(notUsed14)
            }
            output.writeString(iconName)
            output.writeInt(tooltipIdx)
            output.writeBoolean(hookKeyInput)
            output.writeString(workingConfiguration)
            output.writeString(leftTextureName)
            output.writeString(midTextureName)
            output.writeString(rightTextureName)
            output.writeString(minimizeBtnTextureNormal)
            output.writeString(minimizeBtnTexturePushed)
            output.writeString(closeBtnTextureNormal)
            output.writeString(closeBtnTexturePushed)
            output.writeInt(leftBackTextureWidth)
            output.writeInt(midBackTextureWidth)
            output.writeList(additionalState, ArrayLength.COMPACT_INT)
            output.writeBoolean(useParentClipRect)
            output.writeList(children)
        }

        this
    }

    // @formatter:off
    @Deprecated String getUnk100() { parent }
    @Deprecated void setUnk100(String unk100) { this.parent = unk100 }

    @Deprecated String getUnk101() { backTex }
    @Deprecated void setUnk101(String unk101) { this.backTex = unk101 }

    @Deprecated String getUnk102() { script }
    @Deprecated void setUnk102(String unk102) { this.script = unk102 }

    @Deprecated String getUnk103() { state }
    @Deprecated void setUnk103(String unk103) { this.state = unk103 }

    @Deprecated int getUnk104() { IOUtil.boolToInt(frame) }
    @Deprecated void setUnk104(int unk104) { this.frame = IOUtil.intToBool(unk104) }

    @Deprecated int getUnk105() { IOUtil.boolToInt(iconable) }
    @Deprecated void setUnk105(int unk105) { this.iconable = IOUtil.intToBool(unk105) }

    @Deprecated int getUnk106() { IOUtil.boolToInt(stuckable) }
    @Deprecated void setUnk106(int unk106) { this.stuckable = IOUtil.intToBool(unk106) }

    @Deprecated int getUnk107() { IOUtil.boolToInt(hidden) }
    @Deprecated void setUnk107(int unk107) { this.hidden = IOUtil.intToBool(unk107) }

    @Deprecated int getUnk108() { IOUtil.boolToInt(alwaysFullAlpha) }
    @Deprecated void setUnk108(int unk108) { this.alwaysFullAlpha = IOUtil.intToBool(unk108) }

    @Deprecated int getUnk109() { IOUtil.boolToInt(savePosition) }
    @Deprecated void setUnk109(int unk109) { this.savePosition = IOUtil.intToBool(unk109) }

    @Deprecated int getUnk110() { IOUtil.boolToInt(saveSize) }
    @Deprecated void setUnk110(int unk110) { this.saveSize = IOUtil.intToBool(unk110) }

    @Deprecated int getUnk111() { title }
    @Deprecated void setUnk111(int unk111) { this.title = unk111 }

    @Deprecated int getUnk112() { IOUtil.boolToInt(resizeFrame) }
    @Deprecated void setUnk112(int unk112) { this.resizeFrame = IOUtil.intToBool(unk112) }

    @Deprecated int getUnk113() { frameSize.intValue() }
    @Deprecated void setUnk113(int unk113) { this.frameSize = FrameSizeType.valueOf(unk113) }

    @Deprecated int getUnk114() { frameDirection.ordinal() }
    @Deprecated void setUnk114(int unk114) { this.frameDirection = DirectionType.valueOf(unk114) }

    @Deprecated int getUnk115() { IOUtil.boolToInt(exitbutton) }
    @Deprecated void setUnk115(int unk115) { this.exitbutton = IOUtil.intToBool(unk115) }

    @Deprecated int getUnk116() { IOUtil.boolToInt(draggable) }
    @Deprecated void setUnk116(int unk116) { this.draggable = IOUtil.intToBool(unk116) }

    @Deprecated int getUnk117() { resizeFrameDirection.ordinal() }
    @Deprecated void setUnk117(int unk117) { this.resizeFrameDirection = DirectionType.valueOf(unk117) }

    @Deprecated float getUnk118() { resizeFrameX }
    @Deprecated void setUnk118(float unk118) { this.resizeFrameX = unk118 }

    @Deprecated float getUnk119() { resizeFrameY }
    @Deprecated void setUnk119(float unk119) { this.resizeFrameY = unk119 }

    @Deprecated float getUnk120() { resizeFrameWidth }
    @Deprecated void setUnk120(float unk120) { this.resizeFrameWidth = unk120 }

    @Deprecated float getUnk121() { resizeFrameHeight }
    @Deprecated void setUnk121(float unk121) { this.resizeFrameHeight = unk121 }

    @Deprecated int getUnk122() { resizeMaxIncrease }
    @Deprecated void setUnk122(int unk122) { this.resizeMaxIncrease = unk122 }

    @Deprecated int getUnk123() { drawerDirection.ordinal() }
    @Deprecated void setUnk123(int unk123) { this.drawerDirection = DirectionType.valueOf(unk123) }

    @Deprecated int getUnk124() { offsetX }
    @Deprecated void setUnk124(int unk124) { this.offsetX = unk124 }

    @Deprecated int getUnk125() { offsetY }
    @Deprecated void setUnk125(int unk125) { this.offsetY = unk125 }

    @Deprecated int getUnk126() { IOUtil.boolToInt(directionFixed) }
    @Deprecated void setUnk126(int unk126) { this.directionFixed = IOUtil.intToBool(unk126) }

    @Deprecated String getUnk127() { ownerWindow }
    @Deprecated void setUnk127(String unk127) { this.ownerWindow = unk127 }

    @Deprecated int getUnk128() { showAnimType.intValue() }
    @Deprecated void setUnk128(int unk128) { this.showAnimType = AnimType.valueOf(unk128) }

    @Deprecated int getUnk129() { hideAnimType.intValue() }
    @Deprecated void setUnk129(int unk129) { this.hideAnimType = AnimType.valueOf(unk129) }

    @Deprecated int getUnk130() { showAnimDirection.ordinal() }
    @Deprecated void setUnk130(int unk130) { this.showAnimDirection = DirectionType.valueOf(unk130) }

    @Deprecated int getUnk131() { showAnimSeconds }
    @Deprecated void setUnk131(int unk131) { this.showAnimSeconds = unk131 }

    @Deprecated int getUnk132() { hideAnimDirection.ordinal() }
    @Deprecated void setUnk132(int unk132) { this.hideAnimDirection = DirectionType.valueOf(unk132) }

    @Deprecated int getUnk133() { hideAnimSeconds }
    @Deprecated void setUnk133(int unk133) { this.hideAnimSeconds = unk133 }

    @Deprecated String getUnk134() { iconName }
    @Deprecated void setUnk134(String unk134) { this.iconName = unk134 }

    @Deprecated int getUnk135() { tooltipIdx }
    @Deprecated void setUnk135(int unk135) { this.tooltipIdx = unk135 }

    @Deprecated int getUnk136() { IOUtil.boolToInt(hookKeyInput) }
    @Deprecated void setUnk136(int unk136) { this.hookKeyInput = IOUtil.intToBool(unk136) }

    @Deprecated String getUnk137() { workingConfiguration }
    @Deprecated void setUnk137(String unk137) { this.workingConfiguration = unk137 }

    @Deprecated String getUnk138() { leftTextureName }
    @Deprecated void setUnk138(String unk138) { this.leftTextureName = unk138 }

    @Deprecated String getUnk139() { midTextureName }
    @Deprecated void setUnk139(String unk139) { this.midTextureName = unk139 }

    @Deprecated String getUnk140() { rightTextureName }
    @Deprecated void setUnk140(String unk140) { this.rightTextureName = unk140 }

    @Deprecated String getUnk141() { minimizeBtnTextureNormal }
    @Deprecated void setUnk141(String unk141) { this.minimizeBtnTextureNormal = unk141 }

    @Deprecated String getUnk142() { minimizeBtnTexturePushed }
    @Deprecated void setUnk142(String unk142) { this.minimizeBtnTexturePushed = unk142 }

    @Deprecated String getUnk143() { closeBtnTextureNormal }
    @Deprecated void setUnk143(String unk143) { this.closeBtnTextureNormal = unk143 }

    @Deprecated String getUnk144() { closeBtnTexturePushed }
    @Deprecated void setUnk144(String unk144) { this.closeBtnTexturePushed = unk144 }

    @Deprecated int getUnk145() { leftBackTextureWidth }
    @Deprecated void setUnk145(int unk145) { this.leftBackTextureWidth = unk145 }

    @Deprecated int getUnk146() { midBackTextureWidth }
    @Deprecated void setUnk146(int unk146) { this.midBackTextureWidth = unk146 }

    @Deprecated List<State> getUnk147() { additionalState }
    @Deprecated void setUnk147(List<State> unk147) { this.additionalState = unk147 }

    @Deprecated int getUnk149() { IOUtil.boolToInt(useParentClipRect) }
    @Deprecated void setUnk149(int unk149) { this.useParentClipRect = IOUtil.intToBool(unk149) }
    // @formatter:on
}
