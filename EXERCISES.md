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

## Übung 2: Eigene `@Component`-Annotation und ein Mini-Component-Scanner

Wir bauen – zu Fuß und stark vereinfacht – nach, was Spring in Lektion 025 mit
`@Component` und `@ComponentScan` liefern wird: Klassen werden mit einer eigenen
Annotation markiert und per Reflection eingesammelt.

### Teil 2.a: Die `@Component`-Annotation

1. Erstellen Sie eine eigene Annotation `@Component` (nach dem Muster aus
   Übung 1: `@Retention(RUNTIME)`, `@Target(TYPE)`).
2. Geben Sie ihr ein Element `String value() default ""`, mit dem man einer
   Komponente optional einen expliziten Namen geben kann – analog zu Springs
   `@Component("meinName")`.
3. Markieren Sie eine vorhandene Klasse mit der Annotation, z. B. den `CustomerService`. Sie können auch das `name`
   -Attribut setzen, z. B. `@Component("custSrv")`).

### Teil 2.b: Der `ComponentScanner`

Ergänzen Sie die Klasse `ComponentScanner` mit einer Methode
`scan(Class<?>... candidateClasses)`, die eine gegebene Menge an Klassen
untersucht (das simuliert das "Scannen" eines Packages) und für jede mit
`@Component` markierte Klasse deren Namen ausgibt.

1. Prüfen Sie per Reflection mit `clazz.isAnnotationPresent(Component.class)`,
   welche der übergebenen Klassen eine Komponente ist.
2. Ermitteln Sie den Komponenten-Namen: Ist `value()` gesetzt, verwenden Sie
   diesen, sonst leiten Sie ihn aus dem einfachen Klassennamen ab
   (`ProductService` → `productService`).
3. Geben Sie die gefundenen Namen auf der Konsole aus. Nicht annotierte Klassen
   werden ignoriert.

**Bonus:** Geben Sie zusätzlich den Wert des `value`-Attributs bzw. den
abgeleiteten Namen aus und lassen Sie den Scanner die Liste aller gefundenen
Namen zurückgeben.
