<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Examen Opción multiple</title>
	 <link rel="stylesheet" type="text/css" href="../assets/css/estiloHeader/header.css">
</head>
<style type="text/css">
 @import url('https://fonts.googleapis.com/css2?family=Urbanist:wght@300;400;500;600;700&display=swap');

body{
    font-family: 'Urbanist', sans-serif;
}
/*FONDO*/
body {
	margin: 0;
	padding: 0;
	
}

.background {
	position: absolute;
	width: 100%;
	background: #f1ffff;
	z-index: -2;
	box-shadow: 0px 3px 8px rgba(0, 0, 0, 25%);
	overflow: hidden;
}

.background::before {
	content: '';
	position: absolute;
	z-index: -2;
	top: -30%;
	left: -50%;
	width: 100%;
	height: 100vh;
	background: linear-gradient(60deg, #03BB85, #03BB85, #03BB85);
	transform-origin: bottom right;
	animation: animate 120s linear infinite;
}

.background::after {
	content: '';
	z-index: -1;
	position: absolute;
	top: -50%;
	left: -50%;
	width: 100%;
	height: 100vh;
	background: linear-gradient(60deg, #001256, #001256, #001256);
	transform-origin: bottom right;
	animation: animate 120 linear infinite;
	animation-delay: -60s;
}

@keyframes animate {
	0% {
		transform: rotate(0deg);
	}
	100% {
		transform: rotate(360deg);
	}
}

.contenedor {
    background-color: #ffffff;
    width: 98%;
    margin: auto;
    position: relative;
    z-index: 1;
    padding-bottom: 30px;
    padding-top: 20px;
    box-shadow: 0px 3px 8px rgba(0, 0, 0, 25%);
    margin-top: 81px;
}
.contenedor h1{
	text-align: center;
	color: #001256;
	margin-top: 20px;
}
@media (min-width: 320px) and (max-width: 425px) {
	form{
		width: 98%;
	}
}
@media (min-width: 768px) and (max-width: 2560px){
	form{
		width: 65%;
	}
}
form {
    margin: 10px auto;
    padding-top: 25px;
    padding-bottom: 20px;
    padding-left: 5%;
    padding-right: 5%;
    border-radius: 10px;
     box-shadow: 0px 3px 8px rgba(0, 0, 0, 25%);
}

.pregunta {
    font-size: 17px;
    font-weight: 500;
    width: 100%;
    overflow: hidden;
    text-align: left;
    margin-bottom: 10px;
    margin-top: 25px;
}
.respuesta-radio {
    display: flex;
    align-items: center;
    margin-top: 10px;
}
.respuesta-radio label{
	padding: 4px;
	display: inline-block;
	position: relative;
	font-size: 1em;
	border-radius: 5px;
	cursor: pointer;
}
.respuesta-radio label:hover{
	background-color: rgba(3, 187, 133, 0.1)
}
.respuesta-radio label:before{
	content: "";
	width: 14px;
	height: 14px;
	display: inline-block;
	background-color: none;
	border-radius: 50%;
	margin-right: 10px;
	border:2px solid#03BB85;
	top: 7px:;
}
.respuesta-radio input[type="radio"]:checked ~	label{

}
.respuesta-radio input[type="radio"] {
    margin-right: 5px;
    display: none;

}

.respuesta-text {
    display: flex;
    width: 100%;
    align-items: center;
    margin-top: 10px;
}

.respuesta-text textarea{
	margin: auto;
	width: 98%;
	resize: none;
	background-color: #F1FCF9;
	border:0;
	padding: 5px;
	height: 70px;
	margin-bottom: 5px;
	font-size: 15px;
	text-align: left;
	border-radius: 10px;
}
.respuesta-text textarea:focus{
	outline: 2px solid #03BB85;
}
input[type="submit"]{
	width: 250px;
	height: 40px;
	font-size: 18px;
	background-color: #03BB85;
	color: #ffffff;
	border:none;
	border-radius: 20px;
	margin: auto;
}
input[type="submit"]:hover{
	background-color: #001256;
}

</style>
<body>
	
	<div class="background">
	</div>
		<header class="header">
    <div class="container">
      <div class="logo">
      </div>
      
      <div class="menu">
        <img src="../assets/img/sugel.png" alt="Logo">
      </div>
    </div>
  </header>


			<div class="contenedor">
    			<h1>Nombre del exámen</h1>
			    <form action="">
			        <div class="pregunta">
			        	
				            <label>Pregunta 1 escoge la respuesta correcta ¿Cual es la raíz cuadrada de 2?</label>
				            <div class="respuesta-radio">
				                <input type="radio" name="opcion2" id="opcion1" class="respuesta">
				                <label for="opcion1">Opción 1</label>
				            </div>
				            <div class="respuesta-radio">
				                <input type="radio" name="opcion2" id="opcion1" class="respuesta">
				                <label for="opcion1">Opción 1</label>
				            </div>
				            <div class="respuesta-radio">
				                <input type="radio" name="opcion2" id="opcion1" class="respuesta">
				                <label for="opcion1">Opción 1</label>
				            </div>
			            
			        </div>

			      

			        <div class="pregunta">
			            <label>Pregunta 2 Escribe la formula para calcular el diametro de un circulo</label>
			            <div class="respuesta-text">
			                <textarea name="respuesta-t" cols="30" rows="5">
			                	
			               	</textarea>
			                
			        </div>
			            

			        <center><input type="submit" name="" value="Finalizar examen"></center>
			    </form>
</div>



	
</body>
</html>