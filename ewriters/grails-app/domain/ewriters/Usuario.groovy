package ewriters

class Usuario {

	String nombreApellido
	Long reputacion
	Set<Narracion> narraciones = []
    Set<Narracion> narracionesConMeGusta = [] 

	static hasMany = [
        narraciones: Narracion,
        narracionesConMeGusta: Narracion
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

    Narracion escribirNarracion(String titulo, String texto, Narracion.Genero genero, Long minimaReputacionParaCritica) {
    	Narracion narracion = new Narracion(this, titulo, texto, genero, minimaReputacionParaCritica)
    	narraciones << narracion
    	return narracion
    }

    Concurso crearConcurso(String titulo,   
        String descripcion, 
        Long recompensa, 
        Long minimaReputacionParaParticipar, 
        Narracion.Genero genero) {
        return new Concurso(this, titulo, descripcion, recompensa, minimaReputacionParaParticipar, genero)
    }

    void participarEnConcurso(Narracion narracion, Concurso concurso) {
        concurso.registrarParticipacion(narracion)
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