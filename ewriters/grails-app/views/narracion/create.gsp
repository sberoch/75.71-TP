<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'narracion.label', default: 'Narracion')}" />
        <title>Escribir una Narracion</title>
    </head>
    <body>
        <div id="create-narracion" class="content scaffold-create" role="main">
            <g:if test="${flash.message}">
                <div class="container mt-4" role="status">
                    <div class="alert alert-danger" role="alert">
                        ${flash.message}
                    </div>
                </div>
            </g:if>
            <g:hasErrors bean="${this.narracion}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.narracion}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:set var="action" value="${"/narracion/crearEnEspacioPrincipal"}" />
            <g:set var="espacio" value="${"espacioPrincipal"}" />
            <g:render template="/templates/crearNarracion" model="[espacio: espacio, action: action]" />
        </div>
    </body>
</html>
