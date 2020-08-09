package ewriters

class EspacioPrincipal extends EspacioDePublicacion {

    static hasMany = [narraciones: Narracion]

    static constraints = {
    }

    EspacioPrincipal() {}

    void agregarNarracion(Narracion narracion) {
        this.addToNarraciones(narracion)
    }
}
