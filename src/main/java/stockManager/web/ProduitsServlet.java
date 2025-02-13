package stockManager.web;


import stockManager.dao.ProduitsDao;
import stockManager.model.Produits;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProduitsServlet")
public class ProduitsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProduitsDao produitsDao;

    public void init() {
        produitsDao = new ProduitsDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "insert":
                    insertProduits(request, response);
                    break;
                case "update":
                    updateProduits(request, response);
                    break;
                case "delete":
                    deleteProduits(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                default:
                    listProduits(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listProduits(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Produits> listProduits = produitsDao.selectAllProduits();
        request.setAttribute("listProduits", listProduits);
        request.getRequestDispatcher("produits-list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Produits existingProduits = produitsDao.selectProduits(id);
        request.setAttribute("Produits", existingProduits);
        request.getRequestDispatcher("edit-produits.jsp").forward(request, response);
    }

    private void insertProduits(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        int prix = Integer.parseInt(request.getParameter("prix"));
        String categorie = request.getParameter("categorie");

        Produits newProduits = new Produits(nom, description, quantite, prix, categorie);
        produitsDao.insertProduits(newProduits);
        response.sendRedirect("ProduitsServlet");
    }

    private void updateProduits(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        int prix = Integer.parseInt(request.getParameter("prix"));
        String categorie = request.getParameter("categorie");

        Produits produits = new Produits(id, nom, description, quantite, prix, categorie);
        produitsDao.updateProduits(produits);
        response.sendRedirect("ProduitsServlet");
    }

    private void deleteProduits(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        produitsDao.deleteProduits(id);
        response.sendRedirect("ProduitsServlet");
    }
}
