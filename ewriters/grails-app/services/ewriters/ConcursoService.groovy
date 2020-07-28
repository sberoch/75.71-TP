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
        //TODO: si hago "narracion belongsTo Concurso" todas las narraciones tendrian que pertenecer a uno
        //  Hacer una clase NarracionDeConcurso? 

        def narracionesDelConcurso = Narracion.findAllByConcurso(Concurso.get(id))
        return narracionesDelConcurso.max()
    }
}