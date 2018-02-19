package com.sdzee.tp.servlets;

import java.io.IOException;

import javax.naming.directory.DirContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Utilisateur;

import ldapCodes.LdapAuthentification;

public class VerifReponse extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recup�ration des param�tres suivantes : r�ponse saisie, r�ponse enregistr� et l'identifiant
		String reponseSaisie = request.getParameter("reponseSaisie");
		String reponseI = request.getParameter("reponseI");
		String id = request.getParameter("id");

		boolean test = reponseI.equals(reponseSaisie);
		if (test) {
			//r�ponse saisie correcte et envoy� l'identifiant au formulaire de r�initialisation du mdp
			String message = "Veuillez saisir votre nouveau mot de passe";
			request.setAttribute("message", message);
			request.setAttribute("id", id);
			this.getServletContext().getRequestDispatcher("/nouveauPwd.jsp").forward(request, response);

		} else {
			//r�ponse saisie incorrecte
			DirContext contexte = LdapAuthentification.sudo_connect();
			
			//reconstruction del'utilisateur et l'envoy� au formulaire de saisie r�ponse secr�te
			Utilisateur utilisateur = new Utilisateur();
			utilisateur = LdapAuthentification.get_attributes(id, contexte);
			String message = "Mauvaise r�ponse, veuillez r�essayer";
			request.setAttribute("message", message);
			request.setAttribute("utilisateur", utilisateur);
			this.getServletContext().getRequestDispatcher("/reponseSecrete.jsp").forward(request, response);
			
		}
	}

}
