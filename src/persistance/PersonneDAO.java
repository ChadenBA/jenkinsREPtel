package persistance;
import java.util.List;

import metier.Personne;
public interface PersonneDAO {
    void add(Personne p);
    void supprime(Personne p);
    void modifier(Personne p);
    List<Personne> getAllPersonne(); 
    Personne findById(int numcin);

}
