<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>

<head>
    <title>Products Management Application</title>
    <!-- Tailwind CSS CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100">

    <header>
        <nav class="bg-tomato p-4">
            <div>
                <a href="https://www.javaguides.net" class="text-white text-lg font-semibold">Products Management App</a>
            </div>

            <ul class="flex space-x-4">
                <li><a href="<%=request.getContextPath()%>/list" class="text-white">Products</a></li>
            </ul>
        </nav>
    </header>

    <br>

    <div class="container mx-auto p-4">
        <div class="bg-white rounded-lg shadow-md p-6">
            <c:if test="${Produits != null}">
                <form action="update" method="post">
            </c:if>
            <c:if test="${Produits == null}">
                <form action="insert" method="post">
            </c:if>

            <caption>
                <h2 class="text-2xl font-bold mb-4">
                    <c:if test="${Produits != null}">
                        Edit User
                    </c:if>
                    <c:if test="${Produits == null}">
                        Add New Products
                    </c:if>
                </h2>
            </caption>

            <c:if test="${Produits != null}">
                <input type="hidden" name="id" value="<c:out value='${Produits.id}' />" />
            </c:if>

            <div class="mb-4">
                <label class="block text-lg font-medium">Product Name</label>
                <input type="text" value="<c:out value='${Produits.nom}' />" class="mt-2 p-2 w-full border border-gray-300 rounded" name="name" required="required">
            </div>

            <div class="mb-4">
                <label class="block text-lg font-medium">Product Description</label>
                <input type="text" value="<c:out value='${Produits.description}' />" class="mt-2 p-2 w-full border border-gray-300 rounded" name="description">
            </div>

            <div class="mb-4">
                <label class="block text-lg font-medium">Product Quantity</label>
                <input type="text" value="<c:out value='${Produits.quantite}' />" class="mt-2 p-2 w-full border border-gray-300 rounded" name="quantite">
            </div>

            <div class="mb-4">
                <label class="block text-lg font-medium">Product Price</label>
                <input type="text" value="<c:out value='${Produits.prix}' />" class="mt-2 p-2 w-full border border-gray-300 rounded" name="prix">
            </div>

            <div class="mb-4">
                <label class="block text-lg font-medium">Product Category</label>
                <input type="text" value="<c:out value='${Produits.categorie}' />" class="mt-2 p-2 w-full border border-gray-300 rounded" name="categorie">
            </div>

            <button type="submit" class="bg-green-500 text-white py-2 px-4 rounded hover:bg-green-600">Save</button>
            </form>
        </div>
    </div>

</body>

</html>
