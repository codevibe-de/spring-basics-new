# Übungen zu 023 - Crashkurs Annotationen

## Übung 1: Eigene Annotation erstellen und per Reflection auswerten

Erstellen Sie eine eigene Annotation `@Info` und markieren Sie damit eine Klasse.

1. Definieren Sie die Annotation `@Info` mit den Elementen `author` (String) und `version` (int, Default-Wert `1`).
2. Sorgen Sie mit der Meta-Annotation `@Retention(RetentionPolicy.RUNTIME)` dafür, dass die Annotation zur Laufzeit
   verfügbar ist, und beschränken Sie das Ziel mit `@Target(ElementType.TYPE)` auf Klassen.
3. Versehen Sie eine beliebige Klasse mit der Annotation, z. B. `@Info(author = "Max Mustermann", version = 2)`.
4. Lesen Sie die Annotation zur Laufzeit per Reflection aus (`clazz.getAnnotation(Info.class)`) und geben Sie `author`
   und `version` auf der Konsole aus.

## Übung 2: Eigene `@Component`-Annotation und ein Mini-Component-Scanner

Wir bauen – zu Fuß und stark vereinfacht – nach, was Spring in der folgenden Lektion 025 mit
`@Component` und `@ComponentScan` liefern wird: Klassen werden mit einer eigenen Annotation markiert und per Reflection
eingesammelt.

Annotation und Scanner leben im Package `componentscan` (`componentscan.Component`
und `componentscan.ComponentScanner`). 

Als Abnahme-Kriterium dient der bereits vorhandene (aber noch **deaktivierte**) Test
`componentscan.ComponentScannerTest` – sobald er grün ist, sind Teil 2.a und 2.b gelöst.

### Teil 2.a: Die Bean-Klassen mit `@Component` markieren

Die Annotation `componentscan.Component` ist bereits vorgegeben (mit einem Element
`String value() default ""` für einen optionalen expliziten Namen – analog zu Springs `@Component("meinName")`).

Markieren Sie zwei vorhandenen Bean-Klassen damit, sodass der Test sie findet:

- `HashMapProductRepository` mit **explizitem** Namen: `@Component("productRepository")`
- `ProductService` mit einer schlichten `@Component` (der Name wird dann aus dem Klassennamen abgeleitet)

### Teil 2.b: Der `ComponentScanner`

Ergänzen Sie die Klasse `ComponentScanner` mit einer Methode
`scan(Class<?>... candidateClasses)`, die eine gegebene Menge an Klassen untersucht (das simuliert das "Scannen" eines
Packages) und für jede mit `@Component` markierte Klasse deren Namen ermittelt, ausgibt und in einer Liste zurückgibt.

1. Prüfen Sie per Reflection mit `clazz.isAnnotationPresent(Component.class)`, welche der übergebenen Klassen eine
   Komponente ist.
2. Ermitteln Sie den Komponenten-Namen: Ist `value()` gesetzt, verwenden Sie diesen, sonst leiten Sie ihn aus dem
   einfachen Klassennamen ab (`ProductService` → `productService`).
3. Geben Sie die gefundenen Namen auf der Konsole aus. Nicht annotierte Klassen werden ignoriert.
