<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Réinitialisation du mot de passe</title>
<link type="text/css" rel="stylesheet" href="inc/InformationUser.css" />
</head>

<body>
	<form method="get" action="Logout">
		<div class="col-90">
			<input type="submit" value="Page d'accueil" />
		</div>
	</form>
	<div class="container">
		<div class="row">
			<%-- Affichage de la chaîne "message" transmise par la servlet --%>
			<center>
				<p class="info"><b>${ message }</b></p>
			</center>
			<div>
				<form method="get" action="pwdReinitialisation">
					
						<h2>Saisir votre identifiant</h2>
						
						
						<div class="col-10">
							<label for="identifiant"><b>Identifiant </b><span class="requis">*</span></label>
						</div>
						<div class="col-75">
							<input type="text" id="identifiant" name="identifiant" value=""
								size="20" maxlength="20" required />
						</div>
						<div class="col-10">
							<input type="submit" value="Valider" />
						</div>
	
					
					
				</form>
			</div>
		</div>
	</div>
</body>
</html>