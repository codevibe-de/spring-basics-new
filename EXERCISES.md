# Übungen zum Kapitel "040 - Testing"

> **Disabled:** Die Testklassen beider Übungen sind mit `@Disabled` deaktiviert, damit
> der Build im Ausgangszustand grün ist. Entferne die `@Disabled`-Annotation der
> jeweiligen Klasse, bevor du mit der Übung beginnst.

> **Ordner:** Die Testklassen liegen im Verzeichnis `/src/test/java`.

## a) CustomerServiceTest – Spring-Konfiguration ergänzen

Der eigentliche Test-Code für `CustomerService.createCustomer(...)` ist in der Klasse
`pizza.customer.CustomerServiceTest` fast vollständig vorgegeben (given / when / then inkl. der AssertJ-Prüfungen).

Deine Aufgabe ist es, die Testklasse zu einem lauffähigen Spring-Test zu machen:

1. **Klassen-Annotation** – Ergänze an der Klasse die passende Annotation, damit für den Test ein Spring-
   `ApplicationContext` hochgefahren wird (z. B. `@SpringJUnitConfig(PizzaApp.class)`).
2. **Autowiring** – Lass das Feld `customerService` per `@Autowired` aus dem Context injizieren.
3. Vervollständige den Aufruf der zu testenden Logik ("when")

Führe den Test anschließend aus und stelle sicher, dass er grün ist.

## b) OrderServiceTest – Integrationstest zum Laufen bringen

Der Test `pizza.order.OrderServiceTest` bootet über `@SpringJUnitConfig(PizzaApp.class)`
den kompletten Spring-Context und gibt darin Bestellungen auf. Der eigentliche Test-Code (inkl. `DummyProductRepository`
und Assertions) ist **vorgegeben** – aber der Test läuft in diesem Zustand **nicht** durch. Deine Aufgabe ist es, die
zugrundeliegenden Probleme zu lösen. Dabei lernst du gleich mehrere Konzepte kennen.

### 1. CommandLineRunner laufen im Test nicht

In `PizzaApp` werden die `CommandLineRunner` (u. a. der `DataLoadRunner`, der die Testdaten lädt) bisher **von Hand in
`main(...)`** aufgerufen.

Ein Test ruft niemals `main(...)` auf – deshalb werden im Test keine Runner ausgeführt, es werden **keine Daten
geladen**, und `orderService.placeOrder(...)` findet den Kunden nicht.

Löse das, indem die Runner **beim Hochfahren des Context** ausgeführt werden statt in
`main(...)`. Verwende dafür einen Event-Listener in der `PizzaApp` Klasse, z. B.:

```java

@EventListener(ContextRefreshedEvent.class)
public void runRunners() { ...}
```

So laufen die Runner sowohl in der `main(...)`-Anwendung als auch im Test.

### 2. CLI-Argumente als Bean zwischenparken

Die Runner brauchen die Kommandozeilen-Argumente (`String[] args`). Da sie jetzt nicht mehr direkt aus `main(...)`
aufgerufen werden, müssen die Args über den Context verfügbar sein. Dafür ist das Record `pizza.util.CliArgs` bereits
**vorgegeben**:

```java
public record CliArgs(String[] args) {
}
```

Deine Aufgabe: Registriere diese Bean in `main(...)` (z. B. per
`context.registerBean(CliArgs.class, () -> new CliArgs(args))`) und injiziere sie in den Event-Listener aus Schritt 1.

Wichtig: Im Test wird `main(...)` nicht ausgeführt, die Bean fehlt dort also – injiziere sie deshalb mit
`@Autowired(required = false)` und fange den `null`-Fall ab.

### 3. Den LogicRunner im Test deaktivieren

Der `LogicRunner` gibt Demo-Ausgaben aus und legt eine Beispielbestellung an – im Test wollen wir ihn **nicht** laufen
lassen. Er soll nur laufen, wenn die Anwendung „echt“ über `main(...)` gestartet wurde (d. h. wenn die `CliArgs`-Bean
vorhanden ist).

Die dafür nötige `Condition` ist bereits **fertig vorgegeben**: `pizza.util.CliArgsPresentCondition` liefert genau dann
`true`, wenn eine `CliArgs`-Bean registriert ist (die handgeschriebene Variante von Spring Boots `@ConditionalOnBean`).

Deine Aufgabe: Wende sie an, indem du den `LogicRunner` mit `@Conditional(CliArgsPresentCondition.class)` annotierst.

Der `DataLoadRunner` bleibt **ohne** Condition, damit die Testdaten weiterhin geladen werden.

### 4. Zwei Test-Annotationen in OrderServiceTest ergänzen

1. **`@TestPropertySource`** – Fülle die `properties` mit zwei Einträgen:
    - `app.data-loader=sample`, damit der `sample`-DataLoader verwendet wird. Andernfalls greift der Default aus
      `application.properties` (`csv`), der nur Produkte, aber keine Kunden lädt.
    - `app.order.discount-days=` (leer), um den (tagesabhängigen!) Rabatt abzuschalten. So ist der erwartete Gesamtpreis
      deterministisch – unabhängig davon, an welchem Wochentag der Test läuft.
2. **`@DirtiesContext`** – Der Test `placeOrder` ersetzt das `productRepository` im
   `productService` per Reflection (ein Hack, der ein Singleton verändert). Markiere die Methode mit `@DirtiesContext`,
   damit der Context danach verworfen und für `placeAnotherOrder` sauber neu aufgebaut wird -- sonst schlägt dieser Test
   fehl, ohne dass es hierfür einen unmittelbaren Grund zu sehen gibt.

### Ziel

Führe `OrderServiceTest` aus, bis beide Testmethoden grün sind. Die Referenzlösung findest du im Branch
`040-testing-solution`.

