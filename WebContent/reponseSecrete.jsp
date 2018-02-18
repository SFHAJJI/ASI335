<%@page import="com.sdzee.tp.beans.Utilisateur"%>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Questionnaire</title>
        <link type="text/css" rel="stylesheet" href="inc/InformationUser.css" />
    </head>
    <body>
        <%-- Affichage de la chaîne "message" transmise par la servlet --%>
        <p class="info">${ message }</p>
        <div>
            <form method="get" action="verifReponse">
        		<fieldset>
       				<legend>Saisir la réponse à votre question</legend>
       				<div class="col-80">
        			<p>question: ${ utilisateur.question }</p>
        
        			<label for="reponseSaisie">réponse <span class="requis">*</span></label>
                    <input type="text" id="reponseSaisie" name="reponseSaisie" value="" size="20" maxlength="20" required/>
                    <br />
                    </div>
                    <input type="hidden" name="reponseI" value="${utilisateur.reponse}" />
                    <input type="hidden" name="id" value="${utilisateur.identifiant}" />
       		    </fieldset>
       		   
        		
       		    <input type="submit" value="Valider"  />
       		</form>
       		
         </div>
         
    </body>
</html>