package com.sdzee.tp.servlets;

import java.io.IOException;
import javax.naming.directory.DirContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.sdzee.tp.beans.Utilisateur;
import ldapCodes.LdapAuthentification;

public class Modifier extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Récupération des informations envoyées apr la requete : nouvelles adresse mail, id, nom et prénom
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String id = request.getParameter("id");
		String message;
		
		// Récupérer le contexte de l'utilisateur
		HttpSession session = request.getSession(); // session en cours
		DirContext contexte = (DirContext) session.getAttribute("contexte");
		message = "Mise à jour des informations réussie";
		
		//Changer les informations dans le serveur LDAP
		LdapAuthentification.edit_user(contexte, id, "cn", prenom);
		LdapAuthentification.edit_user(contexte, id, "sn", nom);
		LdapAuthentification.edit_user(contexte, id, "mail", email);
		
		// Changer les information dans la variable "utilisateur"
		Utilisateur utilisateur = new Utilisateur();
		utilisateur = LdapAuthentification.get_attributes(id, contexte);
		
		// Introduire le nouveau utilisateur dans la session en cours
		session.setAttribute("utilisateur", utilisateur);
		request.setAttribute("message", message);
		request.setAttribute("utilisateur", utilisateur);
		
		// Revenir à la page des informations
		this.getServletContext().getRequestDispatcher("/informationUser.jsp").forward(request, response);	
		
	}
}
