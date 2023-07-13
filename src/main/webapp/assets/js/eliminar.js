  // Obtén todas las clases de materia
    var materias = document.getElementsByClassName('materia');

    // Recorre todas las clases de materia
    for (var i = 0; i < materias.length; i++) {
      // Agrega el evento de doble clic a cada clase de materia
      materias[i].addEventListener('dblclick', function() {
        var materia = this;

        // Crea la ventana emergente de confirmación
        var confirmOverlay = document.createElement('div');
        confirmOverlay.classList.add('confirm-overlay');

        var confirmModal = document.createElement('div');
        confirmModal.classList.add('confirm-modal');
        confirmModal.innerHTML = `
          <h2>¿Estás seguro de que quieres eliminar esta materia?</h2>
          <button id="confirm-yes">Sí</button>
          <button id="confirm-no">No</button>
        `;

        confirmOverlay.appendChild(confirmModal);
        document.body.appendChild(confirmOverlay);

        // Agrega el evento de clic al botón "Sí" de la ventana emergente
        var confirmYesBtn = document.getElementById('confirm-yes');
        confirmYesBtn.addEventListener('click', function() {
          // Elimina la materia si el usuario confirma
          materia.parentNode.removeChild(materia);
          confirmOverlay.remove();
        });

        // Agrega el evento de clic al botón "No" de la ventana emergente
        var confirmNoBtn = document.getElementById('confirm-no');
        confirmNoBtn.addEventListener('click', function() {
          // Cierra la ventana emergente si el usuario cancela
          confirmOverlay.remove();
        });
      });
    }




      // Obtén todas las clases de examen
    var examen = document.getElementsByClassName('examenes');

    // Recorre todas las clases de examen
    for (var i = 0; i < examen.length; i++) {
      // Agrega el evento de doble clic a cada clase de materia
      examen[i].addEventListener('dblclick', function() {
        var examen = this;

        // Crea la ventana emergente de confirmación
        var confirmOver = document.createElement('div');
        confirmOver.classList.add('confirm-over');

        var confirmMod = document.createElement('div');
        confirmMod.classList.add('confirm-mod');
        confirmMod.innerHTML = `
          <h2>¿Estás seguro de que quieres eliminar esta examen?</h2>
          <button id="confirm">Sí</button>
          <button id="no">No</button>
        `;

        confirmOver.appendChild(confirmMod);
        document.body.appendChild(confirmOver);

        // Agrega el evento de clic al botón "Sí" de la ventana emergente
        var confirmYes = document.getElementById('confirm');
        confirmYes.addEventListener('click', function() {
          // Elimina el examen si el usuario confirma
          examen.parentNode.removeChild(examen);
          confirmOver.remove();
        });

        // Agrega el evento de clic al botón "No" de la ventana emergente
        var confirmNo = document.getElementById('no');
        confirmNo.addEventListener('click', function() {
          // Cierra la ventana emergente si el usuario cancela
          confirmOver.remove();
        });
      });
    }