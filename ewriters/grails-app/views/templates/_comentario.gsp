<div class="container" id="${comentario?.id}">
  <div class="card w-auto h-auto mt-3">
    <div class="card-body">
      <h6 class="card-title">${comentario?.escritor}</h6>
      <g:if test="${ comentario?.seccionCriticada != null }">
         <div class="card bg-light">
          <div class="card-body">
            <p class="font-italic">${comentario?.seccionCriticada}</p>
          </div>
        </div>
      </g:if>
      <p class="card-text mt-2">${comentario?.texto}</p>
    </div>
    <g:if test="${ comentario?.respuesta != null }">
       <div class="card-footer text-muted">
          <p>Respuesta del autor: ${comentario?.respuesta}</p>
        </div>
    </g:if>
  </div>
</div>