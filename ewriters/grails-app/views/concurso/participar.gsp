<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'narracion.label', default: 'Narracion')}" />
        <title>Participar del concurso</title>
    </head>
    <body>
        <div id="enviar-narracion-concurso" class="content scaffold-create" role="main">
            <g:if test="${flash.message}">
                <div class="container mt-4" role="status">
                    <div class="alert alert-danger" role="alert">
                        ${flash.message}
                    </div>
                </div>
            </g:if>
            <g:set var="action" value="${"/concurso/enviarNarracion/${concurso.id}"}" />
            <g:set var="espacio" value="${"concurso"}" />
            <g:render template="/templates/crearNarracion" model="[espacio: espacio, action: action]" />
        </div>
    </body>
</html>