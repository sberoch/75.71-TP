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
}
