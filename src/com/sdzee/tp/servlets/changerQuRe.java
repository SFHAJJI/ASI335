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
		
		try {
		String Actuel = request.getParameter( "Actuel" );
		String Question = request.getParameter( "Question" );
		String Reponse = request.getParameter( "Reponse" );
		  HttpSession session = request.getSession(true); 
		  String id = (String) session.getAttribute("id");
		
		DirContext contexte = LdapAuthentification.user_connect(id, Actuel);
		LdapAuthentification.edit_user(contexte,id,"initials",Reponse ) ;
		LdapAuthentification.edit_user(contexte,id,"carLicense",Question ) ;
		
		 Utilisateur utilisateur= new Utilisateur ();
		utilisateur = LdapAuthentification.get_attributes(id,contexte);
		
		request.setAttribute( "utilisateur", utilisateur );
		String message="Question et réponse modifiées";
		request.setAttribute( "message", message);
		this.getServletContext().getRequestDispatcher( "/informationUser.jsp" ).forward( request, response );
		
		}catch(java.lang.NullPointerException e){
			
			this.getServletContext().getRequestDispatcher( "/authentification.jsp" ).forward( request, response );
		}
			
		
	}

	

}
