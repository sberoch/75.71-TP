<%@ page import="ewriters.Genero" %>

<div class="container">
  <div class="row">
    <div class="col-md-12">
      <h1 class="mt-5">Crear un concurso</h1>
      <form class="mt-5" action="/concurso/crear">
          <div class="form-group">
            <label for="fc-titulo">Titulo</label>
            <input type="text" name="titulo" class="form-control" id="fc-titulo" placeholder="Titulo del concurso" required>
          </div>
          <div class="form-group">
            <label for="fc-desc">Descripcion</label>
            <textarea class="form-control" name="descripcion" id="fc-desc" rows="3" placeholder="Descripcion del concurso" required></textarea>
          </div>
          <div class="form-group">
              <label for="fc-genero">Genero de las narraciones</label>
              <g:select class="form-control" name="genero" from="${Genero.values()*.value}" id="fc-genero" />
          </div>
          <div class="form-group">
            <label for="fc-minRep">Minima reputacion para participar</label>
            <input type="number" class="form-control" name="minimaReputacionParaParticipar" id="fc-minRep" placeholder="100" required>
          </div>
          <div class="form-group">
            <label for="fc-recompensa">Recompensa por ganar</label>
            <input type="number" class="form-control" name="recompensa" id="fc-recompensa" placeholder="300" required>
          </div>
          <p class="font-weight-light mt-5">Al enviar el concurso comenzara inmediatamente y terminara dentro de una semana. ¡Ganara la narracion participante con mayor cantidad de Me Gusta!</p>
          <button type="submit" class="btn btn-primary btn-lg btn-block mt-1">Enviar</button>
      </form>
    </div>
  </div>
</div>