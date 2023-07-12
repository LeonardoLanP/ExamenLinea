<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title></title>
	<link rel="stylesheet" type="text/css" href="../assets/css/estiloHeader/header.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
	<link rel="stylesheet" type="text/css" href="../assets/css/docente/materias.css">
</head>
<style type="text/css">

</style>
<body>

	<!-- FORMULARIO PARA EL REGISTRO DE USUARIOS-->
	<div class="overlay" id="overlay" >
        <div class="pop-up" id="pop-up">
            <a href="#" id="btn-cerrar" class="btn-cerrar"><i class="bi bi-x-lg"></i></a>
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

  	<center><div class="perfil"><i class="bi bi-person-circle"></i><br>
  		<h4>Nombre</h4></div></center>

    <nav> 	
      <a href="">Editar Perfil</a>
      <div class="min-menA">
			<a href="#" class="btn-abrir" id="btn-abrir">Agregar materia</a>
		</div>
      <div class="salir"><a href="">Salir</a></div>
      
    </nav>
    <label for="btn-menu"><i class="bi bi-x-lg"></i></label>
  </div>
</div>

<!--Termina el menu-->

<!--Comienza el contenido principal-->
			<div class="contenedor">
        <!--Main es todo el contenedor de los recuadros de la materia-->
        <div class="main">
            <!-- materia es el contedor completo de la materia y todo el recuadro es a su vez un enlace a ver los examenes de esa materia-->
            <a href="">
              <div class="materia">
                <!-- esto es opcional lo haría yo con js pero pienso poner que cada materia tome una imagen diferente de un catalogo de 6 imagenes solo que como no es algo funcional como tal lo dejare al final-->
                    <div class="img">
                      <img src="../assets/img/class.svg">
                    </div>
                    <div class="info">
                      <h2>Nombre de la materia</h2>
                      <h3>3°B</h3>
                    </div>
              </div>
           </a>
          
          
        </div>
				
			</div>







	<script type="text/javascript" src="../assets/js/agregar.js"></script>
</body>
</html>