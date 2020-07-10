package ewriters

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ConcursoSpec extends Specification implements DomainUnitTest<Concurso> {

	def creadorDelConcurso

    def setup() {
    	creadorDelConcurso = new Usuario("Alice")
    }

    def cleanup() {
    }

    void "test participacion exitosa en concurso"() {
        given: "un concurso"
        	def concurso = new Concurso(creadorDelConcurso,
        		"Concurso de Alice",
        		"Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        		200,
        		50,
        		Narracion.Genero.FANTASIA)
        when: "se quiera participar con los requerimientos minimos"
        	def participante = new Usuario("Bob")
        	participante.reputacion = 100
        	def narracion = new Narracion(participante,
        		"Narracion de Bob",
        		"Lorem ipsum dolor sit amet",
        		Narracion.Genero.FANTASIA)
        	concurso.registrarParticipacion(narracion)
        then: "se registrara exitosamente la narracion"
        	concurso.narraciones.contains(narracion)
    }

    void "test participacion fallida en concurso"() {
        given: "un concurso"
        	def concurso = new Concurso(creadorDelConcurso,
        		"Concurso de Alice",
        		"Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        		200,
        		50,
        		Narracion.Genero.FANTASIA)
        when: "se quiera participar sin los requerimientos minimos"
        	def participante = new Usuario("Bob")
        	participante.reputacion = 25
        	def narracion = new Narracion(participante,
        		"Narracion de Bob",
        		"Lorem ipsum dolor sit amet",
        		Narracion.Genero.TERROR)
        	concurso.registrarParticipacion(narracion)
        then: "no se podra participar con esa narracion"
        	false == concurso.narraciones.contains(narracion)
        	def exception = thrown(IllegalStateException)
        	exception != null 
    }

    void "test el concurso dura una semana"() {
        given: "un concurso"
            def concurso = new Concurso(creadorDelConcurso,
                "Concurso de Alice",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                200,
                50,
                Narracion.Genero.FANTASIA)
        expect: "el concurso no termino si paso menos de una semana"
            !concurso.terminado()

    }

    void "test la narracion con mas me gusta gana el concurso"() {
        given: "un concurso y dos narraciones con diferente cantidad de me gusta"
            def concurso = new Concurso(creadorDelConcurso,
                "Concurso de Alice",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                200,
                0,
                Narracion.Genero.FANTASIA)
            def narracion_1 = new Narracion(new Usuario("Bob"),
                "Narracion de Bob",
                "Lorem ipsum dolor sit amet",
                Narracion.Genero.FANTASIA)
            def narracion_2 = new Narracion(new Usuario("Charlie"),
                "Narracion de Charlie",
                "Lorem ipsum dolor sit amet",
                Narracion.Genero.FANTASIA)
            concurso.registrarParticipacion(narracion_1)
            concurso.registrarParticipacion(narracion_2)
            narracion_1.cantMeGusta = 3
            narracion_2.cantMeGusta = 5
        when: "se determine el ganador"
            concurso.finalizar()
        then: "ganara la narracion con mas me gusta"
            concurso.narracionGanadora == narracion_2
    }
}
