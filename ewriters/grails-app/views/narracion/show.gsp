<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'narracion.label', default: 'Narracion')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div id="show-narracion" class="content scaffold-show" role="main">
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:render template="/templates/narracion" model="[narracion: narracion]" />
            <g:form action="criticar">
                <fieldset class="form">
                    <g:field name="texto" type="text" value="Agregar una critica"/>
                    <g:field name="seccionCriticada" type="text" value=""/>
                    <input type="submit" formaction="/narracion/criticar?id=${this.narracion?.id}" value="Enviar"/>
                </fieldset>
            </g:form>
            <f:table collection="${criticas}" properties="escritor, texto, seccionCriticada"/>
        </div>
    </body>
</html>
