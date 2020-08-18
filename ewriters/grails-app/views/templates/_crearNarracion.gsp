<%@ page import="ewriters.Genero" %>

<div class="container">
  <div class="row">
    <div class="col-md-12">
      <h1 class="mt-5">Escribir una narracion</h1>
      <form class="mt-5" action="${action}">
          <div class="form-group">
            <label for="fc-titulo">Titulo</label>
            <input type="text" name="titulo" class="form-control" id="fc-titulo" placeholder="Titulo de la narracion">
          </div>
          <g:if test="${espacio != 'concurso'}">
            <div class="form-group">
              <label for="fc-genero">Genero</label>
              <g:select class="form-control" name="genero" from="${Genero.values()*.value}" id="fc-genero" />
            </div>
          </g:if>
          <div class="form-group">
            <label for="fc-texto">Texto</label>
            <textarea class="form-control" name="texto" id="fc-texto" rows="10"></textarea>
          </div>
          <g:if test="${espacio == 'espacioPrincipal'}">
            <div class="form-group">
              <label for="fc-minRep">Minima reputacion para criticar</label>
              <input type="number" class="form-control" name="minimaReputacionParaCritica" id="fc-minRep" placeholder="100">
            </div>
          </g:if>
          <button type="submit" class="btn btn-primary btn-lg btn-block">Enviar</button>
      </form>
    </div>
  </div>
</div>

