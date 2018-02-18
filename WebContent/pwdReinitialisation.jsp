<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Réinitialisation du mot de passe</title>
        
       <link type="text/css" rel="stylesheet" href="inc/InformationUser.css" />
    </head>
    <body>
    <div class="row">
    	<%-- Affichage de la chaîne "message" transmise par la servlet --%>
        <center><p class="info">${ message }</p></center>
        <div>
            <form method="get" action="pwdReinitialisation">
                <fieldset>
                    <legend>Saisir votre identifiant</legend>
     				<div class="col-15">
                    <label for="identifiant">identifiant <span class="requis">*</span></label>
                    </div>
                     <div class="col-80">
                    <input type="text" id="identifiant" name="identifiant" value="" size="20" maxlength="20" required/>
                    </div>
                    <br />
    
                </fieldset>
                <input type="submit" value="Valider"  />
            </form>
        </div>
    </div>
    </body>
</html>