package ewriters

class EspacioPrincipal extends EspacioDePublicacion {

    static hasMany = [narraciones: Narracion]

    static constraints = {
    }

    void agregarNarracion(Narracion narracion, Usuario escritor) {
        this.addToNarraciones(narracion)
    }
}
