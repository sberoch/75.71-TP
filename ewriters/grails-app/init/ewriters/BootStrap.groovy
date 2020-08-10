package ewriters

class BootStrap {

    def sesion

    def init = { servletContext ->

        Usuario creador = new Usuario("Alice")
        sesion.usuarioActivo = new Usuario("Bob")


        Narracion narracion = new Narracion(
            "Narracion de Bob",
            "Texto de la narracion",
            Narracion.Genero.NO_FICCION,
            30
        )

        EspacioDePublicacion concurso = new Concurso("Concurso de Alice",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                200,
                0,
                Narracion.Genero.NO_FICCION)

        creador.addToConcursos(concurso)
        concurso.comenzar()
        sesion.usuarioActivo.escribirNarracion(narracion, concurso)

        sesion.usuarioActivo.save(failOnError: true)
        narracion.save(failOnError: true)
        concurso.save(failOnError: true)

    }
    
    def destroy = {
    }
}
