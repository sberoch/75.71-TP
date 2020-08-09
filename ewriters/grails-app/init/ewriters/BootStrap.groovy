package ewriters

class BootStrap {

    def init = { servletContext ->

    	Usuario usuario = new Usuario("Bob")

        Narracion narracion = new Narracion(
            "Narracion de Bob",
            "Texto de la narracion",
            Narracion.Genero.NO_FICCION,
            30
        )

        EspacioDePublicacion espacioPrincipal = EspacioPrincipal.instance

        usuario.escribirNarracion(narracion, espacioPrincipal)

        usuario.save(failOnError: true)
        narracion.save(failOnError: true)
        espacioPrincipal.save(failOnError: true)

    }
    
    def destroy = {
    }
}
