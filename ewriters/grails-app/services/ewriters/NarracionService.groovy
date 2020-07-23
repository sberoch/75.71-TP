package ewriters

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

interface INarracionService {

    Narracion get(Serializable id)

    Long count()

    void delete(Serializable id)

    Narracion save(Narracion narracion)

}

@Service(Narracion)
abstract class NarracionService implements INarracionService {

	@Transactional
	List<Narracion> list(Map args) {
		return Narracion.findAllByPublica(true, [sort: "popularidad", order: "desc"])
	}

	@Transactional
	def darMeGusta(Narracion narracion) {
		narracion.agregarMeGusta()
		narracion.save(flush:true, failOnError: true)
	}

	@Transactional
	def agregarComentario(Comentario comentario, Long narracionId) {
		def narracion = Narracion.get(narracionId)
		narracion.addToComentarios(comentario)
		narracion.save(flush:true, failOnError: true)
		comentario.save(flush:true, failOnError: true)
	}

}