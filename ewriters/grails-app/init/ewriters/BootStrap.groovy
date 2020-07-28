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

        Narracion narracion2 = new Narracion(
            "Narracion 2 de Bob",
            "Texto de la narracion 2",
            Narracion.Genero.NO_FICCION,
            0
        )

        Narracion narracion3 = new Narracion(
            "Narracion 3 de Bob",
            "Texto de la narracion 3",
            Narracion.Genero.NO_FICCION
        )

        Comentario comentario = new Comentario("Comentario de bob!")

        Concurso concurso = new Concurso("Concurso de Alice",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                200,
                50,
                Narracion.Genero.FANTASIA)

        concurso.comenzar()

        usuario.addToNarraciones(narracion)
        usuario.addToNarraciones(narracion2)
        usuario.addToNarraciones(narracion3)
        usuario.addToComentarios(comentario)
        narracion.addToComentarios(comentario)
        usuario.addToConcursos(concurso)
        concurso.addToNarraciones(narracion)

        usuario.save(failOnError: true)
        narracion.save(failOnError: true)
        narracion2.save(failOnError: true)
        narracion3.save(failOnError: true)
        comentario.save(failOnError: true)
        concurso.save(failOnError: true)

    }
    
    def destroy = {
    }
}
