<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'narracion.label', default: 'Narracion')}" />
        <title>Participar del concurso</title>
    </head>
    <body>
        <div id="enviar-narracion-concurso" class="content scaffold-create" role="main">
            <h1>Participar del concurso</h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:form action="save">
                <fieldset class="form">
                    <g:field name="titulo" type="text"/>
                    <g:field name="texto" type="text"/>
                </fieldset>
                <fieldset class="buttons">
                    <input type="submit" formaction="/concurso/enviarNarracion?id=${concurso.id}" value="Enviar"/>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>