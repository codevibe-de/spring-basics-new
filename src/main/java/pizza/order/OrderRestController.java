package pizza.order;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderRestController {

    //
    // --- constants ---
    //

    public static final String ROOT = "/orders";
    public static final String GREETING_PATH = ROOT + "/greeting";
    public static final String PLACE_ORDER_PATH = ROOT;
    public static final String GET_MANY_PATH = ROOT;

    //
    // --- injected beans ---
    //

    private final OrderService orderService;
    private final String greeting;

    //
    // --- constructors and setup ---
    //

    public OrderRestController(OrderService orderService, @Value("${app.order.greeting:Hello!}") String greeting) {
        this.orderService = orderService;
        this.greeting = greeting;
    }

    //
    // --- REST endpoints ---
    //

    @GetMapping(GREETING_PATH)
    public String sayHello() {
        return this.greeting;
    }

    @PostMapping(PLACE_ORDER_PATH)
    @ResponseStatus(HttpStatus.CREATED)
    public Order placeOrder(@RequestBody OrderRequest orderRequest) {
        return this.orderService.placeOrder(
                orderRequest.phoneNumber,
                orderRequest.itemQuantities);
    }

    @GetMapping(GET_MANY_PATH)
    public Iterable<Order> getOrders() {
        return this.orderService.getOrders();
    }
}
