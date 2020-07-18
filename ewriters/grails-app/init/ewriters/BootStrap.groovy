package ewriters

class BootStrap {

    def init = { servletContext ->

    	Usuario usuario = new Usuario("Bob").save(failOnError: true)

    	Narracion narracion = new Narracion(
    		usuario,
    		"Narracion de Bob",
    		"Texto de la narracion",
    		Narracion.Genero.NO_FICCION
    	).save(failOnError: true)

        Concurso concurso = new Concurso(usuario,
                "Concurso de Bob",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                200,
                50,
                Narracion.Genero.NO_FICCION 
        ).save(failOnError: true)
    }
    
    def destroy = {
    }
}
