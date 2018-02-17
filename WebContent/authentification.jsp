<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="inc/acceuil.css">
</head>
<body>

<p class="info">${ message }</p>

<div>
  
  <form method="post" class="modal-content" action="authentification">
    <div class="imgcontainer">
      <img src="image/img_avatar.png" alt="Avatar" class="avatar">
    </div>

    <div class="container">
      <label for="identifiant"><b>Identifiant</b></label>
      <input type="text" placeholder="Entrer votre identifiant" name="identifiant" id="identifiant" required>

      <label for="pwd"><b>Mot de passe</b></label>
      <input type="password" placeholder="Entrer motde passe" name="pwd" id="pwd" required>

      <button type="submit">Connecter</button>
      <!--<label>
        <input type="checkbox" checked="checked" name="remember"> Remember me
      </label>-->
    </div>

    <div class="container" style="background-color:#f1f1f1">
      <span class="psw"> <a href="pwdReinitialisation.jsp">mot de passe oublié ?</a></span>
    </div>
  </form>
</div>

</body>
</html>
