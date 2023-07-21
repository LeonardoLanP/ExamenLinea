<%@ page import="mx.edu.utez.exameneslinea.model.Persona" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../assets/css/estiloHeader/admin.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" type="text/css" href="../assets/css/admin/gestion.css">
</head>
<style type="text/css">
    input[name="matricula"] {
        display: none;
    }
</style>
<body>

<!-- FORMULARIO PARA EL REGISTRO DE USUARIOS -->
<div class="overlay" id="overlay">
    <div class="pop-up" id="pop-up">
        <a href="#" id="btn-cerrar" class="btn-cerrar"><i class="bi bi-x-lg"></i></a>
        <h2 class="equipo">Registro de usuarios</h2>
        <form accept="" method="post" action="../reg-person">
            <input type="text" name="nombre" placeholder="Nombres" required="">
            <input type="text" name="apellido1" placeholder="Apellido paterno" required="">
            <input type="text" name="apellido2" placeholder="Apellido materno">
            <input type="text" name="CURP" placeholder="CURP" required="" maxlength="18"  onkeyup="convertirMayusculas(this)">
            <input type="email" name="correo" placeholder="correo@institucional" required="" value="@utez.edu.mx">
            <input type="text" name="matricula" placeholder="Matricula" required="">
            <label for="rol">Selecciona el rol del nuevo usuario:</label><br>
            <select name="rol" id="rol" onchange="mostrarOcultarMatricula()">
                <option value="">Seleccione un Rol</option>
                <option value="estudiante">Estudiante</option>
                <option value="docente">Docente</option>
            </select>
            <br><input type="submit" value="Agregar" onclick="validarFormulario(event)">
        </form>
    </div>
</div>

<!-- Termina el registro de usuarios -->

<!-- Formulario para modificar un usuario -->
<input type="checkbox" id="btn-modal">
<div class="container-modal">
    <div class="content-modal">
        <h2 id="nombreuser">Nombre:</h2>
        <form action="../up-usr" method="POST" id="formulario-modal">
            <input type="hidden" name="id_user" id="id_user" value="">

            <label>Nombre/s:</label>
            <input type="text" name="nombre" id="nombre">

            <label>Apellido paterno:</label>
            <input type="text" name="ap1" id="ap1">

            <label>Apellido materno:</label>
            <input type="text" name="ape2" id="ape2">

            <label>CURP:</label>
            <input type="text" name="CURP" id="curp">

            <label>Correo:</label>
            <input type="email" name="correo" placeholder="correo@institucional" required="" id="email">

            <label for="user">Usuario:</label>
            <input type="text" name="usuario" id="user">

            <label>Contraseña:</label>
            <input type="text" name="pass" id="pass">

            <br><input type="submit" name="" value="Modificar" id="btn-enviar">

        </form>
        <div class="btn-cerrar">
            <label for="btn-modal">
                Cancelar
            </label>
        </div>
    </div>
</div>

<!-- Formulario para modificar un usuario -->

<div class="background"></div>
<header class="header">
    <div class="container">
        <div class="btn-menu">
            <label for="btn-menu">☰</label>
        </div>
        <div class="logo"></div>
        <div class="menu">
            <img src="../assets/img/sugel.png" alt="Logo">
        </div>
    </div>
</header>

<input type="checkbox" id="btn-menu">
<div class="container-menu">
    <div class="cont-menu">
        <center>
            <div class="perfil">
                <i class="bi bi-person-circle"></i><br>
                <h4>Admin</h4>
                <h4><%= ((Persona) request.getSession().getAttribute("sesion")).getFirstname() %>
                    <%= ((Persona) request.getSession().getAttribute("sesion")).getSecondname() %></h4>
            </div>
        </center>

        <nav>
            <a href="#btn-modal"  class="editar-usuario" id="btn-abre" onclick="cargarDatosUsuario(<%= ((Persona) request.getSession().getAttribute("sesion")).getUser_id() %>)">Editar Perfil</a>
            <c:choose>
                <c:when test="${personType == 'docente'}">
                    <a href="../person?id=docente"><strong>Gestionar docentes</strong></a>
                </c:when>
                <c:otherwise>
                    <a href="../person?id=docente">Gestionar docentes</a>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${personType == 'estudiante'}">
                    <a href="../person?id=estudiante"><strong>Gestionar estudiante</strong></a>
                </c:when>
                <c:otherwise>
                    <a href="../person?id=estudiante">Gestionar estudiante</a>
                </c:otherwise>
            </c:choose>
            <div class="min-menA">
                <a href="#" class="btn-abrir" id="btn-abrir">Agregar usuario</a>
            </div>
            <div class="salir">
                <a href="../login?sesion=salir">Salir</a>
            </div>
        </nav>
        <label for="btn-menu"><i class="bi bi-x-lg"></i></label>
    </div>
</div>

<div class="contenedor">
    <div class="acciones">
        <h1>Gestión de usuarios</h1>
        <table>
            <thead>
            <tr>
                <td colspan="4">
                    <c:choose>
                        <c:when test="${personType == 'docente'}">Docentes</c:when>
                        <c:when test="${personType == 'estudiante'}">Estudiante</c:when>
                        <c:otherwise>Usuario</c:otherwise>
                    </c:choose>
                </td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${personList}" var="person">
                <tr>
                    <td>
                        <label class="switchBtn">
                            <input type="checkbox" id="toggleSwitch_${person.id_person}" data-estado="${person.status}" ${person.status == 1 ? 'checked' : 'focus'}>
                            <div class="slide round" id="toggleText_${person.id_person}">${person.status == 1 ? 'Activado' : 'Desactivado'}</div>
                        </label>
                    </td>
                    <td colspan="2">${person.lastname1} ${person.lastname2} ${person.firstname} ${person.secondname}</td>
                    <td>
                        <div class="boton-modal">
                            <label for="btn-modal" class="editar-usuario" onclick="cargarDatosUsuario(${person.id_person})">
                                Editar
                            </label>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script type="text/javascript" src="../assets/js/agregar.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    function validarFormulario(event) {
        var curp = document.getElementsByName('CURP')[0].value.trim();
        var correo = document.getElementsByName('correo')[0].value.trim();
        var nombre = document.getElementsByName('nombre')[0].value.trim();
        if (nombre.split(" ").length > 2) {
            event.preventDefault();
            Swal.fire({
                icon: 'warning',
                title: 'Alerta',
                text: 'Colobora tu nombre que este escrito correctamente.',
            })
            return;
        }
        if (curp.length < 18 && curp.length > 0) {
            event.preventDefault();
            Swal.fire({
                icon: 'warning',
                title: 'Alerta',
                text: 'Comprueba tu CURP!',
            })
            return;
        }
        if (!/^[\w.-]+@utez\.edu\.mx$/.test(correo)) {
            event.preventDefault();
            Swal.fire({
                icon: 'warning',
                title: 'Alerta',
                text: 'Colobora tu correo XXXXXXXXX@utez.edu.mx',
            })
            return;
        }
    }
</script>

<script>
    function cargarDatosUsuario(userId) {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var responseText = xhr.responseText;

                var datosUsuario = responseText.split('\n');
                var usuario = {
                    first: datosUsuario[0],
                    second: datosUsuario[1],
                    lastname1: datosUsuario[2],
                    lastname2: datosUsuario[3],
                    curp: datosUsuario[4],
                    email: datosUsuario[5],
                    user: datosUsuario[6],
                    pass: datosUsuario[7],
                    rol: datosUsuario[8],
                    id: datosUsuario[9],
                };

                document.getElementById("id_user").value = usuario.id.trim();
                document.getElementById("nombreuser").value = usuario.first;
                document.getElementById("nombre").value = usuario.first + " " +usuario.second;
                document.getElementById("ap1").value = usuario.lastname1;
                document.getElementById("ape2").value = usuario.lastname2;
                document.getElementById("curp").value = usuario.curp;
                document.getElementById("email").value = usuario.email;
                document.getElementById("user").value = usuario.user;
                document.getElementById("pass").value = usuario.pass;

                var userLabel = document.querySelector('label[for="user"]');
                if (usuario.rol.trim() === "3") {
                    userLabel.textContent = "Usuario/Matricula:";
                } else {
                    userLabel.textContent = "Usuario:";
                }
                document.getElementById("btn-modal").checked = true;

            }
        };


        xhr.open("POST", "../BuscarServlet", true);
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.send("userId=" + userId);
    }
</script>

<script type="text/javascript">
    function mostrarOcultarMatricula() {
        var rol = document.getElementById("rol").value;
        var matriculaInput = document.getElementsByName("matricula")[0];
        if (rol === "estudiante") {
            matriculaInput.style.display = "block";
        } else {
            matriculaInput.style.display = "none";
        }
    }

    function convertirMayusculas(input) {
        input.value = input.value.toUpperCase();
    }
</script>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        var toggleSwitches = document.querySelectorAll(".switchBtn input");

        toggleSwitches.forEach(function(switchBtn) {
            switchBtn.addEventListener("click", function() {
                var estadoActual = this.checked ? 1 : 0;
                var estadoNuevo = estadoActual = 0 ? 0 : 1;
                var mensaje = estadoNuevo === 0 ? "desactivar" : "activar";
                var toggleText = this.nextElementSibling;
                Swal.fire({
                    icon: 'warning',
                    title: '¿Estás seguro de ' + mensaje + ' este usuario?',
                    showCancelButton: true,
                    confirmButtonText: 'OK',
                    cancelButtonText: 'Cancelar',
                }).then((result) => {
                    if (result.isConfirmed) {

                        Swal.fire({
                            icon: 'success',
                            title: 'Update realizado con éxito',
                        });
                        toggleText.textContent = estadoNuevo === 1 ? 'Activado' : 'Desactivado';
                    } else {
                        toggleText.textContent = estadoActual === 1 ? 'Activado' : 'Desactivado';
                        this.checked = estadoActual;
                    }
                });
            });
        });
    });
</script>
</body>
</html>
