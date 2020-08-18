package ewriters

class BootStrap {

    def sesion
    def espacioPrincipal

    def init = { servletContext ->

        espacioPrincipal = new EspacioPrincipal()
        espacioPrincipal.save(failOnError: true)

        Usuario usuario = new Usuario("Bob")
        

        Narracion narracion = new Narracion(
            "Un titulo de prueba",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
            "TERROR"
        )
        Narracion narracion2 = new Narracion(
            "Otra prueba",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
            "TERROR"
        )
        Narracion narracion3 = new Narracion(
            "Ultima prueba",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
            "TERROR"
        )

        usuario.escribirNarracion(narracion, espacioPrincipal)
        usuario.escribirNarracion(narracion2, espacioPrincipal)
        usuario.escribirNarracion(narracion3, espacioPrincipal)

        Usuario usuarioGuardado = usuario.save(failOnError: true)
        sesion.usuarioActivoId = usuarioGuardado.id

        narracion.save(failOnError: true)
        narracion2.save(failOnError: true)
        narracion3.save(failOnError: true)   

    }
    
    def destroy = {
    }
}
