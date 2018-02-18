<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link type="text/css" rel="stylesheet" href="inc/InformationUser.css" />
<link rel="stylesheet" type="text/css" href="inc/acceuil.css">
<style>
* {
    box-sizing: border-box;
}

body {
    margin: 0;
    font-family: Arial;
}

/* The grid: Three equal columns that floats next to each other */
.column {
    float: left;
    width: 50%;
    padding: 50px;
    text-align: center;
    font-size: 25px;
    cursor: pointer;
    color: white;
}

.containerTab {
    padding: 20px;
    color: white;
}

/* Clear floats after the columns */
.row:after {
    content: "";
    display: table;
    clear: both;
}

/* Closable button inside the container tab */
.closebtn {
    float: right;
    color: white;
    font-size: 35px;
    cursor: pointer;
}

</style>
</head>
<body>
<form method="get" action="Logout">
 <div class="col-90">
    <input type="submit" value="Déconnexion"  />
     </div>
</form>
<center><p class="info">${ message }</p></center>

<div style="text-align:center">
  <h2>Changer les paramètres de sécurité</h2>

</div>

<!-- Three columns -->
<div class="row">
  <div class="column" onclick="openTab('b1');" style="background:green;">
    Changer le mot de passe du compte     
  </div>
  <div class="column" onclick="openTab('b2');" style="background:blue;">
    Changer la question et sa réponse associée
  </div>
 
</div>

<!-- Full-width columns: (hidden by default) -->
<div id="b1" class="containerTab" style="display:none;background:green">
  <span onclick="this.parentElement.style.display='none'" class="closebtn">&times;</span>
  <h2>Changer le mot de passe </h2>
  <p>Veuillez saisir votre ancienne et nouvelle mot de passe
  <form method="get" action="ChangerMdp" >
  <div class="row">
      <div class="col-25">
        <label for="Actuel">Actuel</label>
      </div>
      <div class="col-75">
        <input type="password" id="Actuel" name="Actuel" placeholder="ancien mot de passe" required>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="Nouveau">Nouveau</label>
      </div>
      <div class="col-75">
        <input type="password" placeholder="Nouveau mot de passe" name="Nouveau" id="Nouveau"  required>
      </div>
    </div>
     <div class="row">
      <div class="col-25">
        <label for="Confirmer">Confirmer</label>
      </div>
      <div class="col-75">
        <input type="password" id="Confirmer" name="Confirmer" placeholder="confirmer le nouveau mot de passe" onblur="validatePassword()" required>
      </div>
    </div>
    <input type="hidden" id="page" name="page" value="0" />
    <input type="submit" value="Valider"  />
   </form>
</div>

<div id="b2" class="containerTab" style="display:none;background:blue">
  <span onclick="this.parentElement.style.display='none'" class="closebtn">&times;</span>
  <h2>Changer la question et la réponse secrète</h2>
  <p>Veuillez saisir votre nouvelle question secrète et sa réponse secrète associée</p>
  <form method="get" action="changerQuRe" >
    <div class="row">
      <div class="col-25">
        <label for="Actuel">Actuel</label>
      </div>
      <div class="col-75">
        <input type="password" id="Actuel" name="Actuel" placeholder="mot de passe" required>
      </div>
    </div>
   <div class="row">
      <div class="col-25">
        <label for="Question">Question secrète</label>
      </div>
      <div class="col-75">
        <input type="text" id="Question" name="Question" placeholder="nouvelle question secrète" required>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="Reponse">Réponse secrète</label>
      </div>
      <div class="col-75">
        <input type="text" id="Reponse" name="Reponse" placeholder="nouvelle réponse secrète" 	 required>
      </div>
    </div>
	<input type="hidden" id="page" name="page" value="1" />
    <input type="submit" value="Valider"  />
   </form>
</div>



<script>
function validatePassword(){
var password = document.getElementById("Nouveau")
, confirm_password = document.getElementById("Confirmer");


if(password.value != confirm_password.value) {
  confirm_password.setCustomValidity("Les mots de passe ne sont pas identiques");

} else {
  confirm_password.setCustomValidity('');
  
}
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;
</script>
<script>
function openTab(tabName) {
  var i, x;
  x = document.getElementsByClassName("containerTab");
  for (i = 0; i < x.length; i++) {
     x[i].style.display = "none";
  }
  document.getElementById(tabName).style.display = "block";
}
</script>

</body>
</html> 
