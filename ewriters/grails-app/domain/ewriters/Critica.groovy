package ewriters

class Critica {

	String texto
	String seccionCriticada
	String respuesta

	static belongsTo = [escritor: Usuario, narracion: Narracion]
    static constraints = {
    	texto blank: false
		seccionCriticada blank: false
		respuesta nullable: true
    }

	Critica(String texto, String seccionCriticada) {
		this.texto = texto
		this.seccionCriticada = seccionCriticada
	}

	void agregarRespuestaDeAutor(String texto, Usuario autor) {
		if (autor == narracion.escritor) {
			this.respuesta = texto
		} else {
			throw new IllegalStateException("Solo el autor de la narracion puede responder a sus criticas")
		}
	}
}
