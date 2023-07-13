<%@ page import="mx.edu.utez.exameneslinea.model.Persona" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Bienvenido</title>
	 <link rel="stylesheet" type="text/css" href="./../assets/css/estiloHeader/header.css">
	 <link rel="stylesheet" type="text/css" href="./../assets/css/estudiante/acceso.css">
	 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
	<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
</head>
<body>
	<header class="header">
    <div class="container">
    	<div class="btn-menu">
      		<label ><i class="bi bi-box-arrow-left"></i></label>
    	</div>
      <div class="logo">
      </div>
      
      <div class="menu">
        <img src="../assets/img/sugel.png" alt="Logo">
      </div>
    </div>
  </header>


	<div class="hexagon-container">
    <div class="hexagon hexagon-top-left"></div>
    <div class="hexagon hexagon-bottom-right"></div>
  </div>

  <div class="box">
 			<form action="../login-examen" method="post">
 				<h2>¡Bienvenido/a <%= ((Persona) request.getSession().getAttribute("sesion")).getFirstname()%>!</h2>
 				<h3>Ingresa el código de acceso al examen</h3>
 				<img src="../assets/img/docente.svg">
				<div class="inputbox">
					<input type="text" name="codigo" id="codigo" required>
					<span>Código</span>
					<i></i>
				</div>
				<input type="submit" name="acceso" id="acceso" value="Acceder">
				 				
 			</form>
  </div>

	<script src="./../assets/css/js/bootstrap.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script type="text/javascript">
		document.addEventListener('DOMContentLoaded', function() {
			var status = '<%= request.getParameter("status") %>';
			if (status === 'noRegistrado') {
				Swal.fire({
					icon: 'error',
					title: 'Oops...',
					text: 'Codigo de Examen No valido, Intenta de nuevo!',
				})
			} else if (status === 'desactivado') {
				Swal.fire({
					icon: 'error',
					title: 'Oops...',
					text: 'Lo sentimos, El examen ha sido desactivado comunicate con el docente!',
				})
			}
		});
	</script>
</body>
</html>