package ewriters
import grails.testing.gorm.DataTest
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UsuarioSpec extends Specification implements DomainUnitTest<Usuario>, DataTest {

    EspacioPrincipal espacioPrincipal

    def setup() {
        mockDomain EspacioPrincipal

        espacioPrincipal = new EspacioPrincipal()
    }

    void "test escribir una narracion"() {
    	given: "un escritor"
    		def usuario = new Usuario("Alice")
    	when: "escriba una narracion"
    		def narracion = new Narracion("Cuento de Alice",
    			"Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    			"TERROR",
    			50)
            usuario.escribirNarracion(narracion, espacioPrincipal)
    	then: "se le asigna esa narracion"
    		narracion == usuario.narraciones.find { it.titulo == "Cuento de Alice" }
    }

    void "test crear un concurso"() {
    	given: "un creador de concursos"
    		def usuario = new Usuario("Bob")
    	when: "crea un concurso"
    		def concurso = new Concurso("Concurso de Bob", 
    			"Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    			200,
    			50,
    			"NO_FICCION")
            usuario.addToConcursos(concurso)
    	then: "se le asigna ese concurso"
    		usuario == concurso.creador
    }

    void "test crear un taller"() {
        given: "un creador del taller"
            def usuario = new Usuario("Alice")
        when: "crea un taller"
            def taller = new Taller("Taller de Alice")
            usuario.addToTalleres(taller)
        then: "se le asigna ese taller"
            usuario == taller.creador
    }

    void "test indicar me gusta en una historia aumenta la reputacion del escritor"() {
        given: "una narracion y un lector que no dio me gusta previamente"
            def narracion = new Narracion("Narracion de Alice",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    "CIENCIA_FICCION"
            )
            def escritor = new Usuario("Alice")
            escritor.escribirNarracion(narracion, espacioPrincipal)
            def lector = new Usuario("Bob")
        when: "el lector indique que le gusta la narracion"
            MeGusta meGusta = new MeGusta(lector)
            narracion.agregarMeGusta(meGusta)
        then: "aumenta la reputacion del escritor"
            escritor.reputacion == 1
    }

    void "test indicar me gusta en una historia donde ya se dio"() {
        given: "una narracion y un lector que le dio me gusta previamente"
            def narracion = new Narracion("Narracion de Alice",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    "CIENCIA_FICCION"
            )
            def escritor = new Usuario("Alice")
            escritor.escribirNarracion(narracion, espacioPrincipal)
            def lector = new Usuario("Bob")
            MeGusta meGusta = new MeGusta(lector)
            narracion.agregarMeGusta(meGusta)
        when: "el lector indique que le gusta la narracion"
            narracion.agregarMeGusta(meGusta)
        then: "ocurrira un error al intentarlo nuevamente"
            escritor.reputacion == 1
            def exception = thrown(IllegalStateException)
            exception != null
    }

    void "test quitar el me gusta de una narracion exitosamente"() {
    	given: "una narracion y un lector que le dio me gusta previamente"
           def narracion = new Narracion("Narracion de Alice",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    "CIENCIA_FICCION"
            )
            def escritor = new Usuario("Alice")
            escritor.escribirNarracion(narracion, espacioPrincipal)
            def lector = new Usuario("Bob")
            MeGusta meGusta = new MeGusta(lector)
            narracion.agregarMeGusta(meGusta)
        when: "el lector quite el me gusta"
        	narracion.removerMeGusta(meGusta)
        then: "disminuira la reputacion del escritor"
        	escritor.reputacion == 0
    }

    void "test quitar el me gusta de una narracion a la que no le habia dado previamente"() {
    	given: "una narracion y un lector que no dio me gusta previamente"
            def narracion = new Narracion("Narracion de Alice",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    "CIENCIA_FICCION"
            )
            def escritor = new Usuario("Alice")
            escritor.escribirNarracion(narracion, espacioPrincipal)
            def lector = new Usuario("Bob")
        when: "el lector quite el me gusta"
        	MeGusta meGusta = new MeGusta(lector)
            narracion.removerMeGusta(meGusta)
        then: "no disminuria la reputacion del escritor"
        	escritor.reputacion == 0
        	def exception = thrown(IllegalStateException)
            exception != null
    }

    void "test ganar un concurso le otorga premios al usuario ganador"() {
        given: "un concurso, un escritor y su narracion"
            def recompensaPorGanarElConcurso = 200
            def concurso = new Concurso("Concurso de Alice",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                recompensaPorGanarElConcurso,
                0,
                "FANTASIA")
            def participante = new Usuario("Bob")
            def narracion = new Narracion("Narracion de Bob",
                "Lorem ipsum dolor sit amet",
                "FANTASIA")
            participante.escribirNarracion(narracion, concurso)
            def reputacionPrevia = participante.reputacion
        when: "el escritor gane el concurso"
            concurso.finalizar()
        then: "el escritor aumento su reputacion"
            participante.reputacion == reputacionPrevia + recompensaPorGanarElConcurso
    }
}
