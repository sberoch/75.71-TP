package ewriters

class Taller extends EspacioDePublicacion {

    static hasMany = [
        narraciones: Narracion,
        usuarios: Usuario
    ]
    static constraints = {
    }

    void agregarUsuario(Usuario usuario) {
        this.addToUsuarios(usuario)
    }

    void removerUsuario(Usuario usuario) {
        this.removeFromUsuarios(usuario)
    }

    void agregarNarracion(Narracion narracion, Usuario escritor) { 
        if (escritor in usuarios) {
            this.addToNarraciones(narracion)
        } else {
            throw new IllegalStateException("El usuario no pertenece a este taller")
        }
    }
}
