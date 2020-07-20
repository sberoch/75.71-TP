package ewriters

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

interface INarracionService {

    Narracion get(Serializable id)

    List<Narracion> list(Map args)

    Long count()

    void delete(Serializable id)

    Narracion save(Narracion narracion)

}

@Service(Narracion)
abstract class NarracionService implements INarracionService {

	@Transactional
	void darMeGusta(Narracion narracion) {
		def usuario = new Usuario("Bobb")
		narracion.agregarMeGusta()
		narracion.validate()
		if (narracion.hasErrors()) {
			narracion.errors.allErrors.each {
				println it
			}
		}
		narracion.save(flush:true, failOnError: true)
	}

}