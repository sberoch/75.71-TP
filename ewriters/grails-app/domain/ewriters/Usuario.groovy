package ewriters

class Usuario {

	String nombreApellido
	Long reputacion 

	static hasMany = [
        concursos: Concurso,
        narraciones: Narracion,
        meGusta: MeGusta,
        comentarios: Comentario
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
    	espacioDePublicacion.agregarNarracion(narracion)
        this.addToNarraciones(narracion)
    }

    Concurso crearConcurso(String titulo,   
        String descripcion, 
        Long recompensa, 
        Long minimaReputacionParaParticipar, 
        Narracion.Genero genero) {
        return new Concurso(this, titulo, descripcion, recompensa, minimaReputacionParaParticipar, genero)
    }

    void participarEnConcurso(Narracion narracion, Concurso concurso) {
        if (reputacion < concurso.minimaReputacionParaParticipar) {
    		throw new IllegalStateException("No se tiene reputacion suficiente para participar")
    	} else {
            concurso.registrarParticipacion(narracion)
            this.addToNarraciones(narracion)
        }
        
    }

    void meGusta(Narracion narracion) {
        if (!narracionesConMeGusta.contains(narracion)) {
            narracionesConMeGusta << narracion
            narracion.agregarMeGusta()
        } else {
            throw new IllegalStateException("Ya se indico me gusta en esta narracion")
        }
    }

    void removerMeGusta(Narracion narracion) {
        if (narracionesConMeGusta.contains(narracion)) {
            narracionesConMeGusta.removeElement(narracion)
            narracion.removerMeGusta()
        } else {
            throw new IllegalStateException("No se indico me gusta previamente en esta narracion")
        }
    }

    void ganarConcurso(Long recompensa) {
        this.reputacion += recompensa
    }
}