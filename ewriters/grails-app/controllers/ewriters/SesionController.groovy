package ewriters

//Para cambiar rapidamente entre usuarios
class SesionController {

    def sesion

    def cambiarUsuario(String nombre) {
        sesion.cambiarUsuario(nombre)
        redirect controller:"narracion", action:"index"
    }
}