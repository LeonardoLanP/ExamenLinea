<%@ page import="mx.edu.utez.exameneslinea.model.Person" %>
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

            <h2 id="nombreuser">Nombre:</h2>
        <form action="../up-doce" method="POST" id="formulario-modal">
           <input type="hidden" name="id_user" id="id_user" value="">

            <label>Nombre/s*:</label>
          <input type="text" name="nombre" id="nombre" maxlength="45">

          <label>Apellido paterno*:</label>
          <input type="text" name="ap1" id="ap1" maxlength="30">

          <label>Apellido materno*:</label>
          <input type="text" name="ape2" id="ape2" maxlength="30">

          <label>CURP*:</label>
          <input type="text" name="CURP" id="curp" maxlength="18">

          <label>Contraseña:</label>
          <input type="text" name="pass" id="pass">
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
            <h4><%= ((Person) request.getSession().getAttribute("sesion")).getName() %></h4>
        </div>
    </div>
    </center>
    <nav> 	
      <div class="min-menA">
			<a href="#" class="btn-abrir" id="btn-abrir" onclick="cargarDatosUsuario(<%= ((Person) request.getSession().getAttribute("sesion")).getId_person()%>)">Editar perfil</a>
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
              <input type="text" name="nombre" placeholder="Nombre de la materia*" required="">
              <div class="g">
                <label for="grado">Grado*:</label>
              <input type="number" name="grado" id="grado"  required="">
              <label for="grupo">Grupo*:</label>
              <input type="text" name="grupo" id="grupo" required="" >
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
                            <a href="../ser-mater?materiaId=${materia.id_sub}">
                                <!-- esto es opcional lo haría yo con js pero pienso poner que cada materia tome una imagen diferente de un catalogo de 6 imagenes solo que como no es algo funcional como tal lo dejare al final-->
                                <div class="img">
                                    <img src="./../assets/img/class.svg">
                                </div>
                                <div class="info">
                                    <h2>${materia.subname}</h2>
                                    <h3>${materia.grade} ° ${materia.grouSub}</h3>
                                </div>
                            </a>
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
    <script>
        function cargarDatosUsuario(userId) {
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var responseText = xhr.responseText;

                    var datosUsuario = responseText.split('\n');
                    var usuario = {
                        name: datosUsuario[0],
                        lastname1: datosUsuario[1],
                        lastname2: datosUsuario[2],
                        curp: datosUsuario[3],
                        id: datosUsuario[7],
                    };

                    document.getElementById("id_user").value = usuario.id.trim();
                    document.getElementById("nombreuser").value = usuario.name;
                    document.getElementById("nombre").value = usuario.name;
                    document.getElementById("ap1").value = usuario.lastname1;
                    document.getElementById("ape2").value = usuario.lastname2;
                    document.getElementById("curp").value = usuario.curp;

                    document.getElementById("overlay").checked = true;
                }
            };


            xhr.open("POST", "../BuscarServlet", true);
            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhr.send("userId=" + userId);
        }
    </script>



</body>
</html>