package ewriters

class Critica {

	String texto
	String seccionCriticada

	static belongsTo = [escritor: Usuario, narracion: Narracion]
    static constraints = {
    	texto blank: false
		seccionCriticada blank: false
    }

	Critica(String texto, String seccionCriticada) {
		this.texto = texto
		this.seccionCriticada = seccionCriticada
	}
}
