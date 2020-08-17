<div class="container" id="${narracion?.id}">
  <div class="card w-auto h-auto mt-5" style="width: 20rem;">
    <div class="card-body">
      <h3 class="card-title">${narracion?.titulo}</h3>
      <h6 class="card-subtitle mb-2 text-muted">${narracion?.escritor}</h6>
      <p class="card-text">${narracion?.texto}</p>
      <div class="container">
        <div class="row">
            <div class="col-xs-6 mr-1">
                <a href="/narracion/meGusta/${narracion?.id}" class="btn-floating btn-lg btn-default"><i class="fa fa-heart"></i></a>
            </div>
            <div class="col-xs-6">
                <p class="pt-1">${narracion?.cantMeGusta}</p>
            </div>
        </div>
      </div>
    </div>
  </div>
  <div class="card w-auto h-auto mt-1 bg-light" style="width: 20rem;">
    <div class="card-body">
      <h4 class="card-title">Comentarios</h4>
      <form class="input-group p-3" action="/narracion/comentar/${narracion?.id}">
        <input type="text" name="texto" class="form-control" placeholder="Agregar un comentario" aria-label="Agregar un comentario" aria-describedby="basic-addon2">
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="submit">Enviar</button>
        </div>
      </form>
      <g:render template="/templates/comentario" var="comentario" collection="${narracion?.comentarios}" />
    </div>
  </div>
</div>