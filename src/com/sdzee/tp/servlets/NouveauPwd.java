package com.sdzee.tp.servlets;
import java.io.IOException;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Utilisateur;
import ldapCodes.LdapAuthentification;
public class NouveauPwd extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /*
         * Récupération des données saisies, envoyées en tant que paramètres de
         * la requête GET générée à la validation du formulaire d'authentification
         */
    	String id = request.getParameter( "id" );
        String newpwd = request.getParameter( "newpwd" );
        String mpdduplicate = request.getParameter( "mpdduplicate" );
        String message;
        if ( mpdduplicate.trim().isEmpty() || newpwd.trim().isEmpty() ) {
            message = "Vous n'avez pas rempli tous les champs obligatoires. ";
            request.setAttribute( "message", message );
            request.setAttribute( "id", id );
            this.getServletContext().getRequestDispatcher( "/nouveauPwd.jsp" ).forward( request, response );
           
        } else {
        	
        	if(!mpdduplicate.equals(mpdduplicate)){
        		 message = "Vous n'avez pas rempli tous les champs obligatoires. ";
        		 request.setAttribute( "message", message );
        		 request.setAttribute( "id", id );
        		 this.getServletContext().getRequestDispatcher( "/nouveauPwd.jsp" ).forward( request, response );
        	}else{
        		 message = "Mise à jour réussite du mot  de passe ";
        		 
        		 LdapAuthentification.edit_user(LdapAuthentification.sudo_connect(),id,"userPassword",newpwd ) ;
        		 request.setAttribute( "message", message );
        		 this.getServletContext().getRequestDispatcher( "/authentification.jsp" ).forward( request, response );
        	}
        	
        }

    }

}
