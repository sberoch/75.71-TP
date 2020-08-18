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
        return nombreApellido
    }

    boolean equals(other) {
        return this.nombreApellido == other.nombreApellido
    }

    int hashCode() {
        return Objects.hash(nombreApellido, reputacion)
    }

    void escribirNarracion(Narracion narracion, EspacioDePublicacion espacioDePublicacion) {
    	espacioDePublicacion.agregarNarracion(narracion, this)
        this.addToNarraciones(narracion)
    }

    Concurso crearConcurso(String titulo,
        String descripcion, 
        Long recompensa, 
        Long minimaReputacionParaParticipar, 
        Genero genero) {
        return new Concurso(this, titulo, descripcion, recompensa, minimaReputacionParaParticipar, genero)
    }

    void ganarConcurso(Long recompensa) {
        this.reputacion += recompensa
    }

    void responderComentario(Comentario comentario, String respuesta) {
        if (comentario.narracion.escritor.equals(this)) {
            comentario.respuesta = respuesta
            reputacion++
            comentario.narracion.popularidad++
        }
    }
}