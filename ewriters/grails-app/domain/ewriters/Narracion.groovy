package ewriters
import java.time.*

class Narracion implements Comparable {

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
    	String genero,
    	Long minimaReputacionParaCritica = 0) {

    	this.titulo = titulo
		this.texto = texto
		this.genero = genero as Genero
		this.minimaReputacionParaCritica = minimaReputacionParaCritica
		this.popularidad = 0
		this.cantMeGusta = 0
		this.fechaCreacion = LocalDateTime.now()
    }

    String toString() {
    	titulo
    }

    int compareTo(other) {
    	this.cantMeGusta <=> other.cantMeGusta
    }

	void agregarComentario(Comentario comentario) {
		if (comentario.escritor.reputacion >= minimaReputacionParaCritica) {
			this.addToComentarios(comentario)
			popularidad++
			escritor.reputacion++
		} else {
			throw new IllegalStateException("No se tiene reputacion suficiente para comentar")
		} 
	}

	void agregarMeGusta(MeGusta meGusta) {
		if (!listaMeGusta || !listaMeGusta.contains(meGusta)) {
			this.addToListaMeGusta(meGusta)
			cantMeGusta++
			popularidad++
			escritor.reputacion++
		} else {
			throw new IllegalStateException("Ya se agrego este me gusta.")
		}
	}

	void removerMeGusta(MeGusta meGusta) {
		if (listaMeGusta?.contains(meGusta)) {
			this.removeFromListaMeGusta(meGusta)
			cantMeGusta--
			popularidad--
			escritor.reputacion--
		} else {
			throw new IllegalStateException("Ya se quito este me gusta.")
		}
	}
}
