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
		//TODO: paginar
		return Narracion.findAllByPublica(true, [sort: "popularidad", order: "desc"])
	}

	@Transactional
	List<Narracion> search(Map args) {
		//Devuelve todas las narraciones con args.query en alguna parte del titulo 
		String query = "%" + args.query + "%"
		return Narracion.findAllByTituloIlike(query)
	}

	@Transactional
	def meGusta(Long id) {
		//TODO: obtener el usuario logueado
		def usuario = new Usuario("Hardcodeado")
		def narracion = Narracion.get(id)
		MeGusta meGusta = new MeGusta()

		//TODO: verificar que pueda
		narracion.agregarMeGusta()

		usuario.addToMeGusta(meGusta)
		narracion.addToMeGusta(meGusta)

		usuario.save(flush: true, failOnError: true)
		narracion.save(flush: true, failOnError: true)
		meGusta.save(flush: true, failOnError: true)
	}

	@Transactional
	def agregarComentario(Comentario comentario, Long narracionId) {
		//TODO: obtener el usuario logueado
		def usuario = new Usuario("Hardcodeado")
		def narracion = Narracion.get(narracionId)

		//TODO: comportamientos del dominio
		usuario.addToComentarios(comentario)
		narracion.addToComentarios(comentario)

		usuario.save(flush: true, failOnError: true)
		narracion.save(flush: true, failOnError: true)
		comentario.save(flush: true, failOnError: true)
	}

	@Transactional
	List<Comentario> listarComentarios(Long narracionId) {
		return Comentario.findAllByNarracion(Narracion.get(narracionId))
	}

	@Transactional
	Long contarMeGusta(Long id) {
		return MeGusta.findAllByNarracion(Narracion.get(id)).size()
	}

}