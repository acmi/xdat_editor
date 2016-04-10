package ct26

import acmi.l2.clientmod.l2resources.Tex
import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic
import groovyx.javafx.beans.FXBindable

@FXBindable
@DefaultIO
@CompileStatic
class FishViewportWindow extends DefaultProperty {
    @Tex
    String texBack = 'l2ui_ct1.Misc.Misc_df_innershadow'
    @Tex
    String texClock = 'l2ui_ch3.FishingWnd.fishing_clockicon'
    @Tex
    String texFishHPBar = 'l2ui_ch3.FishingWnd.fishing_bar1'
    @Tex
    String texFishHPBarBack = 'l2ui_ch3.FishingWnd.fishing_bar2'
    @Tex
    String texFishFakeHPBarWarning = 'l2ui_ch3.PlayerStatusWnd.ps_hpbarwarn1'
    @Tex
    String texFishingEffect = 'l2ui_ch3.FishingWnd.fishing_effect'
    @Tex
    String texIconPumping = 'Icon.skill_i.skill1313'
    @Tex
    String texIconReeling = 'Icon.skill_i.skill1314'
}
