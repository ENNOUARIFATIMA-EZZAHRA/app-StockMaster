<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Product List</title>
   
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">

    <div class="container mx-auto p-6">
        <h2 class="text-3xl font-bold mb-4">Product List</h2>
        <a href="<%=request.getContextPath()%>/new" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">Add New Product</a>
        
        <table class="min-w-full bg-white shadow-md rounded-lg mt-6">
            <thead class="bg-gray-200">
                <tr>
                    <th class="px-6 py-3 text-left">ID</th>
                    <th class="px-6 py-3 text-left">Name</th>
                    <th class="px-6 py-3 text-left">Description</th>
                    <th class="px-6 py-3 text-left">Quantity</th>
                    <th class="px-6 py-3 text-left">Price</th>
                    <th class="px-6 py-3 text-left">Category</th>
                    <th class="px-6 py-3 text-left">Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="Produits" items="${listProduits}">
                    <tr class="border-t">
                        <td class="px-6 py-3">${Produits.id}</td>
                        <td class="px-6 py-3">${Produits.nom}</td>
                        <td class="px-6 py-3">${Produits.description}</td>
                        <td class="px-6 py-3">${Produits.quantite}</td>
                        <td class="px-6 py-3">${Produits.prix}</td>
                        <td class="px-6 py-3">${Produits.categorie}</td>
                        <td class="px-6 py-3">
                            <a href="edit?id=${Produits.id}" class="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600">Edit</a>
                            <a href="delete?id=${Produits.id}" class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600 ml-2">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>
