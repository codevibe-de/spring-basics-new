// Folie 263 – RestController – GET mit Pfadvariable
// Folie 265 – Query Parameter
package com.example.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pizza.product.Product;

@RestController
public class ProductRestController {

    public static final String ROOT = "/products";
    public static final String GET_ONE_ENDPOINT = ROOT + "/{id}";

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    // --- Folie 263 ---

    @GetMapping(GET_ONE_ENDPOINT)
    public Product getProduct(@PathVariable("id") String prdId) {
        return this.productService.getProduct(prdId);
    }

    // --- Folie 265 ---

    @DeleteMapping("/products")
    public Integer deleteProducts(
            @RequestParam(value = "namePrefix", defaultValue = "") String p)
    {
        return this.productService.deleteProductsWithNamePrefix(p);
    }
}
