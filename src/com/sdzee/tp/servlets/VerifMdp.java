package com.sdzee.tp.servlets;

import java.io.IOException;

import javax.naming.directory.DirContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.tp.beans.Utilisateur;

import ldapCodes.LdapAuthentification;

import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
/**
 * Servlet implementation class VerifMdp
 */
@WebServlet("/VerifMdp")
public class VerifMdp extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = request.getParameter( "page" );
		  HttpSession session = request.getSession(true); 
		  String id = (String) session.getAttribute("id");
		  String Actuel = request.getParameter( "Actuel" );
		//  DirContext contexte = LdapAuthentification.user_connect(id, Actuel);
		if(page.equals("0")) {
		
		String Nouveau = request.getParameter( "Nouveau" );
		
		String message;
		
		  
		 // session en cours 
		Utilisateur utilisateur=(Utilisateur)  session.getAttribute("Utilisateur");
		//try {
			
			
    	//	utilisateur = LdapAuthentification.get_attributes(id,contexte);
    		
		LdapShaPasswordEncoder isValid= new LdapShaPasswordEncoder() ; 
	      
	      if( isValid.isPasswordValid(utilisateur.getPwd(), Nouveau, null)) {
    		request.setAttribute( "Nouveau",Nouveau);
    		this.getServletContext().getRequestDispatcher( "/changerMdp" ).forward( request, response );
        
		}else {
			message = "Votre mot de passe est incorrect.";
	       
	            request.setAttribute( "message", message );
	           request.setAttribute( "utilisateur", utilisateur );
	        	this.getServletContext().getRequestDispatcher( "/informationUser.jsp" ).forward( request, response );
		}
		
		/*if(page.equals("1"))  {
		
			String Question = request.getParameter( "Question" );
			String Reponse = request.getParameter( "Reponse" );
			String message;
			
			  
			
			Utilisateur utilisateur= new Utilisateur ();
			try {
				
				
	    		utilisateur = LdapAuthentification.get_attributes(id,contexte);
	    		request.setAttribute( "Actuel", Actuel );
	    		request.setAttribute( "Question",Question);
	    		request.setAttribute( "Reponse",Reponse);
	    		
	    		this.getServletContext().getRequestDispatcher( "/changerQuRe" ).forward( request, response );
	        	
			}catch(java.lang.NullPointerException e) {
				
	        	message = "Votre mot de passejjjjjjjjjjjjjjjjjjjjjjjjj est incorrect.";
	        	contexte = (DirContext)session.getAttribute("contexte"); 
	        	utilisateur= LdapAuthentification.get_attributes(id,contexte);
	        	/* Ajout du message à l'objet requête */
	      /*
	            request.setAttribute( "message", message );
	            request.setAttribute( "utilisateur", utilisateur );
	        	this.getServletContext().getRequestDispatcher( "/informationUser.jsp" ).forward( request, response );
	        	
			}
		*/
			
		}
		
	
	}

	
}
