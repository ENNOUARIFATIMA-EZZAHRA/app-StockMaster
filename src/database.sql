CREATE DATABASE stockmaster;
CREATE TABLE produits (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    description TEXT,
    quantite INT NOT NULL CHECK (quantite >= 0),
    prix DECIMAL(10,2) NOT NULL CHECK (prix >= 0),
    categorie VARCHAR(50)
);
