<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'taller.label', default: 'Taller')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div id="list-taller" class="content scaffold-list" role="main">
            <g:if test="${flash.message}">
                <div class="container mt-4" role="status">
                    <div class="alert alert-danger" role="alert">
                        ${flash.message}
                    </div>
                </div>
            </g:if>
            <g:if test="${tallerList != null && tallerList.size() > 0}">
                <div class="container">
                    <h2 class="mt-5">Actualmente participas de los siguientes talleres</h2>
                </div>
            </g:if>
            <g:else>
                <div class="container">
                    <h2 class="mt-5">Actualmente no participas de ningun taller</h2>
                </div>
            </g:else>
            <g:render template="/templates/tallerEnLista" var="taller" collection="${tallerList}" />
            <div class="container">
                <h3 class="mt-5">¿Deseas crear un taller?</h3>
                <form class="input-group p-3" action="/taller/crear">
                    <input type="text" name="titulo" class="form-control" placeholder="Titulo del taller" aria-label="Titulo del taller" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="submit">Crear</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>