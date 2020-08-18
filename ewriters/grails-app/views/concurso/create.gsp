<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'concurso.label', default: 'Concurso')}" />
        <title>Crear concurso</title>
    </head>
    <body>
        <div id="create-concurso" class="content scaffold-create" role="main">
            <g:if test="${flash.message}">
                <div class="container mt-4" role="status">
                    <div class="alert alert-danger" role="alert">
                        ${flash.message}
                    </div>
                </div>
            </g:if>
            <g:hasErrors bean="${this.concurso}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.concurso}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:render template="/templates/crearConcurso" />
        </div>
    </body>
</html>
