# Übungen zu 027 "AOP, Resources & SpEL"

In dieser Lektion lernen Sie drei Kern-Themen des Spring-Frameworks kennen. Ausgangspunkt ist die
annotationsbasierte Pizza-Anwendung.

Die drei Teilaufgaben sind **unabhängig** voneinander und können in beliebiger Reihenfolge gelöst werden.
Für alle Aufgaben sind bereits Gerüst-Klassen bzw. `TODO`-Markierungen vorbereitet.

Sie müssen keine neuen Abhängigkeiten hinzufügen (`spring-context` bringt AOP, Resources und SpEL bereits mit).

---

## a) AOP -- Tracing- und Profiling-Aspekt

Ziel: Methodenaufrufe einer bestehenden Bean mittels Spring AOP "umwickeln", ohne deren Code zu ändern.

1. Im Package `pizza.aop` finden Sie zwei vorbereitete Klassen:
    - **`TraceBeforeMethodAdvice`** implementiert Springs `MethodBeforeAdvice`.
      Implementieren Sie die `before(...)`-Methode so, dass **vor** jedem Methodenaufruf eine Nachricht
      auf `System.out` geschrieben wird (z.B. `About to execute getProduct(P-10)`).
    - **`ProfilingInterceptor`** implementiert das AOP-Alliance-Interface
      `org.aopalliance.intercept.MethodInterceptor`. Damit können Sie Code **vor und nach** einem Aufruf
      ausführen. Führen Sie eine Zeitmessung um `invocation.proceed()` durch und geben Sie aus, wie lange
      die umwickelte Methode gebraucht hat.

2. Lassen Sie in `PizzaApp.main()` mit Springs `ProxyFactoryBean` einen AOP-Proxy einer bestehenden
   Bean-Instanz erzeugen (z.B. der `ProductService`-Bean aus dem Container). Fügen Sie beide Advices hinzu
   (`addAdvice(...)`) und rufen Sie anschließend eine Methode auf dem Proxy auf (z.B. `getProduct("P-10")`).

   Durch die Ausführung sollten dann beide Aspekte in Aktion treten.

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

Ersetzen Sie in `SpelParserApp` den Platzhalter-Ausdruck (`"23 + 42"`) durch einen SpEL-Ausdruck, der die
**Namen aller Produkte** liefert. Verwenden Sie dabei:

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
