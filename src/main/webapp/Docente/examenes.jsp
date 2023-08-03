<%@ page import="mx.edu.utez.exameneslinea.model.Person" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Examenes</title>
	<link rel="stylesheet" type="text/css" href="../assets/css/estiloHeader/header.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
	<link rel="stylesheet" type="text/css" href="../assets/css/docente/materias.css">
		<link rel="stylesheet" type="text/css" href="../assets/css/docente/boton.css">
</head>
<body>

	<!-- FORMULARIO PARA EL REGISTRO DE USUARIOS-->

    <div class="overlay" id="overlay" >
        <div class="pop-up" id="pop-up">
            <a href="#" id="btn-cerrar" class="btn-cerrar"><i class="bi bi-person-heart"></i></a>
            <h2 id="nombreuser">Nombre:</h2>
            <form action="../docente/actualizar-datos-docente" method="POST" id="formulario-modal">
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
          <h2 class="equipo">Registro de examen</h2>
          <form method="post" action="../examen/registrar-examen">
              <input type="text" name="nombreex" placeholder="Nombre del examen*" required=""
                     maxlength="45" id="nombreex"><br><br>
              <div class="g">
                  <label>Ingresa el tipo de preguntas de tu examen</label>
                  <div class="respuesta-radio">
                      <input type="radio" name="tipo" id="opcion1" class="respuesta" value="Abierta">
                      <label for="opcion1">Abiertas</label>
                  </div>
                  <div class="respuesta-radio">
                      <input type="radio" name="tipo" id="opcion2" class="respuesta" value="Multiple">
                      <label for="opcion2">Cerradas</label>
                  </div>
                  <br><br>
                  <label>Ingresa la cantidad de preguntas que debe tener cada examen*:</label>
                  <input type="number" name="numberex" required="" id="numberex" min="5" max="100">
              </div>
              <br>
              <input type="submit" value="Agregar">
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
                <div class="m">

            <!-- materia es el contedor completo de la materia y todo el recuadro es a su vez un enlace a ver los examenes de esa materia-->
<c:forEach items="${exam}" var="examen">
      		    <div class="examenes">
                    <label class="switchBtn">
                        <input type="checkbox" >
                        <div class="slide round" ></div>
                    </label>
        <div class="imagen">
                <img src="https://img.freepik.com/vector-gratis/ilustracion-concepto-examenes_114360-1815.jpg?w=2000"/>
        </div>
        <div class="pie">
            <a href="../examen/buscar-pregunta?examenid=${examen.id_exam}">
            <p><strong>Codigo: ${examen.code}</strong></p>
            <p><strong>EXAMEN: ${examen.namex}</strong></p>
            <p>Estado Examen: ${examen.statusex}</p>
            <p>Realizado por: </p>
            </a>
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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <script>
        function cargarDatosUsuario(userId) {
            $.ajax({
                type: "POST",
                url: "../admin/buscar-datos",
                data: { userId: userId },
                success: function(data) {
                    var datosUsuario = data.split('\n');
                    var usuario = {
                        name: datosUsuario[0].trim(),
                        lastname1: datosUsuario[1].trim(),
                        lastname2: datosUsuario[2].trim(),
                        curp: datosUsuario[3].trim(),
                        id: datosUsuario[7].trim(),
                    };

                    $("#id_user").val(usuario.id);
                    $("#nombreuser").val(usuario.name);
                    $("#nombre").val(usuario.name);
                    $("#ap1").val(usuario.lastname1);
                    $("#ape2").val(usuario.lastname2);
                    $("#curp").val(usuario.curp);

                    $("#overlay").prop("checked", true);
                },
                error: function() {
                    console.log("Error en la solicitud Ajax.");
                }
            });
        }
    </script>



</body>
</html>
