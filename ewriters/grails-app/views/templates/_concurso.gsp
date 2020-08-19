<div class="container" id="${concurso?.id}">
  <div class="card w-auto h-auto my-5 bg-light" style="width: 20rem;">
    <div class="card-body">
      <h3 class="card-title mx-3">${concurso?.titulo}</h3>
      <h5 class="card-subtitle mx-3 text-muted">${concurso?.descripcion}</h5>
      <h6 class="card-subtitle mt-2 mx-3 text-muted">${concurso?.genero}</h6>
      <div class="container mt-5">
        <div class="row">
            <div class="col-6">
                <h5>Recompensa - ${concurso.recompensa} puntos <i class="fa fa-trophy text-warning"></i></h5>
            </div>
            <div class="col-6">
                <h5 class="text-right">Reputacion necesaria - ${concurso.minimaReputacionParaParticipar} puntos</h5>
            </div>
        </div>
      </div>
      <form class="mx-3 mt-2" action="/concurso/participar/${concurso?.id}">
        <button type="submit" class="btn btn-primary btn-block">Participar</button>
      </form>
    </div>
  </div>
  <g:if test="${concurso?.narraciones != null && concurso?.narraciones.size() > 0}">
     <h5 class="my-3">Narraciones participantes</h5>
  </g:if>
</div>
<g:render template="/templates/narracionEnLista" var="narracion" collection="${concurso?.narraciones.sort().reverse()}" />