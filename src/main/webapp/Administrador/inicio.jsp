<%@ page import="mx.edu.utez.exameneslinea.model.Person" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/estiloHeader/admin.css">
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
            <input type="text" name="nombre" placeholder="Nombres*" required="" maxlength="45">
            <input type="text" name="apellido1" placeholder="Apellido paterno*" required="" maxlength="30">
            <input type="text" name="apellido2" placeholder="Apellido materno" maxlength="30">
            <input type="text" name="CURP" placeholder="CURP*" required="" maxlength="18"  onkeyup="convertirMayusculas(this)">
            <input type="email" name="correo" placeholder="correo@institucional*" required="" value="@utez.edu.mx" maxlength="45">
            <input type="text" name="matricula" placeholder="Matricula*" required="" maxlength="15">
            <label for="rol">Selecciona el rol del nuevo usuario:</label><br>
            <select name="rol" id="rol" onchange="mostrarOcultarMatricula()">
                <option value="">Seleccione un Rol*</option>
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

            <label>Nombres*:</label>
            <input type="text" name="nombre" id="nombre" maxlength="45">

            <label>Apellido paterno*:</label>
            <input type="text" name="ap1" id="ap1" maxlength="30">

            <label>Apellido materno:</label>
            <input type="text" name="ape2" id="ape2" maxlength="30">

            <label>CURP*:</label>
            <input type="text" name="CURP" id="curp" maxlength="18">

            <label>Correo*:</label>
            <input type="email" name="correo" placeholder="correo@institucional" required="" id="email" maxlength="45">

            <label for="user">Usuario*:</label>
            <input type="text" name="usuario" id="user" maxlength="30">
            <label>Actualizar contraseña:</label>
            <input type="text" name="pass" id="pass" value="">

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
                <h4><%= ((Person) request.getSession().getAttribute("sesion")).getName() %></h4>
            </div>
        </center>

        <nav>
            <a href="#btn-modal"  class="editar-usuario" id="btn-abre" onclick="cargarDatosUsuario(<%= ((Person) request.getSession().getAttribute("sesion")).getID_user() %>)">Editar Perfil</a>
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
                            <input type="checkbox" id="toggleSwitch_${person.id_person}" ${person.user_status == 1 ? 'checked' : ''} onChange="updateUserStatus(${person.id_person})">
                            <div class="slide round" id="toggleText_${person.id_person}">
                            </div>
                        </label>
                    </td>
                    <td colspan="2">${person.lastname1} ${person.lastname2} ${person.name}</td>
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
        var rol = document.getElementById("rol").value;

        if (nombre.split(" ").length > 2) {
            event.preventDefault();
            Swal.fire({
                icon: 'warning',
                title: 'Alerta',
                text: 'Colobora tu nombre que este escrito correctamente.',
                timer: 3000,
            })
            return;
        }
        if (curp.length < 18 && curp.length > 0) {
            event.preventDefault();
            Swal.fire({
                icon: 'warning',
                title: 'Alerta',
                text: 'Comprueba tu CURP!',
                timer: 3000,
            })
            return;
        }
        if (!/^[\w.-]+@utez\.edu\.mx$/.test(correo)) {
            event.preventDefault();
            Swal.fire({
                icon: 'warning',
                title: 'Alerta',
                text: 'Colobora tu correo XXXXXXXXX@utez.edu.mx',
                timer: 3000,
            })
            return;
        }

            if (rol === "") {
                event.preventDefault(); // Evitar que el formulario se envíe
                Swal.fire({
                    icon: 'warning',
                    title: 'Alerta',
                    text: 'Por favor, seleccione un rol antes de registrar.',
                    timer: 3000,
                })
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
                    name: datosUsuario[0].trim(),
                    lastname1: datosUsuario[1].trim(),
                    lastname2: datosUsuario[2],
                    curp: datosUsuario[3].trim(),
                    email: datosUsuario[4].trim(),
                    user: datosUsuario[5],
                    rol: datosUsuario[6],
                    id: datosUsuario[7],
                };

                document.getElementById("id_user").value = usuario.id.trim();
                document.getElementById("nombreuser").value = usuario.name;
                    document.getElementById("nombre").value = usuario.name;
                document.getElementById("ap1").value = usuario.lastname1;
                document.getElementById("ape2").value = usuario.lastname2;
                document.getElementById("curp").value = usuario.curp;
                document.getElementById("email").value = usuario.email;
                document.getElementById("user").value = usuario.user;

                var userLabel = document.querySelector('label[for="user"]');
                if (usuario.rol.trim() === "3") {
                    userLabel.textContent = "Usuario/Matricula*:";
                } else {
                    userLabel.textContent = "Usuario*:";
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
            matriculaInput.required = true;
        } else {
            matriculaInput.style.display = "none";
            matriculaInput.required = false;
            matriculaInput.value = "";
        }
    }

    function convertirMayusculas(input) {
        input.value = input.value.toUpperCase();
    }
</script>

<script>
    function updateUserStatus(personId) {
        var checkbox = document.getElementById("toggleSwitch_" + personId);
        var estado = checkbox.checked ? 1 : 0;
        var action = estado === 0 ? 'Desactivar' : 'Activar';

        Swal.fire({
            icon: 'warning',
            title: '¿Estás seguro de '+ action + ' este usuario?',
            text: 'Esta acción '+ action + 'á al usuario',
            showCancelButton: true,
            confirmButtonText: 'Aceptar',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "../UpdateStatusServlet", true);
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        console.log(xhr.responseText);
                        Swal.fire({
                            icon: 'success',
                            title: 'Cambios guardados',
                            showConfirmButton: false,
                            timer: 1500,
                        });
                    }
                };
                xhr.send("personId=" + personId + "&estado=" + estado);
            } else {
                checkbox.checked = !estado;
            }
        });
    }
</script>


</body>
</html>
