package ewriters

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class EspacioPrincipalSpec extends Specification implements DomainUnitTest<EspacioPrincipal> {

    def escritor
    def setup() {
        escritor = new Usuario("Bob")
    }

    void "test un escritor agrega una narracion"() {
        given:"un espacio principal"
            EspacioPrincipal espacioPrincipal = new EspacioPrincipal()
        when:"el escritor agrega una narracion"
            Narracion narracion = new Narracion("Narracion de Bob",
                "Texto de la narracion",
                "NO_FICCION"
            )
            espacioPrincipal.agregarNarracion(narracion, escritor)
        then:"la narracion se agrega al espacio principal"
            espacioPrincipal.narraciones.size() == 1
    }
}
