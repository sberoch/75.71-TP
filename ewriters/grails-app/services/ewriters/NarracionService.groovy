package ewriters

import grails.gorm.services.Service

@Service(Narracion)
interface NarracionService {

    Narracion get(Serializable id)

    List<Narracion> list(Map args)

    Long count()

    void delete(Serializable id)

    Narracion save(Narracion narracion)

}