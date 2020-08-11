package ewriters
import java.time.*

class Concurso extends EspacioDePublicacion {

	String titulo
	String descripcion
	Long recompensa
	Long minimaReputacionParaParticipar
	Narracion.Genero genero
	LocalDateTime fechaCreacion

	static hasMany = [narraciones: Narracion]
	static belongsTo = [creador: Usuario]
    static constraints = {
        titulo blank: false
        descripcion blank: false
        recompensa blank: false
        minimaReputacionParaParticipar blank: false
        genero blank: false
    }

    Concurso(String titulo,	
    	String descripcion,	
    	Long recompensa, 
    	Long minimaReputacionParaParticipar, 
    	Narracion.Genero genero) {

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

    void agregarNarracion(Narracion narracion, Usuario escritor) {
        if (escritor.reputacion < this.minimaReputacionParaParticipar) {
    		throw new IllegalStateException("No se tiene reputacion suficiente para participar")
    	} else if (narracion.genero != this.genero) {
    		throw new IllegalStateException("Genero de la narracion no es el pedido por el concurso")
    	} else {
    		this.addToNarraciones(narracion)
    	}
    }

    void finalizar() {
        println("Termino un concurso!")
        if (!this.narraciones.isEmpty()) {
            this.narraciones.max().escritor.ganarConcurso(recompensa)
        }
    }

    boolean terminado() {
        return fechaCreacion.plusSeconds(7) < LocalDateTime.now()
    }
}
