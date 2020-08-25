package ewriters

class Usuario {

	String nombreApellido
	Long reputacion 

	static hasMany = [
        concursos: Concurso,
        talleres: Taller,
        narraciones: Narracion,
        misMeGusta: MeGusta,
        comentarios: Comentario
    ]

    //Para poder hacer la relacion creador tiene taller | taller tiene usuarios
    static mappedBy = [talleres: "creador"]

    static constraints = {
        nombreApellido blank: false
    }

    Usuario(String nombreApellido) {
    	this.nombreApellido = nombreApellido
		this.reputacion = 0
    }

    String toString() {
        nombreApellido
    }

    boolean equals(other) {
        this.nombreApellido == other.nombreApellido 
    }

    int hashCode() {
        Objects.hash(nombreApellido)
    }

    void escribirNarracion(Narracion narracion, EspacioDePublicacion espacioDePublicacion) {
    	espacioDePublicacion.agregarNarracion(narracion, this)
        this.addToNarraciones(narracion)
    }

    void ganarConcurso(Long recompensa) {
        this.reputacion += recompensa
    }

    void responderComentario(Comentario comentario, String respuesta) {
        if (comentario.narracion.escritor != this) {
            throw new IllegalStateException("Solo puede responder comentarios el autor de la narracion.")

        } else if (comentario.respuesta != null) {
            throw new IllegalStateException("Ya habia una respuesta para este comentario")

        } else {
            comentario.respuesta = respuesta
            reputacion++
            comentario.narracion.popularidad++
        }
    }
}