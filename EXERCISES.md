# Übungen zum Kapitel "040 - Testing"

## a) CustomerServiceTest – Spring-Konfiguration ergänzen

Der eigentliche Test-Code für `CustomerService.createCustomer(...)` ist in der
Klasse `pizza.customer.CustomerServiceTest` bereits **vollständig vorgegeben**
(given / when / then inkl. der AssertJ-Prüfungen).

Führt man den Test in diesem Zustand aus, schlägt er fehl (`NullPointerException`),
weil der `customerService` nicht befüllt wird – es fehlt die Spring-Konfiguration.
Deine Aufgabe ist es, die Testklasse zu einem lauffähigen Spring-Test zu machen:

1. **Klassen-Annotation** – Ergänze an der Klasse die passende Annotation, damit
   für den Test ein Spring-`ApplicationContext` hochgefahren wird
   (z. B. `@SpringJUnitConfig(PizzaApp.class)`).
2. **Autowiring** – Lass das Feld `customerService` per `@Autowired` aus dem
   Context injizieren.
3. Ergänze die dafür nötigen `import`-Anweisungen.

Führe den Test anschließend aus und stelle sicher, dass er grün ist.

