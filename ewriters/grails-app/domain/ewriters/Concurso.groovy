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
	Usuario escritor

	static hasMany = [narraciones: Narracion]
	static belongsTo = [escritor: Usuario]
    static constraints = {
    }

    Concurso(Usuario escritor, 
    	String titulo,	
    	String descripcion,	
    	Long recompensa, 
    	Long minimaReputacionParaParticipar, 
    	Narracion.Genero genero) {

    	this.escritor = escritor
    	this.titulo = titulo 
		this.descripcion = descripcion 
		this.recompensa = recompensa 
		this.minimaReputacionParaParticipar = minimaReputacionParaParticipar 
		this.genero = genero
		this.fechaCreacion = LocalDateTime.now()
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

    //TODO: cambiar para ya asignar los premios al ganador desde aca
    Narracion determinarGanador() {
        return narraciones.max()
    }

    boolean terminado() {
        return fechaCreacion.plusDays(7) < LocalDateTime.now()
    }
}
