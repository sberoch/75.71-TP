<div class="container" id="${narracion?.id}">
  <div class="card w-auto h-auto mt-5" style="width: 20rem;">
    <div class="card-body">
      <h3 class="card-title">${narracion?.titulo}</h3>
      <h6 class="card-subtitle mb-2 text-muted">${narracion?.escritor} - ${narracion?.genero}</h6>
      <p class="card-text">${narracion?.texto}</p>
      <div class="container">
        <div class="row">
            <div class="col-xs-1">
                <a href="/narracion/meGusta/${narracion?.id}" class="btn-floating btn-lg btn-default"><i class="fa fa-heart"></i></a>
            </div>
            <div class="col-xs-1">
                <p class="pt-1">${narracion?.cantMeGusta}</p>
            </div>
            <div class="col">
              <g:if test="${ narracion?.minimaReputacionParaCritica != 0 }">
                <h5 class="text-right">Necesitas ${narracion?.minimaReputacionParaCritica} puntos para criticar.</h5>
              </g:if>
            </div>
        </div>
      </div>
    </div>
  </div>
  <div class="card w-auto h-auto mt-1 mb-5 bg-light" style="width: 20rem;">
    <div class="card-body">
      <h4 class="card-title">Comentarios</h4>
      <form class="input-group p-3" action="/narracion/comentar/${narracion?.id}">
        <div class="container">
          <div class="form-check w-auto">
            <input type="checkbox" class="form-check-input" id="esCritica" onclick="onClickCritica()">
            <label class="form-check-label" for="esCritica">Criticar una seccion en particular</label>
            <script>
              function onClickCritica() {
                var checkBox = document.getElementById("esCritica");
                var seccion = document.getElementById("seccionAComentar");
                if (checkBox.checked == true){
                  seccion.disabled = false;
                  seccion.required = true;
                } else {
                  seccion.disabled = true;
                  seccion.value="";
                  seccion.required = false;
                }
              }
            </script>
          </div>
        </div>
        <div class="container mt-3">
          <input id="seccionAComentar" type="text" name="seccionCriticada" class="form-control" placeholder="Seccion criticada" aria-label="Seccion criticada" aria-describedby="basic-addon2" disabled>
        </div>
        <div class="container mt-3">
          <div class="input-group-append">
            <input id="comentario" type="text" name="texto" class="form-control" placeholder="Agregar un comentario" aria-label="Agregar un comentario" aria-describedby="basic-addon2" required>
            <button class="btn btn-outline-secondary" type="submit">Enviar</button>
          </div>
        </div>    
      </form>
      <g:if test="${ narracion?.escritor?.id == usuarioActivo?.id }">
        <g:render template="/templates/comentarioVistoPorEscritor" var="comentario" collection="${narracion?.comentarios}" />   
      </g:if>
      <g:else>
         <g:render template="/templates/comentario" var="comentario" collection="${narracion?.comentarios}" />
      </g:else>    
    </div>
  </div>
</div>


