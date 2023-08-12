

var btnAbrir = document.getElementById('btn-abrir');
var overlay = document.getElementById('overlay');
var pop = document.getElementById('pop-up');
var btnCerrar = document.getElementById('btn-cerrar');
var menuCheckbox = document.getElementById('btn-menu');

btnAbrir.addEventListener('click', function () {
  overlay.classList.add('active');
  // Cerrar el menú lateral al abrir el overlay
  menuCheckbox.checked = false;
});

btnCerrar.addEventListener('click', function () {
  overlay.classList.remove('active');
});





