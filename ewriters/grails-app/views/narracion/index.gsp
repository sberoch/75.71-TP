<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'narracion.label', default: 'Narracion')}" />
        <title><g:message code="ewriters.title.home" args="[entityName]" /></title>
    </head>
    <body>
        <div id="list-narracion" class="content scaffold-list" role="main">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:render template="/templates/narracionEnLista" var="narracion" collection="${narracionList}" />
        </div>
    </body>
</html>