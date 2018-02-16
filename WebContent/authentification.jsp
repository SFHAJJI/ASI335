<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="inc/acceuil.css">
</head>
<body>
<p class="info">${ message }</p>

<div>
  
  <form method="post" class="modal-content animate" action="authentification">
    <div class="imgcontainer">
      <img src="image/img_avatar.png" alt="Avatar" class="avatar">
    </div>

    <div class="container">
      <label for="identifiant"><b>Identifiant</b></label>
      <input type="text" placeholder="Enter Username" name="identifiant" id="identifiant" required>

      <label for="pwd"><b>Mot de passe</b></label>
      <input type="text" placeholder="Enter Password" name="pwd" id="pwd" required>
        
      <button type="submit">Connecter</button>
      <label>
        <input type="checkbox" checked="checked" name="remember"> Remember me
      </label>
    </div>

    <div class="container" style="background-color:#f1f1f1">
      <span class="psw">Forgot <a href="pwdReinitialisation.jsp">password?</a></span>
    </div>
  </form>
</div>


</body>
</html>
