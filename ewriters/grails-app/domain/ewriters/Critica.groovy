package ewriters

class Critica {

	String texto
	//Seccion: como modelo?
	//	Ideas: Puntero a parte del texto donde empieza + largo

	static belongsTo = [escritor: Usuario]
    static constraints = {
    	texto blank: false
    }
}
