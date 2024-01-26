package persistance;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import BD.conn;
import metier.Personne;
import metier.Telephone;

public class TelDAOImpl implements TelDAO {

    @Override
public void addtel(Telephone tel, Personne p) {
    try (Connection connection = conn.getConnection()) {
        String sql = "INSERT INTO telephone (valeur, type, cin) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, tel.getValeur());
            preparedStatement.setString(2, tel.getType());
            preparedStatement.setInt(3, p.getCin());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Telephone added successfully.");
            } else {
                System.out.println("Failed to add Telephone.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    @Override
    public void supprimetel(Telephone tel) {
        try (Connection connection = conn.getConnection()) {
            String sql = "DELETE FROM telephone WHERE valeur = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, tel.getValeur());

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Telephone deleted successfully.");
                } else {
                    System.out.println("Failed to delete Telephone.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

@Override
public void modifiertel(Telephone tel) {
    try (Connection connection = conn.getConnection()) {
        String sql = "UPDATE telephone SET type = ? WHERE valeur = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, tel.getType());
            preparedStatement.setInt(2, tel.getValeur());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Telephone updated successfully.");
            } else {
                System.out.println("Failed to update Telephone. No rows affected.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Failed to update Telephone: " + e.getMessage());
    }
}
    
    
    
    

    
    
    

    @Override
    public List<Telephone> getTel() {
        List<Telephone> telephones = new ArrayList<>();
        try (Connection connection = conn.getConnection()) {
            String sql = "SELECT * FROM telephone";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Telephone tel = new Telephone();
                    tel.setValeur(resultSet.getInt("valeur"));
                    tel.setType(resultSet.getString("type"));
                    tel.setCin(resultSet.getInt("cin"));
                    telephones.add(tel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return telephones;
    }
    
    @Override
public List<Telephone> getPhonesByPersonName(String personName) {
    List<Telephone> phones = new ArrayList<>();

    try (Connection connection = conn.getConnection()) {
        String sql = "SELECT t.* FROM telephone t JOIN personne p ON t.cin = p.cin WHERE p.nom = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, personName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Telephone phone = new Telephone();
                    phone.setValeur(resultSet.getInt("valeur"));
                    phone.setType(resultSet.getString("type"));

                    phones.add(phone);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Handle the exception appropriately
    }

    return phones;
}

      @Override
      public Telephone findTelephoneByPersonne(Personne p) {
        Telephone telephone = null;
        try (Connection connection = conn.getConnection()) {
            String sql = "SELECT telephone FROM telephone WHERE personne_cin = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, p.getCin());
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    telephone = new Telephone();
                    telephone.setValeur(resultSet.getInt("telephone"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return telephone;
     
      }
      @Override
    public List<Telephone> getPhonesByCin(int cin) {
        List<Telephone> phones = new ArrayList<>();

      
        try (Connection connection = conn.getConnection()) {
            String sql = "SELECT * FROM telephone WHERE cin = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, cin);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Telephone phone = new Telephone();
                        phone.setValeur(resultSet.getInt("valeur"));
                        phone.setType(resultSet.getString("type"));

                        phones.add(phone);
                        
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return phones;
    }

}
