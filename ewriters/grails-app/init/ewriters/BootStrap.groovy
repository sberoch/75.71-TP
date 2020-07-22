package ewriters

class BootStrap {

    def init = { servletContext ->

    	Usuario usuario = new Usuario("Bob")

        Narracion narracion = new Narracion(
            "Narracion de Bob",
            "Texto de la narracion",
            Narracion.Genero.NO_FICCION
        )

        Narracion narracion2 = new Narracion(
            "Narracion 2 de Bob",
            "Texto de la narracion 2",
            Narracion.Genero.NO_FICCION,
            0,
            false
        )

        Comentario comentario = new Comentario("Comentario de bob!")

        Concurso concurso = new Concurso(usuario,
                "Concurso de Alice",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                200,
                50,
                Narracion.Genero.FANTASIA)

        usuario.addToNarraciones(narracion)
        usuario.addToNarraciones(narracion2)
        usuario.addToComentarios(comentario)
        narracion.addToComentarios(comentario)
        usuario.addToConcursos(concurso)
        concurso.addToNarraciones(narracion)

        usuario.save(failOnError: true)
        narracion.save(failOnError: true)
        narracion2.save(failOnError: true)
        comentario.save(failOnError: true)
        concurso.save(failOnError: true)

        //TODO: no se ven las narraciones dentro de concurso
    }
    
    def destroy = {
    }
}
