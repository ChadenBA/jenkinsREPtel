package metier;

import java.util.Objects;

public class Telephone implements Comparable<Telephone> {
    private  int valeur;
    private  String type;
    private  int cin;

    public Telephone(int valeur, String type, int cin) {
        this.valeur = valeur;
        this.type = type;
        this.cin = cin;
    }
     public Telephone() {
       
    }

    public int getValeur() {
        return this.valeur;
    }

    public String getType() {
        return type;
    }

    public int getCin() {
        return cin;
    }
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
    public void setCin(int cin) {
        this.cin = cin;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int compareTo(Telephone o) {
        if (o == null) {
            // Handle the comparison when the other object is null
            return 1; // or -1, depending on your requirements
        }
        return Integer.compare(this.valeur, o.getValeur());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Telephone telephone = (Telephone) obj;
        return valeur == telephone.valeur && cin == telephone.cin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valeur, cin);
    }

    @Override
    public String toString() {
        return "Telephone{" +
                "valeur=" + valeur +
                ", type='" + type + '\'' +
                ", cin=" + cin +
                '}';
    }
}
