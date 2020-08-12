package ewriters

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ConcursoSpec extends Specification implements DomainUnitTest<Concurso> {

	def creadorDelConcurso
    def usuario 

    def setup() {
    	creadorDelConcurso = new Usuario("Alice")
        usuario = new Usuario("Bob")
    }

    void "test participacion exitosa en concurso"() {
        given: "un concurso"
        	def concurso = new Concurso("Concurso de Alice",
        		"Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        		200,
        		50,
        		Narracion.Genero.FANTASIA)
        when: "un usuario quiera participar con los requerimientos minimos"
        	usuario.reputacion = 100
        	def narracion = new Narracion("Narracion de Bob",
        		"Lorem ipsum dolor sit amet",
        		Narracion.Genero.FANTASIA)
        	concurso.agregarNarracion(narracion, usuario)
        then: "se agregara exitosamente la narracion"
        	concurso.narraciones.contains(narracion)
    }

    void "test participacion fallida en concurso"() {
        given: "un concurso"
        	def concurso = new Concurso(
        		"Concurso de Alice",
        		"Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        		200,
        		50,
        		Narracion.Genero.FANTASIA)
        when: "se quiera participar sin los requerimientos minimos"
        	usuario.reputacion = 25
        	def narracion = new Narracion("Narracion de Bob",
        		"Lorem ipsum dolor sit amet",
        		Narracion.Genero.TERROR)
        	concurso.agregarNarracion(narracion, usuario)
        then: "no se podra participar con esa narracion"
        	def exception = thrown(IllegalStateException)
        	exception != null 
    }

    void "test el concurso dura una semana"() {
        given: "un concurso"
            def concurso = new Concurso("Concurso de Alice",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                200,
                50,
                Narracion.Genero.FANTASIA)
        expect: "el concurso no termino si paso menos de una semana"
            !concurso.terminado()

    }

    void "test la narracion con mas me gusta gana el concurso"() {
        given: "un concurso y dos narraciones con diferente cantidad de me gusta"
            def otroUsuario = new Usuario("Charlie")
            def concurso = new Concurso("Concurso de Alice",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                200,
                0,
                Narracion.Genero.FANTASIA)
            def narracion_1 = new Narracion("Narracion de Bob",
                "Lorem ipsum dolor sit amet",
                Narracion.Genero.FANTASIA)
            def narracion_2 = new Narracion("Narracion de Charlie",
                "Lorem ipsum dolor sit amet",
                Narracion.Genero.FANTASIA)
            narracion_1.escritor = usuario
            narracion_2.escritor = otroUsuario
            concurso.agregarNarracion(narracion_1, usuario)
            concurso.agregarNarracion(narracion_2, otroUsuario)
            narracion_1.cantMeGusta = 3
            narracion_2.cantMeGusta = 5
        when: "se determine el ganador"
            concurso.finalizar()
        then: "ganara la narracion con mas me gusta"
            concurso.narraciones.max() == narracion_2
    }
}
