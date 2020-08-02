package ewriters

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class NarracionController {

    NarracionService narracionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        session["userId"] = 2
        params.max = Math.min(max ?: 10, 100)
        respond narracionService.list(params), model:[narracionCount: narracionService.count()]
    }

    def search() {
        List<Narracion> results = narracionService.search(params)
        render(view: "index", model: [
            narracionList: results,
            narracionCount: results.size()
        ])
    }

    def show(Long id) {
        render(view: "show", model: [
            narracion: narracionService.get(id), 
            comentarios: narracionService.listarComentarios(id),
            meGustaCount: narracionService.contarMeGusta(id)
        ])
    }

    def create() {
        respond new Narracion(params)
    }

    def meGusta(Long id) {
        narracionService.meGusta(id)
        redirect action: "show", id: id
    }

    def comentar(String texto, Long id) {
        def comentario = new Comentario(texto)
        println(session["userId"])
        narracionService.agregarComentario(comentario, id, session["userId"])
        redirect action: "show", id: id
    }

    def save(Narracion narracion) {
        narracion.popularidad = 0
        narracion.cantMeGusta = 0
        if (narracion == null) {
            notFound()
            return
        }

        try {
            narracionService.save(narracion)
        } catch (ValidationException e) {
            respond narracion.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'narracion.label', default: 'Narracion'), narracion.id])
                redirect narracion
            }
            '*' { respond narracion, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond narracionService.get(id)
    }

    def update(Narracion narracion) {
        if (narracion == null) {
            notFound()
            return
        }

        try {
            narracionService.save(narracion)
        } catch (ValidationException e) {
            respond narracion.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'narracion.label', default: 'Narracion'), narracion.id])
                redirect narracion
            }
            '*'{ respond narracion, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        narracionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'narracion.label', default: 'Narracion'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'narracion.label', default: 'Narracion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
