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
	Set<Comentario> comentarios = []
	Set<Critica> criticas = []

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
