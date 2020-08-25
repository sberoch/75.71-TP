package ewriters

class Comentario {

	String texto
    String seccionCriticada
	String respuesta

	static belongsTo = [escritor: Usuario, narracion: Narracion]
    static constraints = {
    	texto blank: false, size:0..65535
        seccionCriticada nullable: true, size:0..65535
        respuesta nullable: true, size:0..65535
    }

    Comentario(String texto, String seccionCriticada) {
        this.texto = texto
        this.seccionCriticada = seccionCriticada
    }

    String toString() {
        texto
    }
}
