package ewriters

class Comentario {

	String texto
    Usuario escritor

	static belongsTo = [escritor: Usuario]
    static constraints = {
    }

    Comentario(Usuario escritor, String texto) {
        this.escritor = escritor
        this.texto = texto
    }
}
