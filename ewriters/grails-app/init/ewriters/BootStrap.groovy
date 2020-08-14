package ewriters

class BootStrap {

    def sesion
    def espacioPrincipal

    def init = { servletContext ->

        Narracion narracion = new Narracion(
            "Narracion de Bob",
            "Texto de la narracion",
            Narracion.Genero.NO_FICCION,
            0
        )

        Taller espacio = new Taller()
        espacio.agregarUsuario(sesion.usuarioActivo)
        sesion.usuarioActivo.escribirNarracion(narracion, espacio)

        sesion.usuarioActivo.save(failOnError: true)
        narracion.save(failOnError: true)

    }
    
    def destroy = {
    }
}
