package pizza;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Kleine Bean, die beim Start der Anwendung ausgibt, ob heute Pizza-Freitag ist.
 */
@Component
public class ShopInfo {

    // Der Ausdruck wird einmal beim Erzeugen der Bean ausgewertet:
    //   T(java.time.LocalDate) -> Type-Reference auf die Klasse, nötig für statische Methoden
    //   .now()                 -> statischer Methodenaufruf, liefert das heutige LocalDate
    //   .dayOfWeek             -> Property-Zugriff, ruft getDayOfWeek() auf
    //   .name()                -> Methodenaufruf auf dem Enum, liefert z.B. "FRIDAY"
    //   == 'FRIDAY'            -> vergleicht in SpEL die Werte (equals), nicht die Referenzen
    // Bonus, Vergleich mit der Enum-Konstante, ebenfalls per Type-Reference:
    //   #{T(java.time.LocalDate).now().dayOfWeek == T(java.time.DayOfWeek).FRIDAY}
    @Value("#{T(java.time.LocalDate).now().dayOfWeek.name() == 'FRIDAY'}")
    private boolean pizzaFriday;

    @PostConstruct
    void printPizzaFriday() {
        System.out.println("Heute ist Pizza-Freitag: " + pizzaFriday);
    }

}
