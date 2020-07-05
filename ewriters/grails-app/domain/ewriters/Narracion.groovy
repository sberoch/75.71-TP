package ewriters

class Narracion {

	enum Genero {
		TERROR,
		CIENCIA_FICCION,
		NO_FICCION,
		POESIA,
		FANTASIA,
		ROMANCE,
		MISTERIO,
		HUMOR
	}

	Usuario escritor
	Genero genero
	Long popularidad
	Long cantMeGusta //Sirve modelarlo asi para poder diferenciar usuarios?
	String titulo
	String texto //Puedo necesitar que sea mas largo (ver mappings)
	Long minimaReputacionParaCritica

	static belongsTo = [escritor: Usuario]
	static hasMany = [
		comentarios: Comentario,
		criticas: Critica
	]
    static constraints = {
    }

    Narracion(Usuario escritor,
    	String titulo, 
    	String texto, 
    	Narracion.Genero genero, 
    	Long minimaReputacionParaCritica = 0) {

    	this.escritor = escritor
    	this.titulo = titulo
		this.texto = texto
		this.genero = genero
		this.minimaReputacionParaCritica = minimaReputacionParaCritica
		this.popularidad = 0
		this.cantMeGusta = 0
    }
}
