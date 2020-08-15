<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'narracion.label', default: 'Narracion')}" />
        <title>Escribir una Narracion</title>
    </head>
    <body>
        <div id="escribir-narracion-taller" class="content scaffold-create" role="main">
            <h1>Escribir una narracion</h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:form action="save">
                <fieldset class="form">
                    <g:field name="titulo" type="text" value="Titulo"/>
                    <g:field name="texto" type="text"/>
                </fieldset>
                <fieldset class="buttons">
                    <input type="submit" formaction="/taller/agregarNarracion?id=${taller.id}" value="Enviar"/>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>