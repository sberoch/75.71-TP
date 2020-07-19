package ewriters
import java.time.*

class Concurso {

	String titulo
	String descripcion
	Long recompensa
	Long minimaReputacionParaParticipar
	Narracion.Genero genero
	Set<Narracion> narraciones = []
	LocalDateTime fechaCreacion
	Usuario creador

	static hasMany = [narraciones: Narracion]
	static belongsTo = [creador: Usuario]
    static constraints = {
        titulo blank: false
        descripcion blank: false
        recompensa blank: false
        minimaReputacionParaParticipar blank: false
        genero blank: false
        creador nullable: false
    }

    Concurso(Usuario creador, 
    	String titulo,	
    	String descripcion,	
    	Long recompensa, 
    	Long minimaReputacionParaParticipar, 
    	Narracion.Genero genero) {

    	this.creador = creador
    	this.titulo = titulo 
		this.descripcion = descripcion 
		this.recompensa = recompensa 
		this.minimaReputacionParaParticipar = minimaReputacionParaParticipar 
		this.genero = genero
		this.fechaCreacion = LocalDateTime.now()
    }

    String toString() {
        return titulo
    }

    void comenzar() {
        println("Comenzo un concurso!")
        def fechaFinalizacion = Date.from(this.fechaCreacion.plusSeconds(7).atZone(ZoneId.systemDefault()).toInstant())
        DeterminarGanadorConcursoJob.schedule(fechaFinalizacion, [concurso: this])
    }

    void registrarParticipacion(Narracion narracion) {
    	if (narracion.genero != this.genero) {
    		throw new IllegalStateException("Genero de la narracion no es el pedido por el concurso")
    	} else if (narracion.escritor.reputacion < minimaReputacionParaParticipar) {
    		throw new IllegalStateException("No se tiene reputacion suficiente para participar")
    	} else {
    		narraciones << narracion
    	}
    }

    void finalizar() {
        println("Termino un concurso!")
        if (!this.narraciones.isEmpty()) {
            this.narraciones.max().escritor.ganarConcurso(recompensa)
        }
    }

    boolean terminado() {
        return fechaCreacion.plusDays(7) < LocalDateTime.now()
    }
}
