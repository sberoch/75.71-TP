<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'narracion.label', default: 'Narracion')}" />
        <title>Remover un usuario</title>
    </head>
    <body>
        <div id="remover-usuario" class="content scaffold-create" role="main">
            <h1>Remover un usuario</h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:form action="save">
                <fieldset class="form">
                    <g:field name="nombre" type="text" value="Nombre del usuario"/>
                </fieldset>
                <fieldset class="buttons">
                    <input type="submit" formaction="/taller/removerUsuario?id=${taller.id}" value="Enviar"/>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>