

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


 // Obtener referencia al botón de enviar y al botón de cerrar el modal
  const btnEnviar = document.getElementById('btn-enviar');
  const btn_Cerrar = document.getElementById('btn-modal');

  // Agregar evento al botón de enviar para cerrar el modal al hacer clic
  btnEnviar.addEventListener('click', () => {
    btn_Cerrar.checked = false; // Desmarca el checkbox del botón de cerrar modal
  });




