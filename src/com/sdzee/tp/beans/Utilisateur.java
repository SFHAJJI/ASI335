package com.sdzee.tp.beans;


public class Utilisateur {
	/* Propriétés du bean */
	private String identifiant; //il est référencé dans l'LDAP par l'attribut "uid"
    private String nom; //il est référencé dans l'LDAP par l'attribut "sn"
    private String prenom; //il est référencé dans l'LDAP par l'attribut "cn"
    private String pwd; //il est référencé dans l'LDAP par l'attribut "userPassword"
    private String question; //il est référencé dans l'LDAP par l'attribut "carLicense"
    private String reponse; //il est référencé dans l'LDAP par l'attribut "initials"
    private String email; //il est référencé dans l'LDAP par l'attribut "mail"
    private String qrCode; //il est référencé dans l'LDAP par l'attribut "street"
    private String googleAuth; //il est référencé dans l'LDAP par l'attribut "departmentNumber"
    private String secret; //il est référencé dans l'LDAP par l'attribut ""
   
    
    public void setIdentifiant( String identifiant ) {
        this.identifiant = identifiant;
    }

    public String getIdentifiant() {
        return identifiant;
    }
    public String getSecret() {
        return secret;
    }

    
    public void setSecret(String secret ) {
        this.secret=secret;
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
    
    public void setGoogleAuth( String googleAuth ) {
        this.googleAuth = googleAuth;
    }

    public String getGoogleAuth() {
        return googleAuth;
    }
    
    public void setQrCode( String link ) {
        this.qrCode = link;
    }

    public String getQrCode() {
        return qrCode;
    }
    
}
