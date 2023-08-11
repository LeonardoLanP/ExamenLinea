document.addEventListener('DOMContentLoaded', () => {
  // Obtener todos los elementos con la clase 'materia'
  const materias = document.querySelectorAll('.materia');

  // Lista de las imágenes disponibles
  const imagenesDisponibles = [
    'class.svg',
    'estudiantes.svg',
    'profesores.svg',
    'usD.svg',
    'usE.svg',
    'docente.svg'
  ];

  // Función para obtener un índice aleatorio entre 0 y el número de imágenes disponibles
  function getRandomIndex() {
    return Math.floor(Math.random() * imagenesDisponibles.length);
  }

  // Función para cambiar la imagen y los estilos cuando el checkbox cambia de estado
  function updateImageAndStyles(event) {
    const checkbox = event.target;
    const materiaDiv = checkbox.closest('.materia');
    const materiaImg = materiaDiv.querySelector('.materiaImg');

    if (checkbox.checked) {
      // Si el checkbox está marcado, quitar la clase 'disabled' y restaurar una imagen aleatoria
      materiaDiv.classList.remove('disabled');
      const randomIndex = getRandomIndex();
      materiaImg.src = `../assets/img/${imagenesDisponibles[randomIndex]}`;
    } else {
      // Si el checkbox no está marcado, añadir la clase 'disabled' y establecer la imagen desactivada
      materiaDiv.classList.add('disabled');
      materiaImg.src = '../assets/img/desactivada.svg';
    }
  }

  // Añadir un listener al evento 'change' del checkbox para cada div .materia
  materias.forEach((materia) => {
    const checkbox = materia.querySelector('input[type="checkbox"]');
    checkbox.addEventListener('change', updateImageAndStyles);
    updateImageAndStyles({ target: checkbox }); // Ejecutar la función una vez para que los estilos e imagen iniciales sean correctos
  });
});



document.addEventListener('DOMContentLoaded', () => {


  // Obtener todos los elementos con la clase 'examenes'
  const examenes = document.querySelectorAll('.examenes');

  // Función para cambiar la imagen y los estilos cuando el checkbox cambia de estado
  function updateImageAndStyles(event) {
    const checkbox = event.target;
    const examenDiv = checkbox.closest('.examenes');
    const imagen = examenDiv.querySelector('.imagen img');

    if (checkbox.checked) {
      // Si el checkbox está marcado, quitar la clase 'disabled' y restaurar la imagen normal
      examenDiv.classList.remove('disabled');
      imagen.src = 'https://img.freepik.com/vector-gratis/ilustracion-concepto-examenes_114360-1815.jpg?w=2000';
    } else {
      // Si el checkbox no está marcado, añadir la clase 'disabled' y establecer la imagen desactivada
      examenDiv.classList.add('disabled');
      imagen.src = '../assets/img/desactivada.svg';
    }
  }

  // Añadir un listener al evento 'change' del checkbox para cada div .examenes
  examenes.forEach((examen) => {
    const checkbox = examen.querySelector('input[type="checkbox"]');
    checkbox.addEventListener('change', updateImageAndStyles);
    updateImageAndStyles({ target: checkbox }); // Ejecutar la función una vez para que los estilos e imagen iniciales sean correctos
  });

});




