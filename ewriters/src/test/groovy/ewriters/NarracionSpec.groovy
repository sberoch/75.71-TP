package ewriters

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class NarracionSpec extends Specification implements DomainUnitTest<Narracion> {

    Usuario escritor

    def setup() {
        escritor = new Usuario("Alice")
    }

    def cleanup() {
    }

    void "test crear comentario con reputacion suficiente"() {
        given: "una narracion y un lector con suficiente reputacion"
            def narracion = new Narracion(
                    escritor,
                    "Narracion de Alice",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    Narracion.Genero.CIENCIA_FICCION,
                    60
            )
            def lector = new Usuario("Bob")
            lector.reputacion = 80
        when: "el lector comente la narracion"
            def comentario = new Comentario(lector, "Comentario de Bob")
            narracion.agregarComentario(comentario)
        then: "el comentario se agrega correctamente"
            narracion.comentarios.contains(comentario)

    }
}
