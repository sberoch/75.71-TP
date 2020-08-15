package ewriters

// Place your Spring DSL code here
beans = {
    sesion(Sesion) {
        usuarioActivo = new Usuario("Bob")
    }
    espacioPrincipal(EspacioPrincipal)
}
