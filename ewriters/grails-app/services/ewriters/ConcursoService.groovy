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
        def narracionesDelConcurso = Narracion.findAllByEspacio(Concurso.get(id))
        return narracionesDelConcurso.max()
    }

    @Transactional
    void crear(Concurso concurso, Usuario creador) {
        creador.addToConcursos(concurso)
        concurso.comenzar()
        concurso.save(failOnError: true)
    }

    @Transactional
    void enviarNarracion(Narracion narracion, Concurso concurso, Usuario escritor) {
        escritor.escribirNarracion(narracion, concurso)
        narracion.save(failOnError: true)
    }
}