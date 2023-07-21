<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Preguntas</title>
	<link rel="stylesheet" type="text/css" href="../assets/css/estiloHeader/header.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
	<link rel="stylesheet" type="text/css" href="../assets/css/docente/materias.css">
    <link rel="stylesheet" type="text/css" href="../assets/css/docente/preguntas.css">
</head>

<body>
	<!-- FORMULARIO PARA EL REGISTRO DE USUARIOS-->
	<div class="overlay" id="overlay" >
        <div class="pop-up" id="pop-up">
            <a href="#" id="btn-cerrar" class="btn-cerrar"><i class="bi bi-person-heart"></i></a>
          
        <h2>Nombre <!--DEL USUARIO--></h2>
        <form action="" method="" id="formulario-modal">

          <label>Nombre:</label>
          <input type="text" name="nombre">

          <label>Apellido paterno:</label>
          <input type="text" name="ap1">

          <label>Apellido materno:</label>
          <input type="text" name="ape2">

          <label>CURP:</label>
          <input type="text" name="curp">

          <label>Contraseña:</label>
          <input type="text" name="pass">
              <br><center><input type="submit" name="" value="modificar" id="btn-enviar"></center> 

        </form>
        </div>
    </div>
    <!--Termina el registro de usuarios-->
  

	<div class="background"></div>
	<header class="header">
    <div class="container">
    <div class="btn-menu">
      <label for="btn-menu">☰</label>
    </div>
      <div class="logo">
      </div>
      
      <div class="menu">
        <img src="../assets/img/sugel.png" alt="Logo">
      </div>
    </div>
  </header>

  
<!--  --------------->
<input type="checkbox" id="btn-menu">
<div class="container-menu">
  <div class="cont-menu">

  	<center><div class="perfil"><i class="bi bi-person-heart"></i><br>
  		<h4>Nombre</h4></div></center>

    <nav> 	
      <div class="min-menA">
			<a href="#" class="btn-abrir" id="btn-abrir">Editar perfil</a>
		</div>
      <div class="salir"><a href="">Salir</a></div>
      
    </nav>
    <label for="btn-menu"><i class="bi bi-x-lg"></i></label>
  </div>
</div>

<!--Termina el menu-->


   <!--Formulario para modificar un usuario
      ESTO FUE LO QUE SE CAMBIO
<input type="checkbox" id="btn-modal">
    <div class="container-modal">
      <div class="content-modal">
          <h2 class="equipo">Registro de materia</h2>
            <form accept="" method="">
              <input type="text" name="nombre" placeholder="Nombre de la materia" required="">
              <div class="g">
                <label for="grado">Grado:</label>
              <input type="number" name="grado"  required="">
              <label for="grupo">Grado:</label>
              <input type="text" name="grup" >
              </div>
              <br><input type="submit" name="" value="Agregar"> 
            </form>

        <div class="btn-cerrar">
          <label for="btn-modal">
            Cancelar
          </label>
        </div>
      </div>
    </div>

    TERMINA LA 1° MODIFICACION-->

<!--Comienza el contenido principal-->
			<div class="contenedor">
        <!--Main es todo el contenedor de los recuadros de la materia-->
        <div class="mai">
          <form>
             <h2>Nombre del examen</h2>
            <div class="abierta">
              <textarea placeholder="Ingresa la pregunta del examen"></textarea>
            </div>


            <div class="multiple">
              <textarea placeholder="Ingresa la pregunta del examen"></textarea>

              <input type="text" name="opcion" placeholder="Ingresa la opción">
              <input type="text" name="opcion" placeholder="Ingresa la opción">

               <div class="boton-opcion">
                    <label for="btn-opcion" onclick="agregarInput(this)">
                      +
                    </label>
                  </div>
            </div>
 <div class="multiple">
              <textarea placeholder="Ingresa la pregunta del examen"></textarea>

              <input type="text" name="opcion" placeholder="Ingresa la opción">
              <input type="text" name="opcion" placeholder="Ingresa la opción">

               <div class="boton-opcion">
                    <label for="btn-opcion" onclick="agregarInput(this)">
                      +
                    </label>
                  </div>
            </div>

             <div class="multiple">
              <textarea placeholder="Ingresa la pregunta del examen"></textarea>

              <input type="text" name="opcion" placeholder="Ingresa la opción">
              <input type="text" name="opcion" placeholder="Ingresa la opción">

               <div class="boton-opcion">
                    <label for="btn-opcion" onclick="agregarInput(this)">
                      +
                    </label>
                  </div>
            </div>






            <input type="submit" name="">
            
          </form>
        </div>
				
			</div>

  <script type="text/javascript" src="../assets/js/agregar.js"></script>


<script>
    function agregarInput(boton) {
      var divMultiple = boton.parentElement.parentElement; // Navegamos al div "multiple" padre del botón
      var ultimosInputs = divMultiple.querySelectorAll('input[name="opcion"]');
      var ultimoInput = ultimosInputs[ultimosInputs.length - 1];

      var nuevoInput = document.createElement('input');
      nuevoInput.setAttribute('type', 'text');
      nuevoInput.setAttribute('name', 'opcion');
      nuevoInput.setAttribute('placeholder', 'Ingresa la opción');

      divMultiple.insertBefore(nuevoInput, ultimoInput.nextSibling);
    }
  </script>


</body>
</html>