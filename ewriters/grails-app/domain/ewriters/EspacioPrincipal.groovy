package ewriters

class EspacioPrincipal extends EspacioDePublicacion {

    static hasMany = [narraciones: Narracion]

    static constraints = {
    }

    void agregarNarracion(Narracion narracion) {
        this.addToNarraciones(narracion)
    }

    String toString() {
        return "Espacio Principal"
    }
}
