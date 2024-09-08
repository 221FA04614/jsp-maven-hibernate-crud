<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="Product List">
  <meta name="author" content="EasyCart Team">
  <title>Product List - EasyCart</title>

  <!-- Bootstrap core CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

  <style>
    .product-image {
      max-width: 100px;
      height: auto;
    }
  </style>
</head>

<body>
  <header class="navbar navbar-dark bg-dark p-3">
    <a class="navbar-brand" href="#">EasyCart - Admin</a>
  </header>

  <div class="container">
    <h2 class="my-4 text-center">Product List</h2>

    <!-- Button modal ADD Product -->
    <button type="button" class="btn btn-success mb-4" data-bs-toggle="modal" data-bs-target="#addProductModal">
      Add Product
    </button>

    <!-- Modal for adding product -->
    <div class="modal fade" id="addProductModal" tabindex="-1" aria-labelledby="addProductModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="addProductModalLabel">New Product</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form action="add-product" method="post">
              <div class="mb-3">
                <label for="title" class="form-label">Title</label>
                <input type="text" name="title" id="title" class="form-control" required>
              </div>
              <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" name="description" rows="4" required></textarea>
              </div>
              <div class="mb-3">
                <label for="price" class="form-label">Price</label>
                <input type="number" step="0.01" id="price" name="price" class="form-control" required>
              </div>
              <div class="mb-3">
                <label for="imageLink" class="form-label">Image Link</label>
                <input type="text" name="imageLink" id="imageLink" class="form-control" required>
              </div>
              <button type="submit" class="btn btn-success">Add Product</button>
            </form>
          </div>
        </div>
      </div>
    </div>

    <!-- Products Table -->
    <div class="table-responsive">
      <table class="table table-bordered">
        <thead>
          <tr>
            <th>#</th>
            <th>Title and Description</th>
            <th>Price</th>
            <th>Image</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="product" items="${productList}">
            <tr>
              <td>${product.id}</td>
              <td>
                <strong>${product.title}</strong><br>
                ${product.description}
              </td>
              <td>${product.price}</td>
              <td>
                <img src="${product.imageLink}" alt="${product.title}" class="product-image">
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>

  <!-- Bootstrap Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
