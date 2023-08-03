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

<!-- ... Código HTML existente ... -->

<style>
    /* Agregar el siguiente estilo para resaltar el campo con error */
    input.error {
        background-color: #f54021;
    }
</style>

<body>

<!-- FORMULARIO PARA EL REGISTRO DE USUARIOS -->
<div class="overlay" id="overlay">
    <div class="pop-up" id="pop-up">
        <a href="#" id="btn-cerrar" class="btn-cerrar"><i class="bi bi-x-lg"></i></a>
        <h2 class="equipo">Registro de usuarios</h2>
        <form accept="" method="post" action="../admin/registro-user">
            <input type="text" name="nombre" placeholder="Nombres*" required="" maxlength="45">
            <input type="text" name="apellido1" placeholder="Apellido paterno*" required="" maxlength="30">
            <input type="text" name="apellido2" placeholder="Apellido materno" maxlength="30">
            <input type="text" name="CURP" placeholder="CURP*" required="" maxlength="18" onkeyup="convertirMayusculas(this)">
            <input type="email" name="correo" placeholder="correo@institucional*" required="" value="@utez.edu.mx" maxlength="45">
            <label for="rol">Selecciona el rol del nuevo usuario:</label><br>
            <select name="rol" id="rol" onchange="mostrarOcultarMatricula()">
                <option value="">Seleccione un Rol*</option>
                <option value="estudiante">Estudiante</option>
                <option value="docente">Docente</option>
            </select>
            <input type="text" name="matricula" placeholder="Matricula*" required="" maxlength="15">
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
        <form action="../admin/actualizar-user" method="POST" id="formulario-modal">

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
                    <a href="../admin/gestion-docente-alumno?id=docente"><strong>Gestionar docentes</strong></a>
                </c:when>
                <c:otherwise>
                    <a href="../admin/gestion-docente-alumno?id=docente">Gestionar docentes</a>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${personType == 'estudiante'}">
                    <a href="../admin/gestion-docente-alumno?id=estudiante"><strong>Gestionar estudiante</strong></a>
                </c:when>
                <c:otherwise>
                    <a href="../admin/gestion-docente-alumno?id=estudiante">Gestionar estudiante</a>
                </c:otherwise>
            </c:choose>
            <div class="min-menA">
                <a href="#"  class="btn-abrir" id="btn-abrir" for="btn-menu">Agregar usuario</a>
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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!--<script>
    function validarFormulario(event) {
        if (event) {
            event.preventDefault();
        }

        $('input').removeClass('error');


        var curp = document.getElementsByName('CURP')[0].value.trim();
        var correo = document.getElementsByName('correo')[0].value.trim();
        var nombre = document.getElementsByName('nombre')[0].value.trim();
        var apellido1 = document.getElementsByName('apellido1')[0].value.trim();
        var apellido2 = document.getElementsByName('apellido2')[0].value.trim();
        var rol = document.getElementById("rol").value;
        var matricula = document.getElementsByName('matricula')[0].value.trim();



        // Validar el nombre
        var nombreInput = document.getElementsByName('nombre')[0];
        if (!/^[A-Z][a-z]*$/.test(nombre)) {
            Swal.fire({
                icon: 'warning',
                title: 'Alerta',
                text: 'Verifica que tu nombre esté escrito correctamente.',
                timer: 3000,
            });
            nombreInput.classList.add('error');
        } else {
            nombreInput.classList.remove('error');
        }

        // Validar los apellidos
        var apellido1Input = document.getElementsByName('apellido1')[0];
        if (!/^([A-Z][a-z]*\s*)+$/.test(apellido1)) {
            Swal.fire({
                icon: 'warning',
                title: 'Alerta',
                text: 'Verifica que el primer apellido esté escrito correctamente.',
                timer: 3000,
            });
            apellido1Input.classList.add('error');
        } else {
            apellido1Input.classList.remove('error');
        }

        var apellido2Input = document.getElementsByName('apellido2')[0];
        if (apellido2 !== "" && !/^([A-Z][a-z]*\s*)+$/.test(apellido2)) {
            Swal.fire({
                icon: 'warning',
                title: 'Alerta',
                text: 'Verifica que el segundo apellido esté escrito correctamente.',
                timer: 3000,
            });
            apellido2Input.classList.add('error');
        } else {
            apellido2Input.classList.remove('error');
        }

        // Validar la CURP
        var curpInput = document.getElementsByName('CURP')[0];
        if (!/^[A-Z0-9]{18}$/.test(curp)) {
            Swal.fire({
                icon: 'warning',
                title: 'Alerta',
                text: 'Verifica tu CURP.',
                timer: 3000,
            });
            curpInput.classList.add('error');
        } else {
            curpInput.classList.remove('error');


        var correoInput = document.getElementsByName('correo')[0];
        if (!/^[\w.-]+@utez\.edu\.mx$/.test(correo)) {
            Swal.fire({
                icon: 'warning',
                title: 'Alerta',
                text: 'El correo debe tener el formato XXXXXXXXX@utez.edu.mx',
                timer: 3000,
            });
            correoInput.classList.add('error');
        } else {
            correoInput.classList.remove('error');
        }

        var rolInput = document.getElementById("rol");
        if (rol === "") {
            Swal.fire({
                icon: 'warning',
                title: 'Alerta',
                text: 'Por favor, seleccione un rol antes de registrar.',
                timer: 3000,
            });
            rolInput.classList.add('error');
        } else {
            rolInput.classList.remove('error');
        }

        if (rol === 'estudiante') {
            var matriculaInput = document.getElementsByName('matricula')[0];
            if (!/^[A-Z0-9]{1,15}$/.test(matricula)) {
                Swal.fire({
                    icon: 'warning',
                    title: 'Alerta',
                    text: 'Registre la matrícula correctamente (solo letras mayúsculas y números).',
                    timer: 3000,
                });
                matriculaInput.classList.add('error');
            } else {
                matriculaInput.classList.remove('error');
            }
        }

        // Si todas las validaciones son correctas, enviar el formulario
        if (!document.querySelector('input.error')) {
            document.querySelector('form').submit();
        }
    }

    function convertirMayusculaPrimerLetra(input) {
        var palabras = input.value.split(' ');
        var resultado = '';
        for (var i = 0; i < palabras.length; i++) {
            resultado += palabras[i].charAt(0).toUpperCase() + palabras[i].slice(1).toLowerCase();
            if (i < palabras.length - 1) {
                resultado += ' ';
            }
        }
        input.value = resultado;
    }


    $(document).ready(function () {
        mostrarOcultarMatricula();
        $('#rol').on('change', function () {
            mostrarOcultarMatricula();
        });

        // Evento oninput para activar la validación en tiempo real en cada input
        $('input[name="nombre"]').on('input', function (event) {
            validarFormulario(event);
        });

        $('input[name="apellido1"]').on('input', function (event) {
            validarFormulario(event);
        });

        $('input[name="apellido2"]').on('input', function (event) {
            validarFormulario(event);
        });

        $('input[name="CURP"]').on('input', function (event) {
            validarFormulario(event);
        });

        $('input[name="correo"]').on('input', function (event) {
            validarFormulario(event);
        });

        $('input[name="matricula"]').on('input', function (event) {
            validarFormulario(event);
        });

        $('#rol').on('change', function (event) {
            validarFormulario(event);
        });
    });
</script>
-->


<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.7/dist/sweetalert2.min.js"></script>
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
                    email: datosUsuario[4].trim(),
                    user: datosUsuario[5].trim(),
                    rol: datosUsuario[6].trim(),
                    id: datosUsuario[7].trim(),
                };
                $("#id_user").val(usuario.id);
                $("#nombreuser").text("Nombre: " + usuario.name);
                $("#nombre").val(usuario.name);
                $("#ap1").val(usuario.lastname1);
                $("#ape2").val(usuario.lastname2);
                $("#curp").val(usuario.curp);
                $("#email").val(usuario.email);
                $("#user").val(usuario.user);

                var userLabel = $('label[for="user"]');
                if (usuario.rol === "3") {
                    userLabel.text("Usuario/Matricula*:");
                } else {
                    userLabel.text("Usuario*:");
                }
                document.getElementById("btn-modal").checked = true;

                // Mostrar la alerta al hacer clic en el botón "Modificar"
                $("#btn-enviar").on("click", function(event) {
                    event.preventDefault();

                    Swal.fire({
                        icon: 'warning',
                        title: '¿Estás seguro de modificar este usuario?',
                        text: 'Esta acción modificará al usuario',
                        showCancelButton: true,
                        confirmButtonText: 'Aceptar',
                        cancelButtonText: 'Cancelar'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            Swal.fire({
                                icon: 'success',
                                title: 'Cambios guardados',
                                showConfirmButton: false,
                                timer: 1500,
                            });
                            $("#formulario-modal").submit();
                        }
                    });
                });
            },
            error: function() {
                console.log("Error en la solicitud Ajax.");
            }
        });
    }
</script>


<script type="text/javascript">
    function convertirMayusculas(element) {
        element.value = element.value.toUpperCase();
    }

    function mostrarOcultarMatricula() {
        var rol = $('#rol').val();
        var matriculaInput = $('input[name="matricula"]');
        if (rol === 'estudiante') {
            matriculaInput.prop('required', true);
            matriculaInput.show();
        } else {
            matriculaInput.prop('required', false);
            matriculaInput.val('');
            matriculaInput.hide();
        }
    }
</script>

<script>
    function updateUserStatus(personId) {
        var checkbox = document.getElementById("toggleSwitch_" + personId);
        var estado = checkbox.checked ? 1 : 0;
        var action = estado === 0 ? 'Desactivar' : 'Activar';

        Swal.fire({
            icon: 'warning',
            title: '¿Estás seguro de ' + action + ' este usuario?',
            text: 'Esta acción ' + action + 'á al usuario',
            showCancelButton: true,
            confirmButtonText: 'Aceptar',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    type: "POST",
                    url: "../admin/cambio-status",
                    data: { personId: personId, estado: estado },
                    success: function (data) {
                        console.log(data);
                        Swal.fire({
                            icon: 'success',
                            title: 'Cambios guardados',
                            showConfirmButton: false,
                            timer: 1500,
                        });
                    },
                    error: function () {
                        console.log("Error en la solicitud Ajax.");
                        Swal.fire({
                            icon: 'error',
                            title: 'Error al guardar los cambios',
                            text: 'Hubo un problema al intentar guardar los cambios.',
                            showConfirmButton: true,
                        });
                        checkbox.checked = !estado;
                    }
                });
            } else {
                checkbox.checked = !estado;
            }
        });
    }
</script>


</body>
</html>
