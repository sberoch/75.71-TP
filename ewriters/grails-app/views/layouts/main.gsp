<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="/">EWriters</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
      <li class="nav-item active">
        <a class="nav-link" href="/">Inicio</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/concursos">Concursos</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/talleres">Talleres</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/narracion/create">Escribir una narracion</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Buscar" name="query">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" formaction="/narracion/search">Buscar</button>
    </form>
  </div>
</nav>

    <g:layoutBody/>



    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

    <asset:javascript src="application.js"/>

</body>
</html>
