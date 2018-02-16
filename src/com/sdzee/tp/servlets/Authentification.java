package com.sdzee.tp.servlets;

import java.io.IOException;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.tp.beans.Utilisateur;
import ldapCodes.LdapAuthentification;


public class Authentification extends HttpServlet {
	
	

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /*
         * Récupération des données saisies, envoyées en tant que paramètres de
         * la requête GET générée à la validation du formulaire d'authentification
         */
    	String id = request.getParameter( "identifiant" );
        String motDePasse = request.getParameter( "pwd" );
      
        String message;
        if ( id.trim().isEmpty() || motDePasse.trim().isEmpty() ) {
            message = "Vous n'avez pas rempli tous les champs obligatoires. ";
            this.getServletContext().getRequestDispatcher( "/authentification.jsp" ).forward( request, response );
           
        } else {
            
        
        try{
        	
        	
        DirContext contexte = LdapAuthentification.user_connect(id, motDePasse);
        HttpSession session = request.getSession(true); 
    	session.setAttribute("contexte", contexte); 
        	//DirContext contexte=LdapAuthentification.sudo_connect();
        	Utilisateur utilisateur= new Utilisateur ();
		utilisateur = LdapAuthentification.get_attributes(id,contexte);
		message = "authentification réussite";
		
		
    	request.setAttribute( "message", message );
    	request.setAttribute( "utilisateur", utilisateur );
    	this.getServletContext().getRequestDispatcher( "/informationUser.jsp" ).forward( request, response );
		
        }catch(java.lang.NullPointerException e) {

        	message = "authentification échouée";
        	/* Ajout du message à l'objet requête */
            request.setAttribute( "message", message );
        	this.getServletContext().getRequestDispatcher( "/authentification.jsp" ).forward( request, response );
        	
		}
        }
		
  // Emna
       // GoogleAuthenticator gAuth = new GoogleAuthenticator();
        //final GoogleAuthenticatorKey key = gAuth.createCredentials();

    }
}


