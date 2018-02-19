package com.sdzee.tp.servlets;



import java.io.IOException;
import javax.naming.directory.DirContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.sdzee.tp.beans.Utilisateur;
import ldapCodes.LdapAuthentification;

@WebServlet("/ActivateDesactivateGoogle")
public class ActivateDesactivateGoogle extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupérer le contexte de l'utilisateur
		HttpSession session = request.getSession(); // session en cours
		DirContext contexte = (DirContext) session.getAttribute("contexte");
		Utilisateur utilisateur =(Utilisateur) session.getAttribute("utilisateur");
		String message; 
		//Changer les informations dans le serveur LDAP
		String id = (String) session.getAttribute("id");

		if (utilisateur.getGoogleAuth().equals("désactivé")) {
			LdapAuthentification.edit_user(contexte, id, "departmentNumber", "activé");
			message= "Google Authentificator est maintenant activé";
		}else {
			LdapAuthentification.edit_user(contexte, id, "departmentNumber", "désactivé");
			message= "Google Authentificator est maintenant désactivé";
		}
		
		// Changer les information dans la variable "utilisateur"

		utilisateur = LdapAuthentification.get_attributes(utilisateur.getIdentifiant(), contexte);
		request.setAttribute("message", message);

		// Introduire le nouveau utilisateur dans la session en cours
		session.setAttribute("utilisateur", utilisateur);
		// Revenir à la page des informations
		this.getServletContext().getRequestDispatcher("/googleConfig.jsp").forward(request, response);	
		
	}
}

