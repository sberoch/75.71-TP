package ewriters

class BootStrap {

    def sesion
    def espacioPrincipal

    def init = { servletContext ->

        sesion.usuarioActivo = new Usuario("Bob")
        espacioPrincipal = new EspacioPrincipal()

        Narracion narracion = new Narracion(
            "Narracion de Bob",
            "Texto de la narracion",
            Narracion.Genero.NO_FICCION,
            30
        )

        sesion.usuarioActivo.escribirNarracion(narracion, espacioPrincipal)

        espacioPrincipal.narraciones.each { println(it) }

        sesion.usuarioActivo.save(failOnError: true)
        narracion.save(failOnError: true)

    }
    
    def destroy = {
    }
}
