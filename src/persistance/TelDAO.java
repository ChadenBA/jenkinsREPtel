package persistance;

import java.util.List;

import metier.Personne;
import metier.Telephone;

public interface TelDAO {
    void  addtel(Telephone t ,Personne p);
    void  supprimetel(Telephone p);
    void  modifiertel(Telephone p);
     List<Telephone>   getTel();
     List<Telephone> getPhonesByCin(int cin);
     List<Telephone> getPhonesByPersonName(String personName);
     Telephone findTelephoneByPersonne(Personne p );
}
 
