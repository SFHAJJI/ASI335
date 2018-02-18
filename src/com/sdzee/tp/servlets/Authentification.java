package com.sdzee.tp.servlets;

import java.io.IOException;
import java.net.URLEncoder;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.aerogear.security.otp.Totp;

import com.sdzee.tp.beans.Utilisateur;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;

import ldapCodes.LdapAuthentification;


public class Authentification extends HttpServlet {
	
	

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /*
         * Récupération des données saisies, envoyées en tant que paramètres de
         * la requête GET générée à la validation du formulaire d'authentification
         */
    	String id = request.getParameter( "identifiant" );
        String motDePasse = request.getParameter( "pwd" );
      
    	
    	System.out.println("id: "+id+" mdp: "+motDePasse);

       
        
        
    	
        String message;
    
            
        
        try{
        	
     
        	DirContext contexte = LdapAuthentification.user_connect(id, motDePasse);
            HttpSession session = request.getSession(true); 
        	session.setAttribute("contexte", contexte); 
        	session.setAttribute("id", id);
        	
            	//DirContext contexte=LdapAuthentification.sudo_connect();
            	Utilisateur utilisateur= new Utilisateur ();
    		utilisateur = LdapAuthentification.get_attributes(id,contexte);
    		session.setAttribute("utilisateur", utilisateur);
		
		

        utilisateur.setGoogleAuth(false);
        
        	if(utilisateur.getGoogleAuth()) {
        		message = "Veuillez saisir le code de sécurité";
        		request.setAttribute( "message", message );
        		request.setAttribute( "utilisateur", utilisateur );
        		this.getServletContext().getRequestDispatcher( "/saisieQR.jsp" ).forward( request, response );
        		
        	
        	}else {
        	   	
                
        		message = "authentification réussite";
        		
        		request.setAttribute( "message", message );
        		request.setAttribute( "utilisateur", utilisateur );
        	
        		this.getServletContext().getRequestDispatcher( "/informationUser.jsp" ).forward( request, response );
        	}
        
    
    }catch(java.lang.NullPointerException e) {

    	message = "authentification échouée";
    	/* Ajout du message à l'objet requête */
        request.setAttribute( "message", message );
    	this.getServletContext().getRequestDispatcher( "/authentification.jsp" ).forward( request, response );
    	
	}
    
		
 

    }
}


