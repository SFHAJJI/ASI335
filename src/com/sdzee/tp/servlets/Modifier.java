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
	 public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	        /*
	         * Récupération de l'identifiant
	         */
	    	String nom = request.getParameter( "nom" );
	    	String prenom = request.getParameter( "prenom" );
	    	String email = request.getParameter( "email" );
	    	String id = request.getParameter( "id" );
	        String message;
	        HttpSession session = request.getSession(); // session en cours 
	        DirContext contexte = (DirContext)session.getAttribute("contexte"); 
	        
	        if ( nom.trim().isEmpty() || prenom.trim().isEmpty() || email.trim().isEmpty()) {
	            message = "Veuillez ne pas laissez des champs vides ";
	            
	        //    DirContext contexte = LdapAuthentification.sudo_connect();
		        Utilisateur utilisateur= new Utilisateur ();
				utilisateur = LdapAuthentification.get_attributes(id,contexte);
				request.setAttribute("utilisateur",utilisateur);
	            request.setAttribute( "message", message );
	            this.getServletContext().getRequestDispatcher( "/infomationUser.jsp" ).forward( request, response );
	          
	        } else {
	        //	DirContext contexte = LdapAuthentification.sudo_connect();
	        	message="mise à jour réussite";
	        	LdapAuthentification.edit_user(contexte,id,"cn",prenom ) ;
	        	LdapAuthentification.edit_user(contexte,id,"sn",nom ) ;
	        	LdapAuthentification.edit_user(contexte,id,"mail",email ) ;
				
				
		        Utilisateur utilisateur= new Utilisateur ();
				utilisateur = LdapAuthentification.get_attributes(id,contexte);
	           
	            request.setAttribute( "message", message );
	            request.setAttribute( "utilisateur", utilisateur );
	        	this.getServletContext().getRequestDispatcher( "/informationUser.jsp" ).forward( request, response );
	        		
			}
	        	
	        
	}
	 
}
