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

public class Authentification extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Récupération des données saisies, envoyées en tant que paramètres de la
		 * requête POST générée à la validation du formulaire d'authentification
		 */
		String id = request.getParameter("identifiant");
		String motDePasse = request.getParameter("pwd");
		String message;
		try { 
			//créer le contexte de connexion au serveur ldap par l'utilisateur "id"
			DirContext contexte = LdapAuthentification.user_connect(id, motDePasse); 
			// Récupérer les informations de l'utilisateur et les stocker dans "utilisateur"
			Utilisateur utilisateur = new Utilisateur();
			utilisateur = LdapAuthentification.get_attributes(id, contexte);
			// Créer une nouvelle sesion et y stocker les informations: contexte, utilisateur et id
			HttpSession session = request.getSession(true);
			session.setAttribute("contexte", contexte);
			session.setAttribute("id", id);
			session.setAttribute("utilisateur", utilisateur);
			//Etape de google authentificator
			if (utilisateur.getGoogleAuth().equals("activé")) {
				/* Ajout du message à l'objet requête */
				message = "Deuxième étape de l'authentification!";
				request.setAttribute("message", message);
				/* Ajout de l'utilisateur à l'objet requête */
				request.setAttribute("utilisateur", utilisateur);
				//envoie des objets utilisateur et message à la jsp de saisie de code 
				this.getServletContext().getRequestDispatcher("/saisieQR.jsp").forward(request, response);
			} else {
				/* Ajout du message à l'objet requête */
				message = "Authentification réussie !";
				request.setAttribute("message", message);
				request.setAttribute("utilisateur", utilisateur);
				//envoie des objets message et utilisateur à la jsp pour afficher les infos générales de ce dernier
				this.getServletContext().getRequestDispatcher("/informationUser.jsp").forward(request, response);
			}
		} catch (java.lang.NullPointerException e) { 
			//si la connexion au serveur ldap apr l'utilisateur est échouée
			message = "Authentification échouée: identifiant et/ou mot de passe incorrect ! Veuillez réessayer";
			/* Ajout du message à l'objet requête */
			request.setAttribute("message", message);
			this.getServletContext().getRequestDispatcher("/authentification.jsp").forward(request, response);
		}
	}
}
