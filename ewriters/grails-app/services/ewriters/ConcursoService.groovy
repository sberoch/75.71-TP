package ewriters

import grails.gorm.services.Service

@Service(Concurso)
interface ConcursoService {

    Concurso get(Serializable id)

    List<Concurso> list(Map args)

    Long count()

    void delete(Serializable id)

    Concurso save(Concurso concurso)

}