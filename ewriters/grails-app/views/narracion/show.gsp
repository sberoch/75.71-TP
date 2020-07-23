<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'narracion.label', default: 'Narracion')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div id="show-narracion" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:display bean="narracion" except="['minimaReputacionParaCritica', 'popularidad', 'comentarios', 'criticas', 'publica', 'meGusta', 'cantMeGusta']"/>
            <g:textField name="meGustaCount" value="${meGustaCount}" />
            <form class="form-inline my-2 my-lg-0">
                <g:hiddenField name="id" value="${this.narracion?.id}" />
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit" formaction="/narracion/meGusta">Me Gusta</button>
            </form>
            <g:form resource="${this.narracion}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="btn btn-outline-success my-2 my-sm-0" action="meGusta" resource="${this.narracion}">Me Gusta</g:link>
                    <g:link class="edit" action="edit" resource="${this.narracion}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
            <g:form action="comentar">
                <fieldset class="form">
                    <g:field name="texto" type="text" value="Agregar un comentario"/>
                    <input type="submit" formaction="/narracion/comentar?id=${this.narracion?.id}" value="Enviar"/>
                </fieldset>
            </g:form>
            <f:table collection="${comentarios}" properties="escritor, texto"/>
        </div>
    </body>
</html>
