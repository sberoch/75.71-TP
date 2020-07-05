package ewriters

class Comentario {

	String texto

	static belongsTo = [escritor: Usuario]
    static constraints = {
    }
}
