package ewriters

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

interface IConcursoService {

    Concurso get(Serializable id)

    List<Concurso> list(Map args)

    Long count()

    void delete(Serializable id)

    Concurso save(Concurso concurso)

}

@Service(Concurso)
abstract class ConcursoService implements IConcursoService {
    
    @Transactional
    Narracion obtenerNarracionGanadora(Long id) {
        Concurso concurso = Concurso.get(id)
        if (!concurso) {
            throw new IllegalStateException("Ocurrio un error con el concurso.")
        }
        def narracionesDelConcurso = Narracion.findAllByEspacio(concurso)
        return narracionesDelConcurso.max()
    }

    @Transactional
    void crear(Concurso concurso, Usuario creador) {
        if (!creador) {
            throw new IllegalStateException("Ocurrio un error con el usuario conectado.")
        }
        creador.addToConcursos(concurso)
        concurso.comenzar()
        concurso.save(failOnError: true)
    }

    @Transactional
    void enviarNarracion(Narracion narracion, Concurso concurso, Usuario escritor) {
        if (!escritor) {
            throw new IllegalStateException("Ocurrio un error con el usuario conectado.")
        }
        if (!concurso) {
            throw new IllegalStateException("Ocurrio un error con el concurso.")
        }
        escritor.escribirNarracion(narracion, concurso)
        narracion.save(failOnError: true)
    }
}