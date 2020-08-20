package ewriters

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class NarracionSpec extends Specification implements DomainUnitTest<Narracion> {

    Usuario escritor

    def setup() {
        escritor = new Usuario("Alice")
    }

    void "test dar me gusta"() {
        given: "una narracion y un lector"
            def narracion = new Narracion(
                    "Narracion de Alice",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    "CIENCIA_FICCION"
            )
            escritor.addToNarraciones(narracion)
            def lector = new Usuario("Bob")
        when: "se da me gusta"
            MeGusta meGusta = new MeGusta(lector)
            narracion.agregarMeGusta(meGusta)
        then: "la cantidad de me gusta de la narracion aumenta"
            narracion.cantMeGusta == 1
    }

    void "test dar me gusta cuando ya se habia dado"() {
        given: "una narracion y un lector y un me gusta de ese lector"
            def narracion = new Narracion(
                    "Narracion de Alice",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    "CIENCIA_FICCION"
            )
            escritor.addToNarraciones(narracion)
            def lector = new Usuario("Bob")
            MeGusta meGusta = new MeGusta(lector)
            narracion.agregarMeGusta(meGusta)
        when: "se da me gusta"
            narracion.agregarMeGusta(meGusta)
        then: "se lanza excepcion y no varia la cantidad de me gusta"
            narracion.cantMeGusta == 1
            def exception = thrown(IllegalStateException)
            exception != null 
            
    }

    void "test quitar me gusta"() {
        given: "una narracion y un lector y un me gusta de ese lector"
            def narracion = new Narracion(
                    "Narracion de Alice",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    "CIENCIA_FICCION"
            )
            escritor.addToNarraciones(narracion)
            def lector = new Usuario("Bob")
            MeGusta meGusta = new MeGusta(lector)
            narracion.agregarMeGusta(meGusta)
        when: "se quita el me gusta"
            narracion.removerMeGusta(meGusta)
        then: "la cantidad de me gusta de la narracion dismininuye"
            narracion.cantMeGusta == 0
    }

    void "test quitar me gusta cuando no se habia dado"() {
        given: "una narracion y un lector"
            def narracion = new Narracion(
                    "Narracion de Alice",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    "CIENCIA_FICCION"
            )
            escritor.addToNarraciones(narracion)
            def lector = new Usuario("Bob")
        when: "se quita el me gusta"
            MeGusta meGusta = new MeGusta(lector)
            narracion.removerMeGusta(meGusta)
        then: "se lanza excepcion y no varia la cantidad de me gusta"
            narracion.cantMeGusta == 0
            def exception = thrown(IllegalStateException)
            exception != null 
    }

    void "test crear comentario con reputacion suficiente"() {
        given: "una narracion y un lector con suficiente reputacion"
            def narracion = new Narracion(
                    "Narracion de Alice",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    "CIENCIA_FICCION",
                    60
            )
            escritor.addToNarraciones(narracion)
            def lector = new Usuario("Bob")
            lector.reputacion = 80
        when: "el lector comente la narracion"
            def comentario = new Comentario("Comentario de Bob", null)
            lector.addToComentarios(comentario)
            narracion.agregarComentario(comentario)
        then: "el comentario se agrega correctamente"
            narracion.comentarios.contains(comentario)
    }

    void "test crear comentario sin reputacion suficiente"() {
        given: "una narracion y un lector sin suficiente reputacion"
            def narracion = new Narracion(
                    "Narracion de Alice",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    "CIENCIA_FICCION",
                    60
            )
            escritor.addToNarraciones(narracion)
            def lector = new Usuario("Bob")
            lector.reputacion = 40
        when: "el lector comente la narracion"
            def comentario = new Comentario("Comentario de Bob", null)
            lector.addToComentarios(comentario)
            narracion.agregarComentario(comentario)
        then: "no podra comentar y no se agregara el comentario"
            !narracion.comentarios
            def exception = thrown(IllegalStateException)
            exception != null 
    }
}
