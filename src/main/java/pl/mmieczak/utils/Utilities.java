package pl.mmieczak.utils;

import pl.mmieczak.model.Category;
import pl.mmieczak.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class Utilities {

    private Utilities() {
    }

    public static List<Product> filterProducts(List<Product> productList, Category category) {
        productList = productList.stream()
                .filter(item -> item.getCategory().equals(category))
                .collect(Collectors.toList());
        return productList;
    }

    public static BigDecimal productsPriceSum(List<Product> productList) {
        return productList.stream()
                .map(productPrice -> productPrice.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
