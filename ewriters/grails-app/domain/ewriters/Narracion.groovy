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

	Genero genero
	Long popularidad
	Long cantMeGusta
	String titulo
	String texto //Puedo necesitar que sea mas largo (ver mappings)
	Long minimaReputacionParaCritica
	Boolean publica
	Concurso concurso

	static belongsTo = [escritor: Usuario]
	static hasMany = [
		comentarios: Comentario,
		criticas: Critica,
		listaMeGusta: MeGusta
	]
    static constraints = {
    	genero nullable: false
    	titulo blank: false
    	texto blank: false
    	minimaReputacionParaCritica blank: false
		concurso nullable: true
    }

    Narracion(String titulo, 
    	String texto, 
    	Genero genero,
    	Long minimaReputacionParaCritica = 0,
    	Boolean publica = true) {

    	this.titulo = titulo
		this.texto = texto
		this.genero = genero
		this.minimaReputacionParaCritica = minimaReputacionParaCritica
		this.publica = publica
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
			this.addToComentarios(comentario)
			popularidad++
		} else {
			throw new IllegalStateException("No se tiene reputacion suficiente para comentar")
		} 
	}

	void agregarMeGusta(MeGusta meGusta) {
		if (!listaMeGusta || !listaMeGusta.contains(meGusta)) {
			this.addToListaMeGusta(meGusta)
			cantMeGusta++
			popularidad++
		} else {
			throw new IllegalStateException("Ya se agrego este me gusta.")
		}
	}

	void removerMeGusta(MeGusta meGusta) {
		if (listaMeGusta.contains(meGusta)) {
			this.removeFromListaMeGusta(meGusta)
			cantMeGusta--
			popularidad--
		} else {
			throw new IllegalStateException("Ya se quito este me gusta.")
		}
	}
}
