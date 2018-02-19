<!DOCTYPE html>
<html>
	<head>
	<link type="text/css" rel="stylesheet" href="inc/style.css" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link type="text/css" rel="stylesheet" href="inc/InformationUser.css" />
		<link type="text/css" rel="stylesheet" href="inc/InformationUsertop.css" />
		<link rel="stylesheet" type="text/css" href="inc/acceuil.css">
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
			<a href="informationUser.jsp">Accueil</a><a>Sécurité</a> <a href="googleConfig.jsp">Google Authentificator</a>
		</div>
		<div class="container">
		<div style="text-align: left">
		<p>hello <br /> <b>${ message }</b></p>
			
		</div>
	
	
	
	
	
		<!-- Three columns -->
		<div class="row">
			<div class="column" onclick="openTab('b1');" style="background: #D3D3D3;">
				Changer le mot de passe</div>
			<div class="column" onclick="openTab('b2');" style="background:LightSlateGrey ;">
				Changer la question / réponse secrète</div>
		</div>
	
		<!-- Full-width columns: (hidden by default) -->
		<div id="b1" class="containerTab"
			style="display: none; background: #D3D3D3">
			<span onclick="this.parentElement.style.display='none'"
				class="closebtn">&times;</span>
					
			<p>Veuillez saisir votre nouveau mot de passe</p>
			
			<form method="get" action="ChangerMdp">
				<div class="row">
					<div class="col-15">
						<label for="Actuel"><b>Actuel</b></label>
					</div>
					<div class="col-85">
						<input type="password" id="Actuel" name="Actuel"
							placeholder="Ancien mot de passe" required>
					</div>
				</div>
				<div class="row">
					<div class="col-15">
						<label for="Nouveau"><b>Nouveau</b></label>
					</div>
					<div class="col-85">
						<input type="password" placeholder="Nouveau mot de passe"
							name="Nouveau" id="Nouveau" required>
					</div>
				</div>
				<div class="row">
					<div class="col-15">
						<label for="Confirmer"><b>Confirmer</b></label>
					</div>
					<div class="col-85">
						<input type="password" id="Confirmer" name="Confirmer"
							placeholder="Confirmer le nouveau mot de passe"
							onblur="validatePassword()" required>
					</div>
				</div>
				 <div class="row">
					<input type="submit" value="Valider">
				</div>
			</form>
		</div>
	
		<div id="b2" class="containerTab"
			style="display: none; background: LightSlateGrey ">
			<span onclick="this.parentElement.style.display='none'"
				class="closebtn">&times;</span>
			<p>Veuillez saisir votre nouvelle question secrète et sa réponse
				secrète associée</p>
			<form method="get" action="changerQuRe">
				<div class="row">
					<div class="col-25">
						<label for="Actuel"><b>Mot de passe actuel</b></label>
					</div>
					<div class="col-75">
						<input type="password" id="Actuel" name="Actuel"
							placeholder="Mot de passe actuel" required>
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="Question"><b>Nouvelle question secrète</b></label>
					</div>
					<div class="col-75">
						<input type="text" id="Question" name="Question"
							placeholder="Nouvelle question secrète" required>
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="Reponse"><b>Nouvelle réponse secrète</b></label>
					</div>
					<div class="col-75">
						<input type="text" id="Reponse" name="Reponse"
							placeholder="Nouvelle réponse secrète" required>
					</div>
				</div>
				<div class="row">
					<input type="submit" value="Valider">
				</div>
			</form>
		</div>
	
	</div>
	
		<script>
			function validatePassword() {
				var password = document.getElementById("Nouveau"), confirm_password = document
						.getElementById("Confirmer");
	
				if (password.value != confirm_password.value) {
					confirm_password
							.setCustomValidity("Les mots de passe ne sont pas identiques");
	
				} else {
					confirm_password.setCustomValidity('');
	
				}
			}
	
			password.onchange = validatePassword;
			confirm_password.onkeyup = validatePassword;
		</script>
		<script>
			function openTab(tabName) {
				var i, x;
				x = document.getElementsByClassName("containerTab");
				for (i = 0; i < x.length; i++) {
					x[i].style.display = "none";
				}
				document.getElementById(tabName).style.display = "block";
			}
		</script>
	
	
	
<script>
function change() {

   if( document.getElementById("GoogleAuthentificatorActivated").checked== false)
   
document.getElementById("GoogleAuthentificatorActivated").checked=true;
else
document.getElementById("GoogleAuthentificatorActivated").checked=false;

    }
</script>   
	</body>
</html>
