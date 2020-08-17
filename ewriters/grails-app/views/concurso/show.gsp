<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'concurso.label', default: 'Concurso')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div id="show-concurso" class="content scaffold-show" role="main">
            <g:if test="${terminado}">
                <g:render template="/templates/concursoTerminado" model="[concurso: concurso]" />
            </g:if>
            <g:else>
                <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
                </g:if>
                <g:render template="/templates/concurso" model="[concurso: concurso]" />
            </g:else>
        </div>
    </body>
</html>
