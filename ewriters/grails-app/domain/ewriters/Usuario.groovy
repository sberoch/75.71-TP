package ewriters

class Usuario {

	String nombreApellido
	Long reputacion
	Set narraciones = []

	static hasMany = [narraciones: Narracion]

    static constraints = {
    }

    Usuario(String nombreApellido) {
    	this.nombreApellido = nombreApellido
		this.reputacion = 0
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
}