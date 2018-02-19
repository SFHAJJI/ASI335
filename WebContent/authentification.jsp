<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="inc/acceuil.css">
	</head>
	<body>
		<p class="info"><b>${ message }</b></p>
			
		<div>
			<form method="post" class="modal-content" action="authentification">
				<div class="imgcontainer">
					<img src="image/img_avatar.png" alt="Avatar" style="width:150px;height:150px;" class="avatar">
				</div>
				<div class="container">
					<label for="identifiant"><b>Identifiant</b></label> <input
						type="text" placeholder="Saisir votre identifiant"
						name="identifiant" id="identifiant" required> <label
						for="pwd"><b>Mot de passe</b></label> <input type="password"
						placeholder="Saisir mot de passe" name="pwd" id="pwd" required>
					<button type="submit">Connecter</button>
				</div>
				<div class="container" style="background-color: #f1f1f1">
					<span class="psw"> <a href="pwdReinitialisation.jsp">Mot de passe
							oublié ?</a></span>
				</div>
			</form>
		</div>
	</body>
</html>
