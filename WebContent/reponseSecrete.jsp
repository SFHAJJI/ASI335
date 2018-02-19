<%@page import="com.sdzee.tp.beans.Utilisateur"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Questionnaire</title>
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
			<form method="get" action="verifReponse">

				<h2>${ message }</h2>
				<p>
					<b>Question: ${ utilisateur.question }</b>
				</p>
				<div class="col-10">
					<label for="reponseSaisie"><b>Réponse</b> <span
						class="requis">*</span></label>
				</div>
				<div class="col-75">
					<input type="text" id="reponseSaisie" name="reponseSaisie" value=""
						size="20" maxlength="20" required />
				</div>
				<input type="hidden" name="reponseI" value="${utilisateur.reponse}" />
				<input type="hidden" name="id" value="${utilisateur.identifiant}" />
				<div class="col-10">
					<input type="submit" value="Valider" />
				</div>
				
			</form>

		</div>
	</div>

</body>
</html>