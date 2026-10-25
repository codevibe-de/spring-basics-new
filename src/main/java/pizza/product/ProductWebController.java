package pizza.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Renders a single product as an HTML page via Thymeleaf.
 *
 * <p>Two request styles are offered:
 * <ul>
 *     <li>REST-conform path variable — {@code GET /web/products/{productId}}</li>
 *     <li>query parameter — {@code GET /web/products?productId=…}</li>
 * </ul>
 * Both load the product through {@link ProductService}, put it into the model
 * under the key {@code "product"}, and render the view {@code products/single-product}.
 *
 * <p>The two methods also show the two common return styles of a controller method:
 * a {@link ModelAndView} (as on the slides) and the shorter form of a view name as
 * {@code String} plus a {@link Model} parameter.
 */
@Controller
public class ProductWebController {

    private static final String VIEW = "products/single-product";

    private final ProductService productService;

    public ProductWebController(ProductService productService) {
        this.productService = productService;
    }

    // REST-conform variant: /web/products/P-10
    // Short form: return the view name, Spring passes in the Model to fill
    @GetMapping("/web/products/{productId}")
    public String showProductByPath(@PathVariable String productId, Model model) {
        model.addAttribute("product", productService.getProduct(productId));
        return VIEW;
    }

    // Query-parameter variant: /web/products?productId=P-10
    // ModelAndView as on the slides: view name and model data in one object
    @GetMapping("/web/products")
    public ModelAndView showProductByParam(@RequestParam String productId) {
        var mv = new ModelAndView(VIEW);
        mv.addObject("product", productService.getProduct(productId));
        return mv;
    }
}
