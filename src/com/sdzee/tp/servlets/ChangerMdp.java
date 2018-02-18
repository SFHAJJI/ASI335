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

import com.sdzee.tp.beans.Utilisateur;

import ldapCodes.LdapAuthentification;

public class ChangerMdp extends HttpServlet {

       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		String Nouveau = request.getParameter( "Nouveau" );
		String message;
		 HttpSession session = request.getSession();
		 String id = (String) session.getAttribute("id");
		 // session en cours 
	     DirContext contexte = (DirContext)session.getAttribute("contexte"); 
	     
	 try {    
	     Utilisateur utilisateur= new Utilisateur ();
	
		 if(LdapAuthentification.edit_user_password(contexte,id,Nouveau )) {
			 	message = "Mise à jour réussite du mot  de passe ";
			 	try {
			 		LdapAuthentification.disconnect(contexte);
			 	} catch (NamingException e) {
				// TODO Auto-generated catch block
			 		e.printStackTrace();
			 	}
			
			 	if (session != null) {
			 		session.invalidate();
			 	}
			 	DirContext Nouveau_contexte = LdapAuthentification.user_connect(id, Nouveau);
			 	session = request.getSession(true); 
			 	session.setAttribute("contexte",Nouveau_contexte);
			 	session.setAttribute("id", id);
        	
			
			 	utilisateur = LdapAuthentification.get_attributes(id,Nouveau_contexte);
				
			 	request.setAttribute( "message", message );
       	  		request.setAttribute( "utilisateur", utilisateur );
       	  		this.getServletContext().getRequestDispatcher( "/informationUser.jsp" ).forward( request, response );

		}else {
			message = "ECHEC : mot de passe non modifié ";
	
			utilisateur = LdapAuthentification.get_attributes(id,contexte);
				
        	request.setAttribute( "id", id );
			request.setAttribute( "message", message );
            this.getServletContext().getRequestDispatcher( "/informationUser.jsp" ).forward( request, response );
    	
		}
	
		 
	}catch(java.lang.NullPointerException e) {

    	message = "mot de passe incorrecte";
    	/* Ajout du message à l'objet requête */
        request.setAttribute( "message", message );
      
    	this.getServletContext().getRequestDispatcher( "/securite.jsp" ).forward( request, response );
    	
	}
	
	
		}


}
