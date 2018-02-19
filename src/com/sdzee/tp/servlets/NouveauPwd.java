package com.sdzee.tp.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;

import ldapCodes.LdapAuthentification;


public class NouveauPwd extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * R�cup�ration des donn�es saisies, envoy�es en tant que param�tres de la
		 * requ�te GET g�n�r�e � la validation du formulaire d'authentification
		 */

		String id = request.getParameter("id");
		String newpwd = request.getParameter("newpwd");
		String mpdduplicate = request.getParameter("mpdduplicate");
		String message;

		
		 //essayer de modifier le mot de passe
		String msg= LdapAuthentification.edit_user_password(LdapAuthentification.sudo_connect(), id, newpwd);
	
		if (msg=="") { 
			message = "Mise � jour du mot  de passe r�ussie";
			request.setAttribute("message", message);
			this.getServletContext().getRequestDispatcher("/authentification.jsp").forward(request, response);

		} else {
			request.setAttribute("id", id);
			message = "Mot de passe non modifi�:  ";
			request.setAttribute("id", id);
			request.setAttribute("message", message+msg+"Veuillez r�essayer");
			this.getServletContext().getRequestDispatcher("/nouveauPwd.jsp").forward(request, response);

		}

	}

}
