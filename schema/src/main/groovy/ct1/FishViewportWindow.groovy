package ct1

import acmi.l2.clientmod.util.defaultio.DefaultIO
import groovy.transform.CompileStatic

@DefaultIO
@CompileStatic
class FishViewportWindow extends DefaultProperty {
    String texBack
    String texClock
    String texFishHPBar
    String texFishHPBarBack
    String texFishFakeHPBarWarning
    String texFishingEffect
    String texIconPumping
    String texIconReeling
}
