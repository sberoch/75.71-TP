package ewriters

class Comentario {

	String texto

	static belongsTo = [escritor: Usuario, narracion: Narracion]
    static constraints = {
    	texto blank: false
    }

    Comentario(String texto) {
        this.texto = texto
    }

    String toString() {
        return texto
    }
}
