package dao;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDAO {
    private static List<Product> products = new ArrayList<>();

    static {
        // Initialize with some sample products
        products.add(new Product(1, "Laptop", "High-performance laptop", 999, "laptop.jpg"));
        products.add(new Product(2, "Smartphone", "Latest smartphone model", 599, "smartphone.jpg"));
        products.add(new Product(3, "Headphones", "Noise-cancelling headphones", 199, "headphones.jpg"));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}