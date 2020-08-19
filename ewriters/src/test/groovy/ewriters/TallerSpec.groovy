package ewriters

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class TallerSpec extends Specification implements DomainUnitTest<Taller> {

    //def creadorDelTaller
    def usuario

    def setup() {
        //creadorDelTaller = new Usuario("Alice")
        usuario = new Usuario("Bob")
    }

    void "test agregar un usuario al taller"() {
        given:"un taller"
            Taller taller = new Taller("Un taller")
        when:"se agrega un usuario"
            taller.agregarUsuario(usuario)
        then:"el usuario se agrego a los del taller"
            taller.usuarios.size() == 1
    }

    void "test agregar un usuario que ya estaba en el taller"() {
        given: "un taller y un usuario agregado al taller"
            Taller taller = new Taller("Un taller")
            taller.agregarUsuario(usuario)
        when: "se agrega a ese mismo usuario"
            taller.agregarUsuario(usuario)
        then: "no se lo agrega y se lanza una excepcion"
            taller.usuarios.size() == 1
            def exception = thrown(IllegalStateException) 
            exception != null

    }

    void "test remover usuario del taller"() {
        given:"un taller y un usuario agregado al taller"
            Taller taller = new Taller("Un taller")
            taller.agregarUsuario(usuario)
        when:"se quita al usuario del taller" 
            taller.removerUsuario(usuario)
        then:"el usuario ya no esta en el taller"
            taller.usuarios.size() == 0
    }


    void "test remover un usuario que no esta en el taller"() {
        given:"un taller"
            Taller taller = new Taller("Un taller")
        when:"se quite a un usuario que no esta agregado del taller"
            taller.removerUsuario(usuario)
        then:"se lanzara excepcion"
            def exception = thrown(IllegalStateException) 
            exception != null
    }

    void "test un usuario perteneciente al taller agrega una narracion"() {
        given:"un taller y un usuario agregado al taller"
            Taller taller = new Taller()
            taller.agregarUsuario(usuario)
        when:"el usuario agrega una narracion"
            Narracion narracion = new Narracion("Narracion de Bob",
                "Texto de la narracion",
                "NO_FICCION"
            )
            taller.agregarNarracion(narracion, usuario)
        then:"la narracion se agrega al taller"
            taller.narraciones.size() == 1
    }

    void "test un usuario no perteneciente al taller agrega una narracion"() {
        given:"un taller"
            Taller taller = new Taller()
        when:"un usuario no perteneciente al taller agrega una narracion"
            Narracion narracion = new Narracion("Narracion de Bob",
                "Texto de la narracion",
                "NO_FICCION"
            )
            taller.agregarNarracion(narracion, usuario)
        then:"la narracion no se agrega al taller y se lanzara una excepcion"
            def exception = thrown(IllegalStateException) 
            exception != null
    }
}
