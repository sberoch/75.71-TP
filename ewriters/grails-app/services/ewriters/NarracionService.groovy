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
		switch(args.ordenamiento) {
			case "Popularidad":
				return Narracion.findAllByEspacio(espacio, [sort: "popularidad", order: "desc"])
			
			case "Fecha de publicacion":
				return Narracion.findAllByEspacio(espacio, [sort: "fechaCreacion", order: "desc"])
			
			case "Nombre":
				return Narracion.findAllByEspacio(espacio, [sort: "titulo"])

			default:
				return Narracion.findAllByEspacio(espacio, [sort: "popularidad", order: "desc"])
		}
	}

	@Transactional
	List<Narracion> search(Map args) {
		//Devuelve todas las narraciones con args.query en alguna parte del titulo 
		String query = "%" + args.query + "%"
		return Narracion.findAllByTituloIlike(query)
	}

	@Transactional
	void crear(Narracion narracion, Usuario escritor) {
		if (!escritor) {
			throw new IllegalStateException("El usuario no existe")
		}
		EspacioPrincipal espacio = EspacioPrincipal.list()?.first()
		escritor.escribirNarracion(narracion, espacio)
		narracion.save(failOnError: true)
	}

	@Transactional
	def meGusta(Long narracionId, Usuario usuario) {
		if (!usuario) {
			throw new IllegalStateException("El usuario no existe")
		}
		def narracion = Narracion.get(narracionId)
		if (!narracion) {
			throw new IllegalStateException("No se encontro la narracion solicitada.")
		}
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
		if (!usuario) {
			throw new IllegalStateException("El usuario no existe")
		}
		def narracion = Narracion.get(narracionId)
		if (!narracion) {
			throw new IllegalStateException("No se encontro la narracion solicitada.")
		}
		usuario.addToComentarios(comentario)
		narracion.agregarComentario(comentario)
		narracion.save(flush: true, failOnError: true)
	}

	@Transactional
	List<Comentario> listarComentarios(Long narracionId) {
		def narracion = Narracion.get(narracionId)
		if (!narracion) {
			throw new IllegalStateException("No se encontro la narracion solicitada.")
		}
		return Comentario.findAllByNarracion(narracion)
	}

	@Transactional
	Long contarMeGusta(Long narracionId) {
		def narracion = Narracion.get(narracionId)
		if (!narracion) {
			throw new IllegalStateException("No se encontro la narracion solicitada.")
		}
		return MeGusta.findAllByNarracion(narracion).size()
	}

	@Transactional
	void responder(String respuesta, Long comentarioId, Usuario usuario) {
		if (!usuario) {
			throw new IllegalStateException("El usuario no existe")
		}
		def comentario = Comentario.get(comentarioId)
		usuario.responderComentario(comentario, respuesta)
		comentario.save(flush: true, failOnError: true)
	}

}