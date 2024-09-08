package com.yourcompany.easycart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yourcompany.easycart.dao.ProductDAO;
import com.yourcompany.easycart.model.Product;
import com.yourcompany.easycart.model.ShoppingCart;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        Product product = productDAO.getProductById(productId);

        if (product != null) {
            HttpSession session = request.getSession();
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            if (cart == null) {
                cart = new ShoppingCart();
                session.setAttribute("cart", cart);
            }
            cart.addItem(product, 1);
        }

        response.sendRedirect(request.getContextPath() + "/products");
    }
}