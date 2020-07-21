package ewriters

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class NarracionController {

    NarracionService narracionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond narracionService.list(params), model:[narracionCount: narracionService.count()]
    }

    def show(Long id) {
        Narracion narracion = narracionService.get(id)
        println(narracion.cantMeGusta)
        respond narracion
    }

    def create() {
        respond new Narracion(params)
    }

    def darMeGusta(Long id) {
        Narracion narracion = narracionService.get(id)
        narracionService.darMeGusta(narracion)
        render(view: "show", model: [narracion: narracion])
    }

    def comentar(Comentario comentario, Long id) {
        narracionService.agregarComentario(comentario, id)
        render(view: "index")
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
