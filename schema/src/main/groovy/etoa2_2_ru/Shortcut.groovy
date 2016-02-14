package etoa2_2_ru

import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.Type

class Shortcut implements IOEntity {
    String unk0
    String unk1
    int unk2
    @Type(Action.class)
    List<Action> actions = []
    int unk9

    static class Action implements IOEntity {
        InputKey key_1 = InputKey.IK_None
        InputKey key_2 = InputKey.IK_None
        InputKey key_3 = InputKey.IK_None
        String action
        InputAction input_action = InputAction.None

        @Override
        Action read(InputStream input) {
            use(IOUtil) {
                key_1 = InputKey.values()[input.readInt()]
                key_2 = InputKey.values()[input.readInt()]
                key_3 = InputKey.values()[input.readInt()]
                action = input.readString()
                input_action = InputAction.values()[input.readInt()]
            }
            this
        }

        @Override
        Action write(OutputStream output) {
            use(IOUtil) {
                output.writeInt(key_1.ordinal())
                output.writeInt(key_2.ordinal())
                output.writeInt(key_3.ordinal())
                output.writeString(action)
                output.writeInt(input_action.ordinal())
            }
            this
        }

        @Override
        String toString() {
            return action
        }

        enum InputKey {
            IK_None('None'),
            IK_LeftMouse('LeftMouse'),
            IK_RightMouse('RightMouse'),
            IK_Cancel('Cancel'),
            IK_MiddleMouse('MiddleMouse'),
            IK_Unknown05('Unknown05'),
            IK_Unknown06('Unknown06'),
            IK_Unknown07('Unknown07'),
            IK_Backspace('Backspace'),
            IK_Tab('Tab'),
            IK_Unknown0A('Unknown0A'),
            IK_Unknown0B('Unknown0B'),
            IK_Unknown0C('Unknown0C'),
            IK_Enter('Enter'),
            IK_Unknown0E('Unknown0E'),
            IK_Unknown0F('Unknown0F'),
            IK_Shift('Shift'),
            IK_Ctrl('Ctrl'),
            IK_Alt('Alt'),
            IK_Pause('Pause'),
            IK_CapsLock('CapsLock'),
            IK_Unknown15('Unknown15'),
            IK_Unknown16('Unknown16'),
            IK_Unknown17('Unknown17'),
            IK_Unknown18('Unknown18'),
            IK_Unknown19('Unknown19'),
            IK_Unknown1A('Unknown1A'),
            IK_Escape('Escape'),
            IK_Unknown1C('Unknown1C'),
            IK_Unknown1D('Unknown1D'),
            IK_Unknown1E('Unknown1E'),
            IK_Unknown1F('Unknown1F'),
            IK_Space('Space'),
            IK_PageUp('PageUp'),
            IK_PageDown('PageDown'),
            IK_End('End'),
            IK_Home('Home'),
            IK_Left('Left'),
            IK_Up('Up'),
            IK_Right('Right'),
            IK_Down('Down'),
            IK_Select('Select'),
            IK_Print('Print'),
            IK_Execute('Execute'),
            IK_PrintScrn('PrintScrn'),
            IK_Insert('Insert'),
            IK_Delete('Delete'),
            IK_Help('Help'),
            IK_0('0'),
            IK_1('1'),
            IK_2('2'),
            IK_3('3'),
            IK_4('4'),
            IK_5('5'),
            IK_6('6'),
            IK_7('7'),
            IK_8('8'),
            IK_9('9'),
            IK_Unknown3A('Unknown3A'),
            IK_Unknown3B('Unknown3B'),
            IK_Unknown3C('Unknown3C'),
            IK_Unknown3D('Unknown3D'),
            IK_Unknown3E('Unknown3E'),
            IK_Unknown3F('Unknown3F'),
            IK_Unknown40('Unknown40'),
            IK_A('A'),
            IK_B('B'),
            IK_C('C'),
            IK_D('D'),
            IK_E('E'),
            IK_F('F'),
            IK_G('G'),
            IK_H('H'),
            IK_I('I'),
            IK_J('J'),
            IK_K('K'),
            IK_L('L'),
            IK_M('M'),
            IK_N('N'),
            IK_O('O'),
            IK_P('P'),
            IK_Q('Q'),
            IK_R('R'),
            IK_S('S'),
            IK_T('T'),
            IK_U('U'),
            IK_V('V'),
            IK_W('W'),
            IK_X('X'),
            IK_Y('Y'),
            IK_Z('Z'),
            IK_Unknown5B('Unknown5B'),
            IK_Unknown5C('Unknown5C'),
            IK_Unknown5D('Unknown5D'),
            IK_Unknown5E('Unknown5E'),
            IK_Unknown5F('Unknown5F'),
            IK_NumPad0('NumPad0'),
            IK_NumPad1('NumPad1'),
            IK_NumPad2('NumPad2'),
            IK_NumPad3('NumPad3'),
            IK_NumPad4('NumPad4'),
            IK_NumPad5('NumPad5'),
            IK_NumPad6('NumPad6'),
            IK_NumPad7('NumPad7'),
            IK_NumPad8('NumPad8'),
            IK_NumPad9('NumPad9'),
            IK_GreyStar('GreyStar'),
            IK_GreyPlus('GreyPlus'),
            IK_Separator('Separator'),
            IK_GreyMinus('GreyMinus'),
            IK_NumPadPeriod('NumPadPeriod'),
            IK_GreySlash('GreySlash'),
            IK_F1('F1'),
            IK_F2('F2'),
            IK_F3('F3'),
            IK_F4('F4'),
            IK_F5('F5'),
            IK_F6('F6'),
            IK_F7('F7'),
            IK_F8('F8'),
            IK_F9('F9'),
            IK_F10('F10'),
            IK_F11('F11'),
            IK_F12('F12'),
            IK_F13('F13'),
            IK_F14('F14'),
            IK_F15('F15'),
            IK_F16('F16'),
            IK_F17('F17'),
            IK_F18('F18'),
            IK_F19('F19'),
            IK_F20('F20'),
            IK_F21('F21'),
            IK_F22('F22'),
            IK_F23('F23'),
            IK_F24('F24'),
            IK_Unknown88('Unknown88'),
            IK_Unknown89('Unknown89'),
            IK_Unknown8A('Unknown8A'),
            IK_Unknown8B('Unknown8B'),
            IK_Unknown8C('Unknown8C'),
            IK_Unknown8D('Unknown8D'),
            IK_Unknown8E('Unknown8E'),
            IK_Unknown8F('Unknown8F'),
            IK_NumLock('NumLock'),
            IK_ScrollLock('ScrollLock'),
            IK_Unknown92('Unknown92'),
            IK_Unknown93('Unknown93'),
            IK_Unknown94('Unknown94'),
            IK_Unknown95('Unknown95'),
            IK_Unknown96('Unknown96'),
            IK_Unknown97('Unknown97'),
            IK_Unknown98('Unknown98'),
            IK_Unknown99('Unknown99'),
            IK_Unknown9A('Unknown9A'),
            IK_Unknown9B('Unknown9B'),
            IK_Unknown9C('Unknown9C'),
            IK_Unknown9D('Unknown9D'),
            IK_Unknown9E('Unknown9E'),
            IK_Unknown9F('Unknown9F'),
            IK_LShift('LShift'),
            IK_RShift('RShift'),
            IK_LControl('LControl'),
            IK_RControl('RControl'),
            IK_UnknownA4('UnknownA4'),
            IK_UnknownA5('UnknownA5'),
            IK_UnknownA6('UnknownA6'),
            IK_UnknownA7('UnknownA7'),
            IK_UnknownA8('UnknownA8'),
            IK_UnknownA9('UnknownA9'),
            IK_UnknownAA('UnknownAA'),
            IK_UnknownAB('UnknownAB'),
            IK_UnknownAC('UnknownAC'),
            IK_UnknownAD('UnknownAD'),
            IK_UnknownAE('UnknownAE'),
            IK_UnknownAF('UnknownAF'),
            IK_UnknownB0('UnknownB0'),
            IK_UnknownB1('UnknownB1'),
            IK_UnknownB2('UnknownB2'),
            IK_UnknownB3('UnknownB3'),
            IK_UnknownB4('UnknownB4'),
            IK_UnknownB5('UnknownB5'),
            IK_UnknownB6('UnknownB6'),
            IK_UnknownB7('UnknownB7'),
            IK_UnknownB8('UnknownB8'),
            IK_Unicode('Unicode'),
            IK_Semicolon('Semicolon'),
            IK_Equals('Equals'),
            IK_Comma('Comma'),
            IK_Minus('Minus'),
            IK_Period('Period'),
            IK_Slash('Slash'),
            IK_Tilde('Tilde'),
            IK_UnknownC1('UnknownC1'),
            IK_UnknownC2('UnknownC2'),
            IK_UnknownC3('UnknownC3'),
            IK_UnknownC4('UnknownC4'),
            IK_UnknownC5('UnknownC5'),
            IK_UnknownC6('UnknownC6'),
            IK_UnknownC7('UnknownC7'),
            IK_Joy1('Joy1'),
            IK_Joy2('Joy2'),
            IK_Joy3('Joy3'),
            IK_Joy4('Joy4'),
            IK_Joy5('Joy5'),
            IK_Joy6('Joy6'),
            IK_Joy7('Joy7'),
            IK_Joy8('Joy8'),
            IK_Joy9('Joy9'),
            IK_Joy10('Joy10'),
            IK_Joy11('Joy11'),
            IK_Joy12('Joy12'),
            IK_Joy13('Joy13'),
            IK_Joy14('Joy14'),
            IK_Joy15('Joy15'),
            IK_Joy16('Joy16'),
            IK_UnknownD8('UnknownD8'),
            IK_UnknownD9('UnknownD9'),
            IK_UnknownDA('UnknownDA'),
            IK_LeftBracket('LeftBracket'),
            IK_Backslash('Backslash'),
            IK_RightBracket('RightBracket'),
            IK_SingleQuote('SingleQuote'),
            IK_UnknownDF('UnknownDF'),
            IK_UnknownE0('UnknownE0'),
            IK_UnknownE1('UnknownE1'),
            IK_UnknownE2('UnknownE2'),
            IK_UnknownE3('UnknownE3'),
            IK_MouseX('MouseX'),
            IK_MouseY('MouseY'),
            IK_MouseZ('MouseZ'),
            IK_MouseW('MouseW'),
            IK_JoyU('JoyU'),
            IK_JoyV('JoyV'),
            IK_JoySlider1('JoySlider1'),
            IK_JoySlider2('JoySlider2'),
            IK_MouseWheelUp('MouseWheelUp'),
            IK_MouseWheelDown('MouseWheelDown'),
            IK_Unknown10E('Unknown10E'),
            UK_Unknown10F('Unknown10F'),
            IK_JoyX('JoyX'),
            IK_JoyY('JoyY'),
            IK_JoyZ('JoyZ'),
            IK_JoyR('JoyR'),
            IK_UnknownF4('UnknownF4'),
            IK_UnknownF5('UnknownF5'),
            IK_Attn('Attn'),
            IK_CrSel('CrSel'),
            IK_ExSel('ExSel'),
            IK_ErEof('ErEof'),
            IK_Play('Play'),
            IK_Zoom('Zoom'),
            IK_NoName('NoName'),
            IK_PA1('PA1'),
            IK_OEMClear('OEMClear')

            private String text

            InputKey(String text){
                this.text = text
            }

            @Override
            String toString() {
                text
            }
        }

        enum InputAction {
            None,    // Not performing special input processing.
            Press,   // Handling a keypress or button press.
            Hold,    // Handling holding a key or button.
            Release, // Handling a key or button release.
            Axis,    // Handling analog axis movement.
        }
    }

    @Override
    Shortcut read(InputStream input) {
        use(IOUtil) {
            unk0 = input.readString()
            unk1 = input.readString()
            unk2 = input.readInt()
            int count = input.readInt()
            for (int i = 0; i < count; i++) {
                actions.add(new Action().read(input))
            }
            unk9 = input.readInt()
        }
        this
    }

    @Override
    Shortcut write(OutputStream output) {
        use(IOUtil) {
            output.writeString(unk0)
            output.writeString(unk1)
            output.writeInt(unk2)
            output.writeInt(actions.size())
            for (Action shortcutUnk : actions)
                shortcutUnk.write(output)
            output.writeInt(unk9)
        }
        this
    }

    @Override
    String toString() {
        return unk0
    }
}
