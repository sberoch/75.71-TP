package ewriters
import java.time.*

class Concurso extends EspacioDePublicacion {

	String titulo
	String descripcion
	Long recompensa
	Long minimaReputacionParaParticipar
	Genero genero
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
    	String genero) {

    	this.titulo = titulo 
		this.descripcion = descripcion 
		this.recompensa = recompensa 
		this.minimaReputacionParaParticipar = minimaReputacionParaParticipar 
		this.genero = genero as Genero
		this.fechaCreacion = LocalDateTime.now()
    }

    String toString() {
        titulo
    }

    void comenzar() {
        println("Comenzo un concurso!")
        def fechaFinalizacion = Date.from(this.fechaCreacion.plusDays(7).atZone(ZoneId.systemDefault()).toInstant())
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
        if (narraciones && !narraciones.isEmpty()) {
            narraciones.max().escritor.ganarConcurso(recompensa)
        }
    }

    boolean terminado() {
        fechaCreacion.plusDays(7) < LocalDateTime.now()
    }
}
