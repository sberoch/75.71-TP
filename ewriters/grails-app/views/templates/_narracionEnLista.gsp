<div class="container">
  <div class="card w-auto mt-5" style="width: 20rem;" id="${narracion?.id}">
    <div class="card-body">
      <h3 class="card-title">${narracion?.titulo}</h3>
      <h6 class="card-subtitle mb-2 text-muted">${narracion?.escritor}</h6>
      <p class="card-text">${narracion?.texto}</p>
      <a href="/narracion/show/${narracion?.id}" class="card-link">Ver</a>
    </div>
  </div>
</div>