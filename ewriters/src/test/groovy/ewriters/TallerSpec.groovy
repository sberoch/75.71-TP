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
            Taller taller = new Taller()
        when:"se agrega un usuario"
            taller.agregarUsuario(usuario)
        then:"el usuario se agrego a los del taller"
            taller.usuarios.size() == 1
    }

    void "test remover usuario del taller"() {
        given:"un taller y un usuario agregado al taller"
            Taller taller = new Taller()
            taller.agregarUsuario(usuario)
        when:"se quita al usuario del taller" 
            taller.removerUsuario(usuario)
        then:"el usuario ya no esta en el taller"
            taller.usuarios.size() == 0
    }

    void "test un usuario perteneciente al taller agrega una narracion"() {
        given:"un taller y un usuario agregado al taller"
            Taller taller = new Taller()
            taller.agregarUsuario(usuario)
        when:"el usuario agrega una narracion"
            Narracion narracion = new Narracion("Narracion de Bob",
                "Texto de la narracion",
                Narracion.Genero.NO_FICCION
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
                Narracion.Genero.NO_FICCION
            )
            taller.agregarNarracion(narracion, usuario)
        then:"la narracion no se agrega al taller y se lanzara una excepcion"
            def exception = thrown(IllegalStateException) 
            exception != null
    }
}
