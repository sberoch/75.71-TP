<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'narracion.label', default: 'Narracion')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div id="create-narracion" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.narracion}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.narracion}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="save">
                <fieldset class="form">
                    <f:field bean="narracion" property="escritor"/>
                    <f:field bean="narracion" property="titulo"/>
                    <f:field bean="narracion" property="texto"/>
                    <f:field bean="narracion" property="genero"/>
                    <f:field bean="narracion" property="minimaReputacionParaCritica"/>
                    <f:field bean="narracion" property="popularidad"/>
                    <f:field bean="narracion" property="cantMeGusta"/>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
