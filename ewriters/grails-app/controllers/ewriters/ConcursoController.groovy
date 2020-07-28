package ewriters

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import java.time.*

class ConcursoController {

    ConcursoService concursoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond concursoService.list(params), model:[concursoCount: concursoService.count()]
    }

    def show(Long id) {
        Concurso concurso = concursoService.get(id)
        render(view: "show", model: [
            concurso: concurso, 
            terminado: concurso.terminado()
        ])
    }

    def participar(Long id) {
        //TODO: participar
    }

    def mostrarGanador(Long id) {
        Narracion ganadora = concursoService.obtenerNarracionGanadora(id)
        redirect controller: "narracion", action: "show", id: ganadora.id
    }

    def create() {
        respond new Concurso(params)
    }

    def save(Concurso concurso) {
        concurso.fechaCreacion = LocalDateTime.now()
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

        concurso.comenzar()

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
