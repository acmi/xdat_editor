import acmi.l2.clientmod.util.IOEntity
import org.junit.Test

import static org.junit.Assert.assertArrayEquals

class InterfaceTest {
    void test(Class<? extends IOEntity> clazz, String resource) {
        def originalBytes = getClass().getResourceAsStream(resource).bytes

        def xdat = clazz.newInstance()

        xdat.read(new ByteArrayInputStream(originalBytes))

        def baos = new ByteArrayOutputStream(originalBytes.length)

        xdat.write(baos)

        def resultBytes = baos.toByteArray()

        assertArrayEquals(originalBytes, resultBytes)
    }

    @Test
    void Interlude() {
        test(ct0.XDAT, 'interface/Interface_interlude.xdat')
    }

    @Test
    void Kamael() {
        test(ct1.XDAT, 'interface/Interface_kamael.xdat')
    }

    @Test
    void Hellbound() {
        test(ct15.XDAT, 'interface/Interface_hellbound.xdat')
    }

    @Test
    void GraciaPart1() {
        test(ct22.XDAT, 'interface/Interface_part1.xdat')
    }

    @Test
    void GraciaPart2() {
        test(ct22.XDAT, 'interface/Interface_part2.xdat')
    }

    @Test
    void GraciaFinal() {
        test(ct23.XDAT, 'interface/Interface_final.xdat')
    }

    @Test
    void GraciaEpilogue() {
        test(ct24.XDAT, 'interface/Interface_epilogue.xdat')
    }

    @Test
    void Freya() {
        test(ct25.XDAT, 'interface/Interface_freya.xdat')
    }

    @Test
    void HighFive() {
        test(ct26.XDAT, 'interface/Interface_high_five.xdat')
    }

    @Test
    void Awakening() {
        test(god25.XDAT, 'interface/Interface_awakening.xdat')
    }

    @Test
    void Harmony() {
        test(god25.XDAT, 'interface/Interface_harmony.xdat')
    }

    @Test
    void Tauti() {
        test(god25.XDAT, 'interface/Interface_tauti.xdat')
    }

    @Test
    void GloryDays() {
        test(god25.XDAT, 'interface/Interface_glory_days.xdat')
    }

    @Test
    void Lindvior() {
        test(god3.XDAT, 'interface/Interface_lindvior.xdat')
    }

    @Test
    void Epeisodion() {
        test(god35.XDAT, 'interface/Interface_epeisodion.xdat')
    }

    @Test
    void Ertheia() {
        test(etoa2.XDAT, 'interface/Interface_ertheia.xdat')
    }

    @Test
    void TheBeginningOfJourney() {
        test(etoa2.XDAT, 'interface/Interface_the_beginning_of_the_journey.xdat')
    }

    @Test
    void NewGuardianOfAstatine() {
        test(etoa2_3.XDAT, 'interface/Interface_new_guardian_of_astatine.xdat')
    }

    @Test
    void HymnOfTheSoul() {
        test(etoa2_4.XDAT, 'interface/Interface_hymn_of_the_soul.xdat')
    }

    @Test
    void WillOfTheAncients() {
        test(etoa2_4.XDAT, 'interface/Interface_will_of_the_ancients.xdat')
    }

    @Test
    void Helios() {
        test(etoa3.XDAT, 'interface/Interface_helios.xdat')
    }

    @Test
    void Helios2() {
        test(etoa3_.XDAT, 'interface/Interface_helios+.xdat')
    }
}
