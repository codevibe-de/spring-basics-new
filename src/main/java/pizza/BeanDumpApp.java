package pizza;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pizza.customer.CustomerService;
import pizza.product.ProductService;


public class BeanDumpApp {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(CustomerService.class);
        for (String s : context.getBeanDefinitionNames()) {
            System.out.println(s);
        }
    }

}
