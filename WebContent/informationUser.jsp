<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Affichage d'un utilisateur</title>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
        
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link type="text/css" rel="stylesheet" href="inc/InformationUsertop.css" />
         <link type="text/css" rel="stylesheet" href="inc/InformationUser.css" />

</head>

<body>
<form method="get" action="Logout">
 <div class="col-90">
    <input type="submit" value="Déconnexion"  />
     </div>
</form>


<div class="navbar">
  <a>Acceuil</a>
  <a href="securite.jsp">Sécurité</a>
  
</div>


<div class="container">
  <form method="get" action="modifier">
  <br>
  <center> <p class="info">${ message }</p></center>
 
    <div class="row">
      <div class="col-25">
        <label for="fname">Nom</label>
      </div>
      <div class="col-75">
        <input type="text" id="nom" name="nom" value= ${ utilisateur.nom } required>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="Prenom">Prenom</label>
      </div>
      <div class="col-75">
        <input type="text" id="prenom" name="prenom" value= ${ utilisateur.prenom }  required>
      </div>
    </div>
     <div class="row">
      <div class="col-25">
        <label for="lname">E-mail</label>
      </div>
      <div class="col-75">
        <input type="text" id="email" name="email" value= ${ utilisateur.email }  required>
      </div>
    </div>
    <input type="hidden" name="id" value="${utilisateur.identifiant}" />
    <div class="row">
      <input type="submit" value="Submit">
    </div>
  </form>
  
  
</div>

    </body>
</html>