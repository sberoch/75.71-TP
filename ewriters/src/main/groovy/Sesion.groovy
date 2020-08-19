
package ewriters

class Sesion {

    Long usuarioActivoId

    Usuario getUsuarioActivo() {
        return Usuario.get(usuarioActivoId)
    }

    void cambiarUsuario(String nombre) {
        Usuario usuario = Usuario.findByNombreApellido(nombre)
        if (usuario) {
            usuarioActivoId = usuario.id
        }
    }
}