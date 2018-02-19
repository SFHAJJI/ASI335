
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Saisie d'un Code de sécurité</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
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
				<form method="get" action="googleAuth">
					
						<h2>Saisir le code de sécurité (Google Authentificator)</h2>
						
						
							<div class="col-10">
								<label for="code"><b>Code</b></label>
							</div>
							<div class="col-75">
								
								<input type="text" id="code" name="code"  required onkeypress="return isNumberKey(event)"/>
							</div>
				
							<div>
								<input type="hidden" id="id" name="id"
								value="${utilisateur.identifiant}"/>
							</div>
							<div class="col-10">
							<input type="submit" value="Valider" />
							</div>
		
					
				</form>
			</div>
		</div>
	</div>
			
</body>

<script>

function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}

</script>
</html>