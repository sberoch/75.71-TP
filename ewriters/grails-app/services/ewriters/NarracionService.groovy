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
		EspacioPrincipal espacio = EspacioPrincipal.list()?.first()
		return Narracion.findAllByEspacio(espacio, [sort: "popularidad", order: "desc"])
	}

	@Transactional
	List<Narracion> search(Map args) {
		//Devuelve todas las narraciones con args.query en alguna parte del titulo 
		String query = "%" + args.query + "%"
		return Narracion.findAllByTituloIlike(query)
	}

	@Transactional
	void crear(Narracion narracion, Usuario escritor) {
		EspacioPrincipal espacio = EspacioPrincipal.list()?.first()
		escritor.escribirNarracion(narracion, espacio)
		narracion.save(failOnError: true)
	}

	@Transactional
	def meGusta(Long narracionId, Usuario usuario) {
		def narracion = Narracion.get(narracionId)
		MeGusta meGusta = MeGusta.find {
			(usuario == usuario) &&
			(narracion == narracion) 
		}

		if (meGusta) {
			narracion.removerMeGusta(meGusta)
			meGusta.delete(flush: true)
			narracion.save(flush: true, failOnError: true)
		} else {
			meGusta = new MeGusta(usuario)
			narracion.agregarMeGusta(meGusta)
			narracion.save(flush: true, failOnError: true)
			meGusta.save(flush: true, failOnError: true)
		}
	}

	@Transactional
	def agregarComentario(Comentario comentario, Long narracionId, Usuario usuario) {
		def narracion = Narracion.get(narracionId)
		usuario.addToComentarios(comentario)
		narracion.agregarComentario(comentario)
		narracion.save(flush: true, failOnError: true)
	}

	@Transactional
	def agregarCritica(Critica critica, Long narracionId, Usuario usuario) {
		def narracion = Narracion.get(narracionId)
		usuario.addToCriticas(critica)
		narracion.agregarCritica(critica)
		narracion.save(flush: true, failOnError: true)
	}

	@Transactional
	List<Comentario> listarComentarios(Long narracionId) {
		return Comentario.findAllByNarracion(Narracion.get(narracionId))
	}

	@Transactional
	List<Critica> listarCriticas(Long narracionId) {
		return Critica.findAllByNarracion(Narracion.get(narracionId))
	}

	@Transactional
	Long contarMeGusta(Long id) {
		return MeGusta.findAllByNarracion(Narracion.get(id)).size()
	}

}