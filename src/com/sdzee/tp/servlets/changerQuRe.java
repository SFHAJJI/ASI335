package com.sdzee.tp.servlets;

import java.io.IOException;

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

/**
 * Servlet implementation class changerQuRe
 */
@WebServlet("/changerQuRe")
public class changerQuRe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changerQuRe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String Actuel = request.getParameter( "Actuel" );
		String Question = request.getParameter( "Question" );
		String Reponse = request.getParameter( "Reponse" );
		
		HttpSession session = request.getSession(); 
		String id = (String) session.getAttribute("id");
		
		String message;
				

				Utilisateur utilisateur=(Utilisateur)session.getAttribute("utilisateur");

					System.out.println("le password est"+ utilisateur.getPwd());
					
				     DirContext contexte = (DirContext)session.getAttribute("contexte");
		    		
				LdapShaPasswordEncoder isValid= new LdapShaPasswordEncoder() ; 
		
				  if( isValid.isPasswordValid(utilisateur.getPwd(), Actuel, null)) {
			    		 try {    
		

		LdapAuthentification.edit_user(contexte,id,"initials",Reponse ) ;
		LdapAuthentification.edit_user(contexte,id,"carLicense",Question ) ;
		
		
		utilisateur = LdapAuthentification.get_attributes(id,contexte);
		request.setAttribute( "utilisateur", utilisateur );
		request.setAttribute( "utilisateur", utilisateur );
		message="Question et réponse modifiées";
		request.setAttribute( "message", message);
		this.getServletContext().getRequestDispatcher( "/informationUser.jsp" ).forward( request, response );
		
		}catch(java.lang.NullPointerException e){
			
			this.getServletContext().getRequestDispatcher( "/authentification.jsp" ).forward( request, response );
		}
			
		
	}

	
	else {
		
			message = "Votre mot de passe est incorrect.";
	       
	            request.setAttribute( "message", message );
	           request.setAttribute( "utilisateur", utilisateur );
	        	this.getServletContext().getRequestDispatcher( "/informationUser.jsp" ).forward( request, response );
		}
	      
	}

}
