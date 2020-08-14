package ewriters

class Usuario {

	String nombreApellido
	Long reputacion 

	static hasMany = [
        concursos: Concurso,
        narraciones: Narracion,
        narracionesConMeGusta: MeGusta,
    ]

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