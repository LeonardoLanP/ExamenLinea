<%@ page import="mx.edu.utez.exameneslinea.model.Person" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
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
            <form id="examenForm">
                <h2>Nombre del examen</h2>

                <c:forEach items="${questions}" var="question">
                    <c:choose>
                        <c:when test="${question.answer_id == 1 && question.ques_id == 1}">
                            <div class="abierta">
                                <textarea class="pregunta" data-id="${question.ques_id}" placeholder="Ingresa la pregunta del examen"></textarea>
                            </div>
                        </c:when>
                        <c:when test="${question.answer_id == 1 && question.ques_id != 1}">
                            <div class="abierta">
                                <textarea class="pregunta" data-id="${question.ques_id}" placeholder="Ingresa la pregunta del examen">${question.question}</textarea>
                            </div>
                        </c:when>
                        <c:when test="${question.answer_id == 2 && question.ques_id == 1}">
                            <div class="multiple">
                                <textarea class="pregunta" data-id="${question.ques_id}" placeholder="Ingresa la pregunta del examen"></textarea>
                                <input type="text" class="opcion" data-id="${question.ques_id}" placeholder="Ingresa la opción">
                                <input type="text" class="opcion" data-id="${question.ques_id}" placeholder="Ingresa la opción">
                                <div class="boton-opcion" id="btn-opcionSD">
                                    <label for="btn-opcionSD" onclick="agregarInput(this)">+</label>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${question.answer_id > 2 && question.ques_id != 1}">
                            <div class="multiple">
                                <textarea class="pregunta" data-id="${question.ques_id}" placeholder="Ingresa la pregunta del examen">${question.question}</textarea>
                                <input type="text" class="opcion" data-id="${question.ques_id}" placeholder="Ingresa la opción">
                                <input type="text" class="opcion" data-id="${question.ques_id}" placeholder="Ingresa la opción">
                                <div class="boton-opcion" id="btn-opcionCD">
                                    <label for="btn-opcionCD" onclick="agregarInput(this)">+</label>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>
                </c:forEach>
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


    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            class InputData {
                constructor(originalId, newId) {
                    this.originalId = originalId;
                    this.newId = newId;
                }
            }

            var inputDataMap = {};

            function sendDataToServer(id, value) {
                var dataToSend = {
                    "id": id,
                    "value": value
                };

                $.ajax({
                    url: "../RegistrarQuestionServlet",
                    method: "POST",
                    data: JSON.stringify(dataToSend),
                    contentType: "application/json",
                    success: function(data) {
                        console.log("Datos enviados satisfactoriamente");
                        var originalId = id;
                        var newId = data.newId;
                        var inputData = new InputData(originalId, newId);
                        inputDataMap[originalId] = inputData;
                        $("textarea[data-id='" + originalId + "']").data("id", newId);
                        $("input[data-id='" + originalId + "']").data("id", newId);
                        refreshForm();
                    },
                    error: function(xhr, status, error) {
                        console.error("Error en la solicitud AJAX:", status, error);
                    }
                });
            }

            $("textarea.pregunta, input.opcion").on("input", function() {
                var id = $(this).data("id");
                var value = $(this).val();
                sendDataToServer(id, value);
            });

            function refreshForm() {
                $("textarea.pregunta, input.opcion").each(function() {
                    var id = $(this).data("id");
                    if (inputDataMap.hasOwnProperty(id)) {
                        var newId = inputDataMap[id].newId;
                        $(this).data("id", newId);
                    }
                });
            }
        });
    </script>




</body>
</html>