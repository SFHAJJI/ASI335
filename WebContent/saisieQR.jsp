
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Saisie d'un Code de sécurité</title>
    
        
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
         <link type="text/css" rel="stylesheet" href="inc/InformationUser.css" />

</head>

<form method="get" action="googleAuth">
<fieldset>
<legend>Saisir le code affiché dans votre téléphone</legend>
  <center> <p class="info">${ message }</p></center>
  <div class="row">
      <div class="col-15">
        <label for="code">code</label>
      </div>
      <div class="col-80">
        <input type="text" id="code" name="code" value="${utilisateur.secret}" required>
      </div>
     
    <div>
      <input type="hidden" id="id" name="id" value="${utilisateur.identifiant}" />
     
      </div>
       <input type="submit" value="Submit">
    </div>
</fieldset>
    </form>