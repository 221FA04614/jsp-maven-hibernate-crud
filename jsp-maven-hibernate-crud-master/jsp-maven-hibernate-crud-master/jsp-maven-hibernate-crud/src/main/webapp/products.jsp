<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="Product Page">
  <meta name="author" content="EasyCart Team">
  <title>Products - EasyCart</title>

  <!-- Bootstrap core CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

  <style>
    .product-card {
      margin-bottom: 20px;
    }
    .product-image {
      max-width: 100%;
      height: auto;
    }
    .product-description {
      max-height: 100px;
      overflow: hidden;
    }
    .card-footer button {
      background-color: bisque;
    }
    body {
      background-color: lightblue;
    }
  </style>
</head>
<body>

  <!-- Header -->
  <header class="navbar navbar-light bg-light p-3">
    <a class="navbar-brand" href="#">EasyCart</a>
    <a href="cart.jsp" class="btn btn-outline-secondary">View Cart</a>
  </header>

  <!-- Main Content -->
  <div class="container mt-4">
    <h2 class="text-center mb-4">Available Products</h2>
    
    <div class="row">
      <!-- Iterate through the product list and display each product -->
      <c:forEach var="product" items="${productList}">
        <div class="col-lg-4 col-md-6">
          <div class="card product-card">
            <img src="${product.imageLink}" class="card-img-top product-image" alt="${product.title}">
            <div class="card-body">
              <h5 class="card-title">${product.title}</h5>
              <p class="card-text product-description">${product.description}</p>
              <p class="card-text"><strong>Price: $${product.price}</strong></p>
            </div>
            <div class="card-footer">
              <form action="add-to-cart" method="post">
                <input type="hidden" name="productId" value="${product.id}">
                <button type="submit" class="btn btn-primary btn-block">Add to Cart</button>
              </form>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
  </div>

  <!-- Footer -->
  <footer class="py-3 my-4 text-center">
    <p class="text-muted">© 2024 EasyCart</p>
  </footer>

  <!-- Bootstrap Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
