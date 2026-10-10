# Übungen zu 027 "AOP, Resources & SpEL"

In dieser Lektion lernen Sie drei Kern-Themen des Spring-Frameworks kennen. Ausgangspunkt ist die
annotationsbasierte Pizza-Anwendung.

Die drei Teilaufgaben sind **unabhängig** voneinander und können in beliebiger Reihenfolge gelöst werden.
Für alle Aufgaben sind bereits Gerüst-Klassen bzw. `TODO`-Markierungen vorbereitet.

Sie müssen keine neuen Abhängigkeiten hinzufügen: `spring-context` bringt Spring AOP, Resources und SpEL mit, und
für die Aspekt-Annotationen (`@Aspect`, `@Around`, ...) steht `aspectjweaver` bereits in `pom.xml` bzw. `build.gradle`.

---

## a) AOP -- Profiling- und Tracing-Aspekt

Ziel: Methodenaufrufe einer bestehenden Bean mit Aspekten "umwickeln", ohne deren Code zu ändern.

1. Im Package `pizza.aop` finden Sie die Klasse **`ProfilingAspect`**. Die Zeitmessung um `joinPoint.proceed()`
   ist bereits fertig. Machen Sie daraus einen echten Aspekt:
    - Markieren Sie die Klasse als Aspekt (`@Aspect`) und als Bean (`@Component`).
    - Machen Sie die Methode `profile(...)` zu einem **Around-Advice** (`@Around`), dessen Pointcut-Expression alle
      Methoden der Klasse `pizza.product.ProductService` erfasst.

2. Damit Spring für Beans, auf die ein Aspekt passt, automatisch einen Proxy erzeugt, muss die Verarbeitung der
   Aspekte eingeschaltet werden: Ergänzen Sie an der Konfigurationsklasse `PizzaApp` die Annotation
   `@EnableAspectJAutoProxy`.

   ➡️ Starten Sie die Anwendung. Da `LogicRunner` und `DataLoader` den `ProductService` nutzen, sehen Sie nun für
   jeden Aufruf eine Zeile wie `Execution of getAllProducts() took 0 ms` -- ohne dass Sie diese Klassen angefasst
   haben.

3. Schreiben Sie in der vorbereiteten Klasse **`TraceAspect`** einen **Before-Advice** (`@Before`) für dieselben
   Methoden, der vor jedem Aufruf den Methodennamen und die Argumente ausgibt, z.B.
   `About to execute getProduct(P-10)`. Name und Argumente liefert ein Parameter vom Typ `JoinPoint`
   (`getSignature().getName()` bzw. `getArgs()`).

> **Bonus:** Lassen Sie sich im `LogicRunner` mit `productService.getClass().getName()` die Klasse der injizierten
> Bean ausgeben. Was sehen Sie, und warum?

## b) Resources -- CSV-DataLoader

Ziel: Produkte aus einer Datei laden, die über Springs `Resource`-Abstraktion angesprochen wird.

In `DataLoader.java` gibt es eine neue innere Klasse **`DataLoader.Csv`** (Bean-Name `"csv"`). Ihr soll
Springs `ResourceLoader` in den Konstruktor injiziert werden (eine solche Bean gibt uns Spring).

Die Datei `src/main/resources/products.csv` hat das Format `id;name;preis`.

1. Implementieren Sie die `todo` Stellen `DataLoader.Csv`.
2. Aktivieren Sie den neuen Loader, indem Sie in `LogicRunner` den `@Qualifier("sample")` auf
   `@Qualifier("csv")` umstellen. Beim Start der Anwendung sollten die Produkte nun aus der CSV-Datei
   stammen.

> Hinweis: Der Dateipfad ist hier noch fest verdrahtet. In der nächsten Lektion ("Configuration")
> werden Sie solche Werte in die Konfiguration auslagern.

## c) SpEL -- Spring Expression Language

Ziel: Ausdrücke der Spring Expression Language schreiben und auswerten.

Die eigenständige Klasse `SpelParserApp` richtet einen `SpelExpressionParser` und einen
`StandardEvaluationContext` ein. Über einen `BeanFactoryResolver` hat der Ausdruck Zugriff auf die Beans des
Containers (Referenz per `@beanName`).

Ersetzen Sie in `SpelParserApp` den Platzhalter-Ausdruck (`"23 + 42"`) durch einen SpEL-Ausdruck, der die **Namen aller
Produkte** liefert. Verwenden Sie dabei:

- eine **Bean-Referenz** (`@productService`),
- **Property-Zugriff** (`.allProducts` -> `getAllProducts()`),
- eine **Collection-Projection** (`.![ ... ]`), die jedes Produkt auf seinen Namen abbildet.

Führen Sie `SpelParserApp` aus und experimentieren Sie anschließend mit weiteren Ausdrücken, z.B.:

- `@productService.allProducts.size()` -- Anzahl der Produkte,
- `@productService.allProducts.?[price.doubleValue() < 7.0].![name]` -- **Selection** (`.?[...]`)
  kombiniert mit einer Projektion: die Namen aller Produkte günstiger als 7,00,
- `T(java.time.LocalDate).now()` -- eine **Type-Reference** (`T(...)`) mit statischem Methodenaufruf.

> Hinweis: SpEL wird sehr häufig auch direkt in Annotationen genutzt, z.B. in `@Value("#{ ... }")`.
> Diese Form lernen Sie in der nächsten Lektion kennen.
