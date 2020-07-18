package ewriters

class Comentario {

	String texto
    Usuario escritor

	static belongsTo = [escritor: Usuario]
    static constraints = {
    	texto blank: false
    	escritor nullable: false
    }

    Comentario(Usuario escritor, String texto) {
        this.escritor = escritor
        this.texto = texto
    }
}
