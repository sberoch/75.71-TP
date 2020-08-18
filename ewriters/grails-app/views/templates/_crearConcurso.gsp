<%@ page import="ewriters.Genero" %>

<div class="container">
  <div class="row">
    <div class="col-md-12">
      <h1 class="mt-5">Crear un concurso</h1>
      <form class="mt-5" action="/concurso/crear">
          <div class="form-group">
            <label for="fc-titulo">Titulo</label>
            <input type="text" name="titulo" class="form-control" id="fc-titulo" placeholder="Titulo del concurso">
          </div>
          <div class="form-group">
            <label for="fc-desc">Texto</label>
            <textarea class="form-control" name="descripcion" id="fc-desc" rows="3" placeholder="Descripcion del concurso"></textarea>
          </div>
          <div class="form-group">
              <label for="fc-genero">Genero de las narraciones</label>
              <g:select class="form-control" name="genero" from="${Genero.values()*.value}" id="fc-genero" />
          </div>
          <div class="form-group">
            <label for="fc-minRep">Minima reputacion para participar</label>
            <input type="number" class="form-control" name="minimaReputacionParaParticipar" id="fc-minRep" placeholder="100">
          </div>
          <div class="form-group">
            <label for="fc-recompensa">Recompensa</label>
            <input type="number" class="form-control" name="recompensa" id="fc-recompensa" placeholder="300">
          </div>
          <button type="submit" class="btn btn-primary btn-lg btn-block">Enviar</button>
      </form>
    </div>
  </div>
</div>