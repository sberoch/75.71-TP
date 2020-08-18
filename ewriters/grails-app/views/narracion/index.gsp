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
                <div class="container mt-4" role="status">
                    <div class="alert alert-danger" role="alert">
                        ${flash.message}
                    </div>
                </div>
            </g:if>
            <div class="container">
                <div class="row mt-5">
                    <div class="col">
                        <h6>Ordenar por...</h6>
                        <form action="/">
                            <g:select class="form-control w-50" name="ordenamiento" from="${['Popularidad', 'Fecha de publicacion', 'Nombre']}"
                            valueMessagePrefix="ordenamiento" onchange="javascript:this.form.submit()"
                            noSelection="['':'-Seleccionar-']" />
                        </form>
                    </div>
                    <div class="col">
                        <h4 class="text-right mt-4">Tu reputacion - ${reputacion}</h4>
                    </div>
                </div>  
            </div>
            <g:render template="/templates/narracionEnLista" var="narracion" collection="${narracionList}" />
        </div>
    </body>
</html>