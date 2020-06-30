package pl.mmieczak.model;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productList = new ArrayList<>();

    public ProductRepository() {
        productList.add(new Product("Maslo", new BigDecimal("14.99"), Category.GROCERIES));
        productList.add(new Product("Kufel", new BigDecimal("25"), Category.HOUSEHOLD_ITEMS));
        productList.add(new Product("Sok", new BigDecimal("4.99"), Category.GROCERIES));
        productList.add(new Product("Patelnia", new BigDecimal("14.99"), Category.HOUSEHOLD_ITEMS));
        productList.add(new Product("Ryż", new BigDecimal("7.50"), Category.GROCERIES));
        productList.add(new Product("TV", new BigDecimal("4500"), Category.OTHER));
        productList.add(new Product("iPhone", new BigDecimal("3000"), Category.OTHER));
    }

    public void add(String name, BigDecimal price, Category category) {
        productList.add(new Product(name, price, category));
    }

    public List<Product> getAll() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
