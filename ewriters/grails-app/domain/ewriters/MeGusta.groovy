package ewriters

class MeGusta {
    Usuario usuario
    static belongsTo = [
    	narracion: Narracion
    ]

    MeGusta(Usuario usuario) {
        this.usuario = usuario
    }
}
