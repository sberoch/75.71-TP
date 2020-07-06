package ewriters

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UsuarioSpec extends Specification implements DomainUnitTest<Usuario> {

    def setup() {
    }

    def cleanup() {
    }

    void "test escribir una narracion"() {
    	given: "un escritor"
    		def usuario = new Usuario("Alice")
    	when: "escriba una narracion"
    		def narracion = usuario.escribirNarracion("Cuento de Alice",
    			"Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    			Narracion.Genero.TERROR,
    			50)
    	then: "se le asigna esa narracion"
    		narracion == usuario.narraciones.find { it.titulo == "Cuento de Alice" }
    }

    void "test crear un concurso"() {
    	given: "un creador de concursos"
    		def usuario = new Usuario("Bob")
    	when: "crea un concurso"
    		def concurso = usuario.crearConcurso(
    			"Concurso de Bob", 
    			"Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    			200,
    			50,
    			Narracion.Genero.NO_FICCION)
    	then: "se le asigna ese concurso"
    		usuario == concurso.escritor
    }

    void "test indicar me gusta en una historia exitosamente"() {
        given: "una narracion y un lector que no dio me gusta previamente"
            def narracion = new Narracion(
                    new Usuario("Alice"),
                    "Narracion de Alice",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    Narracion.Genero.CIENCIA_FICCION,
                    60
            )
            def lector = new Usuario("Bob")
        when: "el lector indique que le gusta la narracion"
            lector.meGusta(narracion)
            narracion.cantMeGusta == 1
        then: "se agregara el me gusta correctamente"
            lector.narracionesConMeGusta.contains(narracion)
    }

    void "test indicar me gusta en una historia donde ya se dio"() {
        given: "una narracion y un lector que le dio me gusta previamente"
            def narracion = new Narracion(
                    new Usuario("Alice"),
                    "Narracion de Alice",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    Narracion.Genero.CIENCIA_FICCION,
                    60
            )
            def lector = new Usuario("Bob")
            lector.meGusta(narracion)
        when: "el lector indique que le gusta la narracion"
            lector.meGusta(narracion)
        then: "ocurrira un error al intentarlo nuevamente"
            narracion.cantMeGusta == 1
            def exception = thrown(IllegalStateException)
            exception != null
    }

    void "test quitar el me gusta de una narracion exitosamente"() {
    	given: "una narracion y un lector que le dio me gusta previamente"
            def narracion = new Narracion(
                    new Usuario("Alice"),
                    "Narracion de Alice",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    Narracion.Genero.CIENCIA_FICCION,
                    60
            )
            def lector = new Usuario("Bob")
            lector.meGusta(narracion)
        when: "el lector quite el me gusta"
        	lector.removerMeGusta(narracion)
        then: "ya no aparecera entre sus narraciones con me gusta"
        	!lector.narracionesConMeGusta.contains(narracion)
        	narracion.cantMeGusta == 0
    }

    void "test quitar el me gusta de una narracion a la que no le habia dado previamente"() {
    	given: "una narracion y un lector que no dio me gusta previamente"
            def narracion = new Narracion(
                    new Usuario("Alice"),
                    "Narracion de Alice",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    Narracion.Genero.CIENCIA_FICCION,
                    60
            )
            def lector = new Usuario("Bob")
        when: "el lector quite el me gusta"
        	lector.removerMeGusta(narracion)
        then: "ya no aparecera entre sus narraciones con me gusta"
        	narracion.cantMeGusta == 0
        	def exception = thrown(IllegalStateException)
            exception != null
    }
}
