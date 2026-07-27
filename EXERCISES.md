# Übungen zu 023 - Crashkurs Annotationen

## Übung 1: Eigene Annotation erstellen und per Reflection auswerten

Erstellen Sie eine eigene Annotation `@Info` und markieren Sie damit eine Klasse.

1. Definieren Sie die Annotation `@Info` mit den Elementen `author` (String) und
   `version` (int, Default-Wert `1`).
2. Sorgen Sie mit der Meta-Annotation `@Retention(RetentionPolicy.RUNTIME)` dafür,
   dass die Annotation zur Laufzeit verfügbar ist, und beschränken Sie das Ziel
   mit `@Target(ElementType.TYPE)` auf Klassen.
3. Versehen Sie eine beliebige Klasse mit der Annotation, z. B.
   `@Info(author = "Max Mustermann", version = 2)`.
4. Lesen Sie die Annotation zur Laufzeit per Reflection aus
   (`clazz.getAnnotation(Info.class)`) und geben Sie `author` und `version`
   auf der Konsole aus.

## Übung 2: Vom `XmlBeanContainer` zum `AnnotationConfigBeanContainer`

In Lektion 013 haben wir mit dem `XmlBeanContainer` unsere Beans noch aus einer
`beans.xml` definiert. Jetzt drehen wir den Spieß um: Statt jede Bean in XML
aufzulisten, **markieren wir die Bean-Klassen mit einer eigenen Annotation** und
lassen den Container die passenden Klassen per Reflection selbst finden. Damit
bauen wir – zu Fuß – nach, was Spring in Lektion 025 mit `@Component` und
`@ComponentScan` liefert.

1. Erstellen Sie eine eigene Marker-Annotation `@Component` (nach dem Muster aus
   Übung 1: `@Retention(RUNTIME)`, `@Target(TYPE)`, zunächst ohne Elemente).
2. Annotieren Sie die vorhandenen Bean-Klassen (z. B. `ProductService`,
   `CustomerService`, `OrderService`, `HashMapProductRepository`, `DataLoader`)
   mit `@Component`.
3. Schreiben Sie eine Klasse `AnnotationConfigBeanContainer` – analog zum
   `XmlBeanContainer`. Sie bekommt eine Liste von Kandidaten-Klassen übergeben
   (das simuliert das "Scannen" eines Packages) und
   - prüft per Reflection mit `clazz.isAnnotationPresent(Component.class)`,
     welche davon eine Bean sind, und
   - registriert jede gefundene Bean über `defineBean(name, clazz)` im
     `BeanContainer` (den Bean-Namen können Sie z. B. aus dem einfachen
     Klassennamen ableiten).
4. Rufen Sie anschließend `refresh()` auf und holen Sie sich eine Bean mit
   `getBean(...)`, um zu zeigen, dass Instanziierung und
   Constructor-Injection wie beim XML-Container funktionieren.

**Bonus:** Geben Sie Ihrer `@Component`-Annotation ein Element
`String value() default ""`, mit dem man den Bean-Namen explizit setzen kann –
genau wie bei Springs `@Component("meinName")`.
