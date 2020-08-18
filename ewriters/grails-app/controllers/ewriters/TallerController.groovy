package ewriters

class TallerController {

    TallerService tallerService
    def sesion

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond tallerService.list(params), model:[tallerCount: tallerService.count()]
    }

    def show(Long id) {
        Taller taller = tallerService.get(id)
        render(view: "show", model:[
            taller: taller
        ])
    }

    def crear(String titulo) {
        try {
            Taller taller = new Taller(titulo)
            tallerService.crearTaller(taller, sesion.getUsuarioActivo())
        } catch (IllegalStateException e) {
            flash.message = e.message
        }
        redirect action: "index"
    }

    def agregarUsuario(String nombre, Long id) {
        try {
            tallerService.agregarUsuario(id, nombre)
            redirect action: "show", id: id
        } catch (IllegalStateException e) {
            flash.message = e.message
            redirect action: "agregar", id: id
        }
    }

    def removerUsuario(String nombre, Long id) {
        try {
            tallerService.removerUsuario(id, nombre)
            redirect action: "show", id: id
        } catch (IllegalStateException e) {
            flash.message = e.message
            redirect action: "remover", id: id
        }
    }

    def agregarNarracion(String titulo, String texto, String genero, Long id) {
        try {
            Narracion narracion = new Narracion(titulo, texto, genero)
            tallerService.agregarNarracion(id, narracion, sesion.getUsuarioActivo())
            redirect action: "show", id: id
        } catch (IllegalStateException e) {
            flash.message = e.message
            redirect action: "escribir", id: id
        } 
    }

    def create() {
        respond new Taller(params)
    }

    def agregar(Long id) {
        respond tallerService.get(id)
    }

    def remover(Long id) {
        respond tallerService.get(id)
    }

    def escribir(Long id) {
        respond tallerService.get(id)
    }
}
