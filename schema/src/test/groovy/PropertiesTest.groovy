import acmi.l2.clientmod.util.SubclassManager
import org.junit.Test

import java.beans.Introspector
import java.beans.PropertyDescriptor

class PropertiesTest {
    void test(classes){
        classes.each{
            def instance = it.newInstance()

            def beanInfo = Introspector.getBeanInfo(it)
            def propertyDescriptors = beanInfo.getPropertyDescriptors()
            for (PropertyDescriptor pd : propertyDescriptors)
                pd.getReadMethod().invoke(instance)
        }
    }

    @Test
    void Interlude() {
        new ct0.XDAT()
        test(SubclassManager.instance.getClassWithAllSubclasses(ct0.DefaultProperty))
    }

    @Test
    void Kamael() {
        new ct1.XDAT()
        test(SubclassManager.instance.getClassWithAllSubclasses(ct1.DefaultProperty))
    }

    @Test
    void Hellbound() {
        new ct15.XDAT()
        test(SubclassManager.instance.getClassWithAllSubclasses(ct15.DefaultProperty))
    }

    @Test
    void GraciaPart1() {
        new ct22.XDAT()
        test(SubclassManager.instance.getClassWithAllSubclasses(ct22.DefaultProperty))
    }

    @Test
    void GraciaFinal() {
        new ct23.XDAT()
        test(SubclassManager.instance.getClassWithAllSubclasses(ct23.DefaultProperty))
    }

    @Test
    void GraciaEpilogue() {
        new ct24.XDAT()
        test(SubclassManager.instance.getClassWithAllSubclasses(ct24.DefaultProperty))
    }

    @Test
    void Freya() {
        new ct25.XDAT()
        test(SubclassManager.instance.getClassWithAllSubclasses(ct25.DefaultProperty))
    }

    @Test
    void HighFive() {
        new ct26.XDAT()
        test(SubclassManager.instance.getClassWithAllSubclasses(ct26.DefaultProperty))
    }

    @Test
    void Awakening() {
        new god25.XDAT()
        test(SubclassManager.instance.getClassWithAllSubclasses(god25.DefaultProperty))
    }

    @Test
    void Lindvior() {
        new god3.XDAT()
        test(SubclassManager.instance.getClassWithAllSubclasses(god3.DefaultProperty))
    }

    @Test
    void Epeisodion() {
        new god35.XDAT()
        test(SubclassManager.instance.getClassWithAllSubclasses(god35.DefaultProperty))
    }

    @Test
    void Ertheia() {
        new etoa2.XDAT()
        test(SubclassManager.instance.getClassWithAllSubclasses(etoa2.DefaultProperty))
    }

    @Test
    void TheBeginningOfJourneyRu() {
        new etoa2_2_ru.XDAT()
        test(SubclassManager.instance.getClassWithAllSubclasses(etoa2_2_ru.DefaultProperty))
    }

    @Test
    void NewGuardianOfAstatine() {
        new etoa2_3.XDAT()
        test(SubclassManager.instance.getClassWithAllSubclasses(etoa2_3.DefaultProperty))
    }

    @Test
    void HymnOfTheSoulRu() {
        new etoa2_3_ru.XDAT()
        test(SubclassManager.instance.getClassWithAllSubclasses(etoa2_3_ru.DefaultProperty))
    }

    @Test
    void HymnOfTheSoul() {
        new etoa2_4.XDAT()
        test(SubclassManager.instance.getClassWithAllSubclasses(etoa2_4.DefaultProperty))
    }

    @Test
    void Helios() {
        new etoa3.XDAT()
        test(SubclassManager.instance.getClassWithAllSubclasses(etoa3.DefaultProperty))
    }

    @Test
    void Helios2() {
        new etoa3_.XDAT()
        test(SubclassManager.instance.getClassWithAllSubclasses(etoa3_.DefaultProperty))
    }

    @Test
    void Helios3() {
        new etoa3__.XDAT()
        test(SubclassManager.instance.getClassWithAllSubclasses(etoa3__.DefaultProperty))
    }

    @Test
    void GrandCrusade() {
        new etoa4.XDAT()
        test(SubclassManager.instance.getClassWithAllSubclasses(etoa4.DefaultProperty))
    }
}
