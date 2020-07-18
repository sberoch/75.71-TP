package ewriters

class Narracion implements Comparable {

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
	Long cantMeGusta
	String titulo
	String texto //Puedo necesitar que sea mas largo (ver mappings)
	Long minimaReputacionParaCritica
	Set<Comentario> comentarios = []
	Set<Critica> criticas = []

	static belongsTo = [escritor: Usuario]
	static hasMany = [
		comentarios: Comentario,
		criticas: Critica
	]
    static constraints = {
    	escritor nullable: false
    	genero nullable: false
    	titulo blank: false
    	texto blank: false
    	minimaReputacionParaCritica blank: false
    }

    Narracion(Usuario escritor,
    	String titulo, 
    	String texto, 
    	Genero genero,
    	Long minimaReputacionParaCritica = 0) {

    	this.escritor = escritor
    	this.titulo = titulo
		this.texto = texto
		this.genero = genero
		this.minimaReputacionParaCritica = minimaReputacionParaCritica
		this.popularidad = 0
		this.cantMeGusta = 0
    }

    String toString() {
    	return titulo
    }

    int compareTo(other) {
    	return this.cantMeGusta <=> other.cantMeGusta
    }

	void agregarComentario(Comentario comentario) {
		if (comentario.escritor.reputacion >= minimaReputacionParaCritica) {
			comentarios << comentario
			popularidad++
		} else {
			throw new IllegalStateException("No se tiene reputacion suficiente para comentar")
		} 
	}

	void agregarMeGusta() {
		cantMeGusta++
		popularidad++
	}

	void removerMeGusta() {
		cantMeGusta--
		popularidad--
	}
}
