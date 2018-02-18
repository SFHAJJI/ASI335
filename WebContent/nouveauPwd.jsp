<%@page import="com.sdzee.tp.beans.Utilisateur"%>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Questionnaire</title>
       <link type="text/css" rel="stylesheet" href="inc/InformationUser.css" />
       <link rel="stylesheet" type="text/css" href="inc/acceuil.css">
    </head>
    <body>
        <%-- Affichage de la chaîne "message" transmise par la servlet --%>
        <p class="info">${ message }</p>
        <div>
            <form method="get" action="nouveauPwd">
        		<fieldset>
        
        			<legend>Veuillez saisir votre nouveau mot de passe </legend>
        
        			<label for="newpwd">Nouveau <span class="requis">*</span></label>
        			<div class="col-80">
                    <input type="password" id="newpwd" name="newpwd" value="" size="20" maxlength="20"  required/>
                    <br />
                    <label for="mpdduplicate">Confirmer <span class="requis">*</span></label>
                    <input type="password" id="mpdduplicate" name="mpdduplicate" value="" size="20" maxlength="20"  onblur="validatePassword()" required/>
              		 <br />
              		 </div>
                    <input type="hidden" name="id" value="${id}" />
       		    </fieldset>
       		   
        		
       		    <input type="submit" value="Valider"  />
       		</form>
       		
         </div>
         
    </body>
    
    <script>
function validatePassword(){
var password = document.getElementById("mpdduplicate")
, confirm_password = document.getElementById("newpwd");


if(password.value != confirm_password.value) {
  confirm_password.setCustomValidity("Les mots de passe ne sont pas identiques");

} else {
  confirm_password.setCustomValidity('');
  
}
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;
</script>
</html>