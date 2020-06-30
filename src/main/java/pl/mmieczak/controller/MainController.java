package pl.mmieczak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mmieczak.model.Category;
import pl.mmieczak.model.Product;
import pl.mmieczak.model.ProductRepository;
import pl.mmieczak.utils.Utilities;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class MainController {

    ProductRepository productRepository;

    public MainController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String main(Model model) {
        return "index";
    }

    @GetMapping("/lista")
    public String products(Model model, @RequestParam("kategoria") String category) {
        List<Product> productList = productRepository.getAll();
        BigDecimal sumOfProductPrices;

        switch (category) {
            case "spozywcze": {
                generateModelForProductsTemplate(model, productList, Category.GROCERIES);
                break;
            }
            case "artgospdomowego": {
                generateModelForProductsTemplate(model, productList, Category.HOUSEHOLD_ITEMS);
                break;
            }
            case "inne":
                generateModelForProductsTemplate(model, productList, Category.OTHER);
                break;
            default: {
                sumOfProductPrices = Utilities.productsPriceSum(productList);
                model.addAttribute("category", "Wszystkie produkty");
                model.addAttribute("products", productList);
                model.addAttribute("productsPriceSum", sumOfProductPrices);
            }
        }
        return "products";
    }

    @GetMapping("/dodaj")
    public String add(Model model) {
        model.addAttribute("title","Dodaj produkt");
        return "addProduct";
    }

    @PostMapping("dodajProdukt")
    public String addProduct(Model model, @RequestParam String name, @RequestParam String price, @RequestParam String category) {
        boolean result = true;
        if (!name.equals("") && price != null) {
            productRepository.add(name, new BigDecimal(price), Category.getCategoryUsingDescription(category));
        } else {
            result = false;
        }
        return result ? "redirect:success.html" : "redirect:err.html";
    }


    private void generateModelForProductsTemplate(Model model, List<Product> productList, Category groceries) {
        BigDecimal sumOfProductPrices;
        productList = Utilities.filterProducts(productList, groceries);
        sumOfProductPrices = Utilities.productsPriceSum(productList);
        model.addAttribute("category", groceries.getDescription());
        model.addAttribute("products", productList);
        model.addAttribute("productsPriceSum", sumOfProductPrices);
    }


}

