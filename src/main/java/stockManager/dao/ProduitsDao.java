package stockManager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import stockManager.model.Produits;

public class ProduitsDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/stockmaster?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_PRODUITS_SQL = "INSERT INTO produits (nom, description, quantite, prix, categorie) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_PRODUITS_BY_ID = "SELECT * FROM produits WHERE id = ?";
    private static final String SELECT_ALL_PRODUITS = "SELECT * FROM produits";
    private static final String DELETE_PRODUITS_SQL = "DELETE FROM produits WHERE id = ?";
    private static final String UPDATE_PRODUITS_SQL = "UPDATE produits SET nom = ?, description = ?, quantite = ?, prix = ?, categorie = ? WHERE id = ?";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertProduits(Produits produits) throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUITS_SQL)) {
            preparedStatement.setString(1, produits.getNom());
            preparedStatement.setString(2, produits.getDescription());
            preparedStatement.setInt(3, produits.getQuantite());
            preparedStatement.setInt(4, produits.getPrix());
            preparedStatement.setString(5, produits.getCategorie());
            preparedStatement.executeUpdate();
        }
    }

    public Produits selectProduits(int id) {
        Produits produits = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUITS_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("nom");
                String description = rs.getString("description");
                int quantite = rs.getInt("quantite");
                int prix = rs.getInt("prix");
                String categorie = rs.getString("categorie");
                produits = new Produits(id, nom, description, quantite, prix, categorie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }

    public List<Produits> selectAllProduits() {
        List<Produits> produitsList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUITS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String description = rs.getString("description");
                int quantite = rs.getInt("quantite");
                int prix = rs.getInt("prix");
                String categorie = rs.getString("categorie");
                produitsList.add(new Produits(id, nom, description, quantite, prix, categorie));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produitsList;
    }

    public boolean deleteProduits(int id) throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PRODUITS_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    public boolean updateProduits(Produits produits) throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUITS_SQL)) {
            statement.setString(1, produits.getNom());
            statement.setString(2, produits.getDescription());
            statement.setInt(3, produits.getQuantite());
            statement.setInt(4, produits.getPrix());
            statement.setString(5, produits.getCategorie());
            statement.setInt(6, produits.getId());
            return statement.executeUpdate() > 0;
        }
    }
}
