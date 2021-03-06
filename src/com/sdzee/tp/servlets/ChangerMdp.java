package com.sdzee.tp.servlets;

import java.io.IOException;

import javax.naming.NamingException;
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

@WebServlet("/ChangerMdp")
public class ChangerMdp extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// R�cup�rer le nouveau et l'ancien mots de passe envoy�s par la requete
		String Actuel = request.getParameter("Actuel");
		String nouveau = request.getParameter("Nouveau");
		//R�cup�rer les informations stock�es dans la session
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		DirContext contexte = (DirContext) session.getAttribute("contexte");
		
		String message;
		// V�rifier si le mot de passe actuel entr� par l'utilisateur est correct
		LdapShaPasswordEncoder isValid = new LdapShaPasswordEncoder();

		if (isValid.isPasswordValid(utilisateur.getPwd(), Actuel, null)) { 
			
		//si le mot de passe est valide
			String msg= LdapAuthentification.edit_user_password(contexte, id, nouveau);
				if (msg=="") { 
					// destruction de l'ancienne session 
					message = "Mise � jour r�ussite du mot  de passe ";
					try {
						LdapAuthentification.disconnect(contexte);
					} catch (NamingException e) {
						e.printStackTrace();
					}
					
					if (session != null) {
						session.invalidate();
					}
					//Cr�er un nouveau contexte et une nouvelle session
					DirContext Nouveau_contexte = LdapAuthentification.user_connect(id, nouveau);
					utilisateur = LdapAuthentification.get_attributes(id, Nouveau_contexte);
					session = request.getSession(true);
					session.setAttribute("contexte", Nouveau_contexte);
					session.setAttribute("id", id);
					session.setAttribute("utilisateur", utilisateur);
					
					// transformer les informations dans la requetes
					request.setAttribute("message", message);
					request.setAttribute("utilisateur", utilisateur);
					this.getServletContext().getRequestDispatcher("/informationUser.jsp").forward(request, response);

				} else {
				
					request.setAttribute("message", msg+ " Veuillez r�essayer !");
					this.getServletContext().getRequestDispatcher("/securite.jsp").forward(request, response);

				}


		} else {
			message = "Votre mot de passe est non valide. Veuillez r�essayer";
			request.setAttribute("message", message);
			this.getServletContext().getRequestDispatcher("/securite.jsp").forward(request, response);
		}

	}

}
