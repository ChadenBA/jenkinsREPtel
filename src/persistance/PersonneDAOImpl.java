package persistance;

import metier.Personne;
import BD.conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PersonneDAOImpl implements PersonneDAO {

    @Override

    public void add(Personne p) {
        try (Connection connection = conn.getConnection()) {
            String sql = "INSERT INTO personne (cin, nom, prenom, civilite) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, p.getCin());
                preparedStatement.setString(2, p.getNom());
                preparedStatement.setString(3, p.getPrenom());
                preparedStatement.setString(4, p.getCivilite());

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Personne added successfully.");
                } else {
                    System.out.println("Failed to add Personne.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Personne> getAllPersonne() {
        List<Personne> personnes = new ArrayList<>();
        try (Connection connection = conn.getConnection()) {
            String sql = "SELECT * FROM personne";
            System.out.println(111);
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Personne p = new Personne();
                    p.setCin(resultSet.getInt("cin"));
                    p.setNom(resultSet.getString("nom"));
                    p.setPrenom(resultSet.getString("prenom"));
                    p.setCivilite(resultSet.getString("civilite"));
                    personnes.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personnes;
    }
    
        @Override
        public Personne findById(int numcin) {
            try (Connection connection = conn.getConnection()) {
                String sql = "SELECT * FROM personne WHERE cin = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, numcin);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        Personne p = new Personne();
                        p.setCin(resultSet.getInt("cin"));
                        p.setNom(resultSet.getString("nom"));
                        p.setPrenom(resultSet.getString("prenom"));
                        p.setCivilite(resultSet.getString("civilite"));
                        return p;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

    @Override
    public void supprime(Personne p) {
        try (Connection connection = conn.getConnection()) {
            String sql = "DELETE FROM personne WHERE cin = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, p.getCin());

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Personne deleted successfully.");
                } else {
                    System.out.println("Failed to delete Personne.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifier(Personne p) {
        try (Connection connection = conn.getConnection()) {
            String sql = "UPDATE personne SET nom = ?, prenom = ?, civilite = ?, cin = ? WHERE cin = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, p.getNom());
                preparedStatement.setString(2, p.getPrenom());
                preparedStatement.setString(3, p.getCivilite());
                preparedStatement.setInt(4, p.getCin());
                preparedStatement.setInt(5, p.getCin());
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Personne updated successfully.");
                } else {
                    System.out.println("Failed to update Personne.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    }
    
