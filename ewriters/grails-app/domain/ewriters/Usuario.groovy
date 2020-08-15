package ewriters

class Usuario {

	String nombreApellido
	Long reputacion 

	static hasMany = [
        concursos: Concurso,
        talleres: Taller,
        narraciones: Narracion,
        misMeGusta: MeGusta,
        comentarios: Comentario,
        criticas: Critica
    ]
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
        //TODO: mejorar el hashcode. @EqualsAndHashCode no sirve
        return nombreApellido.size()
    }

    void escribirNarracion(Narracion narracion, EspacioDePublicacion espacioDePublicacion) {
    	espacioDePublicacion.agregarNarracion(narracion, this)
        this.addToNarraciones(narracion)
    }

    Concurso crearConcurso(String titulo,
        String descripcion, 
        Long recompensa, 
        Long minimaReputacionParaParticipar, 
        Narracion.Genero genero) {
        return new Concurso(this, titulo, descripcion, recompensa, minimaReputacionParaParticipar, genero)
    }

    void ganarConcurso(Long recompensa) {
        this.reputacion += recompensa
    }
}