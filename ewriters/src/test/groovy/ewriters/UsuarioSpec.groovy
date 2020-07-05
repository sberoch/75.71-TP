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
}
