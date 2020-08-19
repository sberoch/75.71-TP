<div class="container mt-5" id="${taller?.id}">
  <div class="row">
    <div class="col-9">
      <h2 class="card-title mx-3 mt-5 py-3 text-center">${taller?.titulo}</h2>
      <div class="container">
        <div class="row">
          <g:if test="${ taller?.creador?.id == usuarioActivo?.id }">
            <div class="col">
              <form>
                <button type="submit" formaction="/taller/agregar/${taller?.id}" class="btn btn-primary btn-block">Agregar usuario</button>
              </form>
            </div>
            <div class="col">
              <form>
                <button type="submit" formaction="/taller/remover/${taller?.id}" class="btn btn-primary btn-block">Remover usuario</button>
              </form>
            </div>
          </g:if>
          <div class="col">
            <form>
              <button type="submit" formaction="/taller/escribir/${taller?.id}" class="btn btn-primary btn-block">Escribir narracion</button>
            </form>
          </div>
        </div>
      </div>
      <g:render template="/templates/narracionEnLista" var="narracion" collection="${taller?.narraciones}" />
    </div>
    <div class="col-3">
      <div class="card w-auto h-100 my-5 bg-light" style="width: 20rem;">
        <div class="card-body">
          <h3 class="card-title mx-3">Creador</h3>
          <g:render template="/templates/usuarioEnLista" model="[usuario: taller?.creador]" />
          <h3 class="card-title mx-3 mt-5">Participantes</h3>
          <g:render template="/templates/usuarioEnLista" var="usuario" collection="${taller?.usuarios}" />
        </div>
      </div>
    </div>
  </div>
</div>