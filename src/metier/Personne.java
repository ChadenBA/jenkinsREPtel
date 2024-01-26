package metier;
import java.util.ArrayList;
import java.util.List;

public class Personne implements Comparable<Personne> {
    private int cin;
    private String nom;
    private String prenom;
    private String civilite;
    private List<Telephone> telephones; 

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilité) {
        this.civilite = civilité;
    }

    public List<Telephone> getTelephones() {
        return telephones;
    }

    public void setTelephones(List<Telephone> telephones) {
        this.telephones = telephones;
    }

    @Override
    public int compareTo(Personne o) {
        return Integer.compare(this.cin, o.getCin());
    }
    

    @Override
    public String toString() {
        return "Personne{" +
                "cin=" + cin +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", civilité='" + civilite + '\'' +
                ", telephones=" + telephones +
                '}';
    }
}
