package ewriters

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

interface ITallerService {

    Taller get(Serializable id)

    List<Taller> list(Map args)

    Long count()

    void delete(Serializable id)

    Taller save(Taller taller)

}

@Service(Taller)
abstract class TallerService implements ITallerService {

    @Transactional
    void crearTaller(Taller taller, Usuario creador) {
        creador.addToTalleres(taller)
        taller.save(failOnError: true)
    }

    @Transactional
    void agregarUsuario(Long tallerId, String nombre) {
        Taller taller = Taller.get(tallerId)
        Usuario usuario = Usuario.findByNombreApellido(nombre)
        if (usuario) {
            taller.agregarUsuario(usuario)
            taller.save(flush: true, failOnError: true)
            taller.usuarios.each { println("!!!!" + it) }
        } else {
            throw new IllegalStateException("No se encontro un usuario con ese nombre")
        }
    }

    @Transactional
    void removerUsuario(Long tallerId, String nombre) {
        Taller taller = Taller.get(tallerId)
        Usuario usuario = Usuario.findByNombreApellido(nombre)
        if (usuario) {
            taller.removerUsuario(usuario)
            taller.save(flush: true, failOnError: true)
        } else {
            throw new IllegalStateException("No se encontro un usuario con ese nombre")
        }
    }

    @Transactional
    void agregarNarracion(Long tallerId, Narracion narracion, Usuario escritor) {
        Taller taller = Taller.get(tallerId)
        taller.usuarios.each { println("\n\n" + it + "\n\n") }
        escritor.escribirNarracion(narracion, taller)
        narracion.save(flush: true, failOnError: true)
    }
}
