package ewriters
import java.time.*

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
	String texto
	Long minimaReputacionParaCritica
	LocalDateTime fechaCreacion

	static belongsTo = [escritor: Usuario, espacio: EspacioDePublicacion]
	static hasMany = [
		comentarios: Comentario,
		criticas: Critica,
		listaMeGusta: MeGusta
	]
    static constraints = {
    	genero nullable: false
    	titulo blank: false
    	texto blank: false, size:0..65535
    	minimaReputacionParaCritica blank: false
    }

    Narracion(String titulo, 
    	String texto, 
    	Genero genero,
    	Long minimaReputacionParaCritica = 0) {

    	this.titulo = titulo
		this.texto = texto
		this.genero = genero
		this.minimaReputacionParaCritica = minimaReputacionParaCritica
		this.popularidad = 0
		this.cantMeGusta = 0
		this.fechaCreacion = LocalDateTime.now()
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

	void agregarCritica(Critica critica) {
		if (critica.escritor.reputacion < minimaReputacionParaCritica) {
			throw new IllegalStateException("No se tiene reputacion suficiente para comentar")
		} else if (!this.texto.contains(critica.seccionCriticada) || critica.seccionCriticada.isEmpty()) {
			throw new IllegalStateException("Se esta criticando sobre una seccion inexistente")
		} else {
			this.addToCriticas(critica)
			popularidad++
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
