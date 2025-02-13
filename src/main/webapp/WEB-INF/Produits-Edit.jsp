<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Product Management Application</title>
            <script src="https://cdn.tailwindcss.com"></script>
        </head>

        <body>

            <header>
                <nav class="bg-red-500 p-4 text-white flex justify-between">
                    <div>
                        <a href="#"> Product Management App </a>
                    </div>

                    <ul class="flex space-x-4">
                        <li><a href="<%=request.getContextPath()%>/list" class="hover:underline">Product</a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container mx-auto w-1/2">
                <div class="bg-white shadow-md rounded-lg p-6">
                    <div class="mb-4">
                        <c:if test="${Produits != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${Produits == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <h2 class="text-xl font-bold mb-4">
                            <c:if test="${Produits != null}">Edit Product</c:if>
                            <c:if test="${Produits == null}">Add New Product</c:if>
                        </h2>

                        <c:if test="${Produits != null}">
                            <input type="hidden" name="id" value="<c:out value='${Produits.id}' />" />
                        </c:if>

                        <div class="mb-4">
                        <label class="block text-lg font-medium">Product Name</label>
                            
                            <input type="text" value="<c:out value='${Produits.nom}' />" class="mt-2 p-2 w-full border border-gray-100 rounded" name="nom" required>
                        </div>

                        <div class="mb-4">
                             <label class="block text-lg font-medium">Product Description</label>
                            <input type="text" value="<c:out value='${Produits.description}' />" class="mt-2 p-2 w-full border border-gray-100 rounded" name="description">
                        </div>

                        <div class="mb-4">
                           <label class="block text-lg font-medium">Product Quantity</label>
                            <input type="number" value="<c:out value='${Produits.quantite}' />" class="mt-2 p-2 w-full border border-gray-100 rounded" name="quantite">
                        </div>
                        <div class="mb-4">
                          <label class="block text-lg font-medium">Product Price</label>
                            <input type="number" value="<c:out value='${Produits.prix}' />" class="mt-2 p-2 w-full border border-gray-100 rounded" name="prix">
                        </div>
                        <div class="mb-4">
                          <label class="block text-lg font-medium">Product Category</label>
                            <input type="text" value="<c:out value='${Produits.categorie}' />" class="mt-2 p-2 w-full border border-gray-100 rounded" name="categorie">
                        </div>
               
                        <button type="submit" class="bg-green-500 text-white py-2 px-4 rounded hover:bg-green-600">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>
       