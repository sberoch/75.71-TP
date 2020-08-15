package ewriters

class Taller extends EspacioDePublicacion {

    String titulo

    static hasMany = [
        narraciones: Narracion,
        usuarios: Usuario
    ]
    static belongsTo = [creador: Usuario]
    static constraints = {
    }

    Taller(String titulo) {
        this.titulo = titulo
    }

    void agregarUsuario(Usuario usuario) {
        if (!usuarios || !usuarios.contains(usuario)) {
            this.addToUsuarios(usuario)
        } else {
            throw new IllegalStateException("Este usuario ya esta en el taller")
        }
    }

    void removerUsuario(Usuario usuario) {
        if (usuarios.contains(usuario)) {
            this.removeFromUsuarios(usuario)
        } else {
            throw new IllegalStateException("Este usuario no esta en el taller")
        }
        
    }

    void agregarNarracion(Narracion narracion, Usuario escritor) {
        if (usuarios.contains(escritor)) {
            this.addToNarraciones(narracion)
        } else {
            throw new IllegalStateException("Este usuario no esta en el taller")
        }
    }
}
