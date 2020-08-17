<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'concurso.label', default: 'Concurso')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div id="list-concurso" class="content scaffold-list" role="main">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:render template="/templates/concursoEnLista" var="concurso" collection="${concursoList}" />
            <div class="container">
                <h3 class="mt-5">¿Deseas crear un concurso?</h3>
                <form class="input-group p-3" action="/concurso/create">
                    <button class="btn btn-primary btn-lg px-5" type="submit">Crear</button>
                </form>
            </div>
        </div>
    </body>
</html>