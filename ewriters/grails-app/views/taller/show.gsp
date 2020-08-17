<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'taller.label', default: 'Taller')}" />
        <title>${this.taller?.titulo}</title>
    </head>
    <body>
        <div id="show-taller" class="content scaffold-show" role="main">
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:render template="/templates/taller" model="[taller: taller]" />
        </div>
    </body>
</html>
