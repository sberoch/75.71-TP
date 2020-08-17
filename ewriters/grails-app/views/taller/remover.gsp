<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'narracion.label', default: 'Narracion')}" />
        <title>Remover un usuario</title>
    </head>
    <body>
        <div id="remover-usuario" class="content scaffold-create" role="main">
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <div class="container">
              <h3 class="mt-5">Remover un usuario</h3>
              <form class="input-group mt-4" action="/taller/removerUsuario/${taller.id}">
                  <input type="text" name="nombre" class="form-control" placeholder="Nombre del usuario" aria-label="Nombre del usuario" aria-describedby="basic-addon2">
                  <div class="input-group-append">
                      <button class="btn btn-outline-primary" type="submit">Remover</button>
                  </div>
              </form>
            </div>
        </div>
    </body>
</html>