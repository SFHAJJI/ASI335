package com.sdzee.tp.beans;


public class Utilisateur {
	/* Propri�t�s du bean */
	private String identifiant;
    private String nom;
    private String prenom;
    private String pwd;
    private String question;
    private String reponse;
    private String email;
    private boolean googleAuth;
    
    public void setIdentifiant( String identifiant ) {
        this.identifiant = identifiant;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setPrenom( String prenom ) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPwd( String pwd ) {
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }
    
    public void setQuestion( String question ) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }
    
    public void setReponse( String reponse ) {
        this.reponse = reponse;
    }

    public String getReponse() {
        return reponse;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    
    public void setGoogleAuth( boolean googleAuth ) {
        this.googleAuth = googleAuth;
    }

    public boolean getGoogleAuth() {
        return googleAuth;
    }
}
