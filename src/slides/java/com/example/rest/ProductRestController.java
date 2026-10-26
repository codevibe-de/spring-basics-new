// Folie 263 – RestController – GET mit Pfadvariable
// Folie „RestController – PUT“, hinter 264 (Deck mit 290 Folien)
// Folie 265 – Query Parameter
package com.example.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pizza.product.Product;

@RestController
public class ProductRestController {

    public static final String ROOT = "/products";
    public static final String GET_ONE_PATH = ROOT + "/{id}";
    public static final String UPDATE_PATH = ROOT + "/{id}";

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    // --- Folie 263 ---

    @GetMapping(GET_ONE_PATH)
    public Product getProduct(@PathVariable("id") String prdId) {
        return this.productService.getProduct(prdId);
    }

    // --- Folie „RestController – PUT“ ---

    @PutMapping(UPDATE_PATH)
    public Product updateProduct(
            @PathVariable String id,
            @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    // --- Folie 265 ---

    @DeleteMapping("/products")
    public Integer deleteProducts(
            @RequestParam(value = "namePrefix", defaultValue = "") String p)
    {
        return this.productService.deleteProductsWithNamePrefix(p);
    }
}
