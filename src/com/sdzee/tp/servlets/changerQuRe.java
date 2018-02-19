package com.sdzee.tp.servlets;

import java.io.IOException;
import javax.naming.directory.DirContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import com.sdzee.tp.beans.Utilisateur;
import ldapCodes.LdapAuthentification;


@WebServlet("/changerQuRe")
public class changerQuRe extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public changerQuRe() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// R�cup�rer les informations envoy�es par la requete : mot de passe actuelle, nouvelles question et r�ponse secr�tes
		String Actuel = request.getParameter("Actuel");
		String Question = request.getParameter("Question");
		String Reponse = request.getParameter("Reponse");
		//R�cup�rer les informations stock�es dans la session
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		DirContext contexte = (DirContext) session.getAttribute("contexte");
		
		String message;

		// V�rifier si le mot de passe actuel entr� par l'utilisateur est correct
		LdapShaPasswordEncoder isValid = new LdapShaPasswordEncoder();

		if (isValid.isPasswordValid(utilisateur.getPwd(), Actuel, null)) { //si le mot de passe est correct
			try {
				// Changer la question et r�ponse de l'utilisateur dans LDAP
				LdapAuthentification.edit_user(contexte, id, "initials", Reponse);
				LdapAuthentification.edit_user(contexte, id, "carLicense", Question);
				
				// Mise � jour de la variable utilisateur
				utilisateur = LdapAuthentification.get_attributes(id, contexte);
				
				// Transformer les informations dans la requetes
				request.setAttribute("utilisateur", utilisateur);
				message = "Question et r�ponse modifi�es";
				request.setAttribute("message", message);
				this.getServletContext().getRequestDispatcher("/informationUser.jsp").forward(request, response);

			} catch (java.lang.NullPointerException e) {
				this.getServletContext().getRequestDispatcher("/authentification.jsp").forward(request, response);
			}
		}

		else {
			message = "Votre mot de passe est incorrect. Veuillez r�essayer";
			request.setAttribute("message", message);
			this.getServletContext().getRequestDispatcher("/securite.jsp").forward(request, response);
		}

	}

}
