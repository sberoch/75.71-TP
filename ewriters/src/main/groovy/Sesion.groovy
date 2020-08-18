
package ewriters

class Sesion {

    Long usuarioActivoId

    Usuario getUsuarioActivo() {
        return Usuario.get(usuarioActivoId)
    }
}