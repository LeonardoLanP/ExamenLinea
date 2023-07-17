<%@ page import="mx.edu.utez.exameneslinea.model.Persona" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Materias</title>
	<link rel="stylesheet" type="text/css" href="../assets/css/estiloHeader/header.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
	<link rel="stylesheet" type="text/css" href="../assets/css/docente/materias.css">
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
        <div>
            <h4><%= ((Persona) request.getSession().getAttribute("sesion")).getFirstname() %></h4>
            <!-- <h4><%= ((Persona) request.getSession().getAttribute("sesion")).getLastname1() %></h4>
            <h4><%= ((Persona) request.getSession().getAttribute("sesion")).getLastname2() %></h4> -->
        </div>
    </center>
    <nav> 	
      <div class="min-menA">
			<a href="#" class="btn-abrir" id="btn-abrir">Editar perfil</a>
		</div>
      <div class="salir"><a href="../login?sesion=salir">Salir</a></div>
      
    </nav>
    <label for="btn-menu"><i class="bi bi-x-lg"></i></label>
  </div>
</div>

<!--Termina el menu-->


   <!--Formulario para modificar un usuario
      ESTO FUE LO QUE SE CAMBIO-->
<input type="checkbox" id="btn-modal">
    <div class="container-modal">
      <div class="content-modal">
          <h2 class="equipo">Registro de materia</h2>
            <form accept="" method="post" action="../reg-materia">
              <input type="text" name="nombre" placeholder="Nombre de la materia" required="">
              <div class="g">
                <label for="grado">Grado:</label>
              <input type="number" name="grado" id="grado"  required="">
              <label for="grupo">Grupo:</label>
              <input type="text" name="grupo" id="grupo" required="">
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

    <!--TERMINA LA 1° MODIFICACION-->

<!--Comienza el contenido principal-->
			<div class="contenedor">
        <!--Main es todo el contenedor de los recuadros de la materia-->
                <div class="main">
                <c:forEach items="${subjectlista}" var="materia">
                    <!-- materia es el contedor completo de la materia y todo el recuadro es a su vez un enlace a ver los examenes de esa materia-->
                    <div class="materia">
                        <!-- esto es opcional lo haría yo con js pero pienso poner que cada materia tome una imagen diferente de un catalogo de 6 imagenes solo que como no es algo funcional como tal lo dejare al final-->
                        <div class="img">
                            <img src="../assets/img/class.png">
                        </div>
                        <div class="info">
                            <h2>${materia.nombre_materia}</h2>
                            <h3>${materia.grado} ° ${materia.gupo}</h3>
                        </div>
                    </div>
                 </c:forEach>
                </div>
                <!--2° MODIFICACIÓN-->
                <div class="boton-modal">
                    <label for="btn-modal">
                        +
                    </label>
                </div>
                <!-- ULTIMA-->

            </div>







	<script type="text/javascript" src="../assets/js/agregar.js"></script>
  <script type="text/javascript" src="../assets/js/eliminar.js"></script>


</body>
</html>