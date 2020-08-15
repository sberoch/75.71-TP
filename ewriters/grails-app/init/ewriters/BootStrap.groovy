package ewriters

class BootStrap {

    def sesion
    def espacioPrincipal

    def init = { servletContext ->

        espacioPrincipal = new EspacioPrincipal()
        
        Usuario creador = new Usuario("Alice")
        creador.save(failOnError: true)
        Taller espacio = new Taller("Taller re copado")
        creador.addToTalleres(espacio)

        sesion.usuarioActivo.save(failOnError: true)
        espacio.save(failOnError: true)

    }
    
    def destroy = {
    }
}
