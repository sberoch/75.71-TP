<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'concurso.label', default: 'Concurso')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div id="show-concurso" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:display bean="concurso" except="['narraciones', 'fechaCreacion']"/>
            <f:table collection="${this.concurso?.narraciones}" properties="titulo, texto, genero, escritor, cantMeGusta"/>
            <g:form resource="${this.concurso}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.concurso}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
