<%@page import="com.sdzee.tp.beans.Utilisateur"%>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Questionnaire</title>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
    </head>
    <body>
        <%-- Affichage de la chaîne "message" transmise par la servlet --%>
        <p class="info">${ message }</p>
        <div>
            <form method="get" action="nouveauPwd">
        		<fieldset>
        
        			<legend>Veuillez saisir votre nouveau mot de passe </legend>
        
        			<label for="newpwd">mpd <span class="requis">*</span></label>
                    <input type="text" id="newpwd" name="newpwd" value="" size="20" maxlength="20" />
                    <br />
                    <label for="mpdduplicate">mpdduplicate <span class="requis">*</span></label>
                    <input type="text" id="mpdduplicate" name="mpdduplicate" value="" size="20" maxlength="20" />
              		 <br />
                    <input type="hidden" name="id" value="${id}" />
       		    </fieldset>
       		   
        		
       		    <input type="submit" value="Valider"  />
       		</form>
       		
         </div>
         
    </body>
</html>