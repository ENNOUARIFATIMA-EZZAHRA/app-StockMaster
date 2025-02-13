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
    private String jdbcURL = "jdbc:mysql://localhost:3306/stockmaster?useSSL=false&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "1234";

    private static final String INSERT_PRODUITS_SQL = "INSERT INTO Produits (nom, description, quantite, prix, categorie) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_PRODUITS_BY_ID = "SELECT * FROM Produits WHERE id = ?";
    private static final String SELECT_ALL_PRODUITS = "SELECT * FROM Produits";
    public static final String DELETE_PRODUITS_SQL = "DELETE FROM produits WHERE id = ?";
    public static final String UPDATE_PRODUITS_SQL = "UPDATE produits SET nom = ?, description = ?, quantite = ?, prix = ?, categorie = ? WHERE id = ?";
	private static final Produits Produits = null;

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
        return Produits;
    }

    public List<Produits> selectAllProduits() {
        List<Produits> ProduitsList = new ArrayList<>();
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
                ProduitsList.add(new Produits(id, nom, description, quantite, prix, categorie));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ProduitsList;
    }

    public boolean deleteProduits(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); 
        		PreparedStatement statement = connection.prepareStatement(DELETE_PRODUITS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateProduits(Produits produits) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUITS_SQL);) {
            statement.setString(1, produits.getNom());
            statement.setString(2, produits.getDescription());
            statement.setInt(3, produits.getQuantite());
            statement.setInt(4, produits.getPrix());
            statement.setString(5, produits.getCategorie());
            statement.setInt(6, produits.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
