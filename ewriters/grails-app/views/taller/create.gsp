<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'taller.label', default: 'Taller')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div id="create-taller" class="content scaffold-create" role="main">
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.taller}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.taller}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.taller}" method="POST">
                <fieldset class="form">
                    <g:field name="titulo" type="text" value="Titulo"/>
                </fieldset>
                <fieldset class="buttons">
                    <input type="submit" formaction="/taller/crear" value="Enviar"/>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
