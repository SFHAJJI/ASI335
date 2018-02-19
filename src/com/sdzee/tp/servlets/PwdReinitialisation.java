package com.sdzee.tp.servlets;

import java.io.IOException;

import javax.naming.directory.DirContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Utilisateur;

import ldapCodes.LdapAuthentification;

public class PwdReinitialisation extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Récupération de l'identifiant
		 */
		String id = request.getParameter("identifiant");

		String message;

		DirContext contexte = LdapAuthentification.sudo_connect();
		Utilisateur utilisateur = new Utilisateur();
		utilisateur = LdapAuthentification.get_attributes(id, contexte);
		if (utilisateur == null) {
			message = "Utilisateur non trouvé !";
			request.setAttribute("message", message);
			this.getServletContext().getRequestDispatcher("/pwdReinitialisation.jsp").forward(request, response);
		} else {
			message = "Veuillez répondre à votre question secrète";
			request.setAttribute("message", message);
			request.setAttribute("utilisateur", utilisateur);
			this.getServletContext().getRequestDispatcher("/reponseSecrete.jsp").forward(request, response);

		}

	}

}
