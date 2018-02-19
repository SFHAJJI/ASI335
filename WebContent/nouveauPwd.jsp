<%@page import="com.sdzee.tp.beans.Utilisateur"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Questionnaire</title>
<link type="text/css" rel="stylesheet" href="inc/InformationUser.css" />
<link rel="stylesheet" type="text/css" href="inc/acceuil.css">
</head>

<body>
	<form method="get" action="Logout">
		<div class="col-90">
			<input type="submit" value="Page d'accueil" />
		</div>
	</form>
	<div class="container">
		<div class="row">

		<div>
		<form method="get" action="nouveauPwd">
	
				<h2>${ message }</h2>


						<div class="col-15">
							<label for="newpwd"><b>Nouveau </b><span class="requis">*</span></label>
						</div>
						<div class="col-85">
							<input type="password" id="newpwd" name="newpwd" value=""
								size="20" maxlength="20" required />
						</div>
						<div class="col-15">
							<label for="mpdduplicate"><b>Confirmer </b><span class="requis">*</span></label>
						</div>
						<div class="col-85">
							<input type="password" id="mpdduplicate" name="mpdduplicate" value=""
								size="20" maxlength="20"  onblur="validatePassword()" required />
						</div>
						
						<input type="hidden" name="id" value="${id}" />
		
						<input type="submit" value="Valider" />
		</form>

	</div>

</body>

<script>
	function validatePassword() {
		var password = document.getElementById("mpdduplicate"), confirm_password = document
				.getElementById("newpwd");

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
</html>