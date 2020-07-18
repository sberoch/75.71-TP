package ewriters

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ConcursoController {

    ConcursoService concursoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond concursoService.list(params), model:[concursoCount: concursoService.count()]
    }

    def show(Long id) {
        respond concursoService.get(id)
    }

    def create() {
        respond new Concurso(params)
    }

    def save(Concurso concurso) {
        if (concurso == null) {
            notFound()
            return
        }

        try {
            concursoService.save(concurso)
        } catch (ValidationException e) {
            respond concurso.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'concurso.label', default: 'Concurso'), concurso.id])
                redirect concurso
            }
            '*' { respond concurso, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond concursoService.get(id)
    }

    def update(Concurso concurso) {
        if (concurso == null) {
            notFound()
            return
        }

        try {
            concursoService.save(concurso)
        } catch (ValidationException e) {
            respond concurso.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'concurso.label', default: 'Concurso'), concurso.id])
                redirect concurso
            }
            '*'{ respond concurso, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        concursoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'concurso.label', default: 'Concurso'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'concurso.label', default: 'Concurso'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
