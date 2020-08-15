<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'concurso.label', default: 'Concurso')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div id="create-concurso" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.concurso}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.concurso}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="save">
                <fieldset class="form">
                    <g:field name="titulo" type="text"/>
                    <g:field name="descripcion" type="text"/>
                    <g:field name="recompensa" type="number"/>
                    <g:field name="minimaReputacionParaParticipar" type="number"/>
                </fieldset>
                <fieldset class="buttons">
                    <input type="submit" formaction="/concurso/crear" value="Enviar"/>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
