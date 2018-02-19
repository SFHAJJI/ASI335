<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>Information QR code</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link type="text/css" rel="stylesheet" href="inc/InformationUsertop.css" />
<link type="text/css" rel="stylesheet" href="inc/InformationUser.css" />
<style>
			* {
				box-sizing: border-box;
			}
			
			body {
				margin: 0;
				font-family: Arial;
			}
			
			/* The grid: Three equal columns that floats next to each other */
			.column {
				float: left;
				width: 50%;
				padding: 50px;
				text-align: center;
				font-size: 20px;
				cursor: pointer;
				color: #000000;
				border-top:    1px;
  				border-right:  1px;
  				border-bottom: 0px;
 				 border-left: 1px;
 				border-style:solid; 
 				border-color:black;
			}
			
			.containerTab {
				padding: 20px;
				color: #000000;
				border-top:    0px;
  				border-right:  1px;
  				border-bottom: 1px;
 				border-left: 1px;
 				border-style:solid; 
 				border-color:black;
			}
			
			/* Clear floats after the columns */
			.row:after {
				content: "";
				display: table;
				clear: both;
			}
			
			/* Closable button inside the container tab */
			.closebtn {
				float: right;
				color: white;
				font-size: 35px;
				cursor: pointer;
			}
		</style>

</head>

<body>

	<form method="get" action="Logout">
		<div class="col-90">
			<input type="submit" value="Déconnexion" />
		</div>
	</form>

	<div class="navbar">
		<a href="informationUser.jsp">Accueil</a> <a href="securite.jsp">Sécurité</a> <a>Google Authentificator</a>
	</div>

	<div class="container">
		<form method="get" action="ActivateDesactivateGoogle">
			<br>
			<center>
				<p class="info">
					<b>${ message }</b>
				</p>
			</center>
			<div class="col-85">
					<b>Google Authentificator est ${ utilisateur.googleAuth }</b>
				</div>
			
				
				<div class="col-15">
				<input type="submit" value="Activer / desactiver">
			
			</div>
		</form>
		<b>SI vous voulez associer un autre téléphone à votre compte, scanner ce QR code</b>
		<img src="${utilisateur.qrCode }" >

		
	</div>

	</body>
	</html>