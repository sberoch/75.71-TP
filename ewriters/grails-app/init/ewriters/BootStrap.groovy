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

        Usuario creador = new Usuario("Alice")
        Concurso espacio = new Concurso("Concurso de Alice",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            300,
            0,
            Narracion.Genero.NO_FICCION)
        creador.addToConcursos(espacio)

        try {
            sesion.usuarioActivo.escribirNarracion(narracion, espacio)
            espacio.narraciones.each { println(it) }
            sesion.usuarioActivo.save(failOnError: true)
            narracion.save(failOnError: true)
        } catch(e) {
            println(e)
        }
    }
    
    def destroy = {
    }
}
