<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'narracion.label', default: 'Narracion')}" />
        <title><g:message code="ewriters.title.home" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-narracion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="ewriters.nav.home"/></a></li>
                <li><g:link class="create" action="create"><g:message code="ewriters.new.narracion" /></g:link>
                </li>
            </ul>
        </div>
        <div id="list-narracion" class="content scaffold-list" role="main">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${narracionList}" properties="titulo, texto, genero, escritor, cantMeGusta"/>

            <div class="pagination">
                <g:paginate total="${narracionCount ?: 0}" />
            </div>
        </div>
    </body>
</html>