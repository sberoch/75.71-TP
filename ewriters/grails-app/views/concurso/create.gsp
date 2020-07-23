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
                    <f:field bean="concurso" property="creador"/>
                    <f:field bean="concurso" property="titulo"/>
                    <f:field bean="concurso" property="descripcion"/>
                    <f:field bean="concurso" property="genero"/>
                    <f:field bean="concurso" property="recompensa"/>
                    <f:field bean="concurso" property="minimaReputacionParaParticipar"/>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
