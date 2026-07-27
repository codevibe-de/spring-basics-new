# Übungen zu 025 "Beans"

Machen Sie aus der bestehenden Kommandozeilenanwendung eine Spring basierte Anwendung.

Die bestehende Klasse `PizzaApp` ist hierfür der Ausgangspunkt. Diese wurde auf Basis einer bereits angepassten
`pom.xml` bzw. `build.gradle` Datei bereits für Spring vorbereitet.

Diese Übung besteht aus **zwei Phasen**:

## Phase 1

Wir wollen, dass Spring nun alle Beans über Annotations findet.

Somit müssen wir den erstellen ApplicationContext auf `AnnotationConfigApplicationContext` ändern und
die benötigten Annotationen in diversen Klassen hinzufügen.

Sie können frei wählen, ob Sie dies per Stereotypen oder Bean-Methoden machen. Natürlich auch gemischt...

## Phase 2 -- optional

Wie arbeiten wir nun mit den Service-Beans?

Wir können die Ausführung der Geschäftslogik so belassen wie gehabt, oder wir nutzen das bereitgestellte
`pizza.util.CommandLineRunner` Interface. Das geht wie folgt:

1. Erstellung einer neuen Klasse `LogicRunner` (oder beliebiger anderer Name) im Package `pizza`, welche
   das `CommandLineRunner` Interface implementiert.
2. Annotation als Bean (z.B. `@Component`), damit Spring diese Klasse als Bean erkennt.
3. Verschiebung der Geschäftslogik in die `run()` Methode dieser Klasse.
4. Autowiring der benötigten Service-Beans in diese Klasse.
5. Abfrage aller Beans des Typs `CommandLineRunner` in der `PizzaApp.main()` Methode und Ausführung der `run()`
   Methoden. Besonders leicht lässt sich dies (inkl. Sortierung) mit `BeanFactory.getBeanProvider(java.lang.Class<T>)`
   und dessen `orderedStream()` Methode realisieren. So können wir später weitere Runner anlegen und diese in
   der gewünschten Reihenfolge ausführen lassen.

