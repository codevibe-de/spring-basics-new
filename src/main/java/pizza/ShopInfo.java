package pizza;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * Kleine Bean, die beim Start der Anwendung ausgibt, ob heute Pizza-Freitag ist.
 */
@Component
public class ShopInfo {

    // TODO Belegen Sie das Feld per @Value mit einem SpEL-Ausdruck, der genau dann true liefert,
    //  wenn heute Freitag ist. Den heutigen Tag liefert die statische Methode LocalDate.now(),
    //  in SpEL erreichbar über die Type-Reference T(java.time.LocalDate).
    private boolean pizzaFriday;

    @PostConstruct
    void printPizzaFriday() {
        System.out.println("Heute ist Pizza-Freitag: " + pizzaFriday);
    }

}
