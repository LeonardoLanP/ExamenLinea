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

</style>
<body>

<!-- FORMULARIO PARA EL REGISTRO DE USUARIOS -->
<div class="overlay" id="overlay">
    <div class="pop-up" id="pop-up">
        <a href="#" id="btn-cerrar" class="btn-cerrar"><i class="bi bi-x-lg"></i></a>
        <h2 class="equipo">Registro de estudiantes</h2>
        <form accept="" method="post" action="../reg-person">
            <input type="text" name="nombre" placeholder="Nombres" required="">
            <input type="text" name="apellido1" placeholder="Apellido paterno" required="">
            <input type="text" name="apellido2" placeholder="Apellido materno">
            <input type="text" name="CURP" placeholder="CURP" required="" maxlength="18">
            <input type="email" name="correo" placeholder="correo@institucional" required="" value="@utez.edu.mx">
            <label for="rol">Selecciona el rol del nuevo usuario:</label><br>
            <select name="rol" id="rol">
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
        <h2>Nombre <!--DEL USUARIO--></h2>
        <form action="#" method="POST" id="formulario-modal">
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

            <label for="rol">Selecciona el estado del usuario:</label><br>
            <select name="estado" id="estado">
                <option value="true">Activo</option>
                <option value="false" disabled>Desactivado</option>
            </select>
            <br><input type="submit" value="Modificar" id="btn-enviar">
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
            </div>
        </center>

        <nav>
            <a href="">Editar Perfil</a>
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
                <td colspan="3">
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
                    <td colspan="2">${person.firstname} ${person.secondname} ${person.lastname1} ${person.lastname2}</td>
                    <td>
                        <div class="boton-modal">
                            <label for="btn-modal">
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
        if (curp.length < 18 && curp.length > 5) {
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

</body>
</html>
