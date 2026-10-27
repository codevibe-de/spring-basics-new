# Übungen zum Kapitel "075 - Web Application"

In diesem Kapitel bauen wir eine erste Web-Oberfläche für unsere Pizza-Anwendung. Ein Produkt soll unter einer URL
wie `http://localhost:8080/web/products/P-10` als HTML-Seite dargestellt werden.

> **Hinweis:** Wir nutzen hier noch *klassisches* Spring MVC — also **ohne** Spring Boot (das kommt erst im nächsten
> Kapitel). Es gibt daher noch keinen automatisch gestarteten, eingebetteten Webserver und keine Auto-Konfiguration.
> Die dafür nötige Infrastruktur ist bereits vorbereitet (siehe unten).

## Setup

Folgendes wurde für Sie **bereits vorbereitet**:

- Die Build-Skripte (`pom.xml` / `build.gradle`) wurden um `thymeleaf` und `thymeleaf-spring6` erweitert
  (`spring-webmvc` und die `jakarta.servlet-api` waren bereits vorhanden).
- Die Klasse `AppInitializer` bootet über die Servlet-3.0-API (ohne `web.xml`) einen `DispatcherServlet` um den
  bestehenden `PizzaApp`-Context herum.
- Die Klasse `WebConfig` aktiviert Spring MVC (`@EnableWebMvc`), konfiguriert die Thymeleaf-View-Auflösung
  (`TemplateResolver → TemplateEngine → ViewResolver`), stellt statische Ressourcen bereit und setzt die Locale
  auf `Locale.GERMANY` (damit das Euro-Zeichen korrekt gerendert wird).
- Das Verzeichnis mit statischem Inhalt ist angelegt: `src/main/resources/static` (enthält u. a.
  `bootstrap-5.3.3.css`).
- Das Template `src/main/resources/templates/products/single-product.html` ist als Grundgerüst vorbereitet
  (HTML-Struktur inkl. Bootstrap). Die eigentliche Datenanbindung tragen Sie in Aufgabe b) selbst nach.

## Anwendung starten

`PizzaApp.main()` startet weiterhin nur den Spring-Kontext, aber keinen Webserver. Damit der `DispatcherServlet` Requests
bekommt, muss ein Servlet-Container die Anwendung laden und dabei `AppInitializer` aufrufen. Spring Framework 7 setzt
Servlet 6.1 voraus, also **Tomcat 11** oder neuer; mit Tomcat 10 startet die Anwendung nicht.

Es gibt drei Wege. Geprüft wird jeweils mit dem Produkt "P-10" (Pizza Margarita).

### Variante 1: Eingebetteter Tomcat (in jeder IDE)

Das ist Aufgabe c) unten: Abhängigkeit `tomcat-embed-core` ergänzen und eine `main`-Methode schreiben, die einen
Tomcat startet. Gestartet wird wie jede andere Java-Klasse, in IntelliJ über den grünen Pfeil neben `main` bzw. eine
Run Configuration vom Typ **Application**.

➡️ URL: `http://localhost:8080/web/products/P-10`

### Variante 2: IntelliJ IDEA mit lokalem Tomcat

Voraussetzung: ein entpackter Tomcat 11 (Download unter https://tomcat.apache.org/download-11.cgi, "Core") und eine
IntelliJ-Installation mit dem Plugin "Tomcat and TomEE". Je nach Lizenz fehlt es; dann Variante 1 oder 3 nehmen.

1. *Run → Edit Configurations… → + → Tomcat Server → Local*
2. Bei *Application server* über *Configure…* das Tomcat-Verzeichnis auswählen
3. Reiter *Deployment*: *+ → Artifact… → pizza-app:war exploded*
4. Im selben Reiter *Application context* auf `/` setzen. Sonst hängt IntelliJ einen aus dem Artefaktnamen gebildeten
   Pfad vor alle URLs.
5. Run Configuration starten; IntelliJ baut das Artefakt, startet Tomcat und deployt die Anwendung

➡️ URL: `http://localhost:8080/web/products/P-10`

### Variante 3: WAR in einen Tomcat deployen

Das ist der klassische Weg ohne IDE, wie im Betrieb.

1. WAR bauen: `mvn package` erzeugt `target/pizza-app-075.war`, `./gradlew war` erzeugt `build/libs/pizza-app-075.war`
2. Die Datei in das Verzeichnis `webapps/` des Tomcat kopieren
3. Tomcat starten mit `bin/startup.sh` (Windows: `bin\startup.bat`), beenden mit `bin/shutdown.sh`. Dafür muss
   `JAVA_HOME` auf ein JDK 17 oder neuer zeigen.

Tomcat nimmt den Dateinamen des WAR als Kontextpfad, er steht daher in jeder URL:

➡️ URL: `http://localhost:8080/pizza-app-075/web/products/P-10`

> **Hinweis:** Links im Template deshalb immer mit Thymeleafs `@{…}` schreiben, z. B.
> `th:href="@{/bootstrap-5.3.3.css}"`. Thymeleaf setzt den Kontextpfad davor; ein festes `href="/bootstrap-5.3.3.css"`
> würde unter `/pizza-app-075` ins Leere zeigen.

## a) ProductWebController

Schreiben Sie einen Controller namens `ProductWebController` (z. B. im Paket `pizza.product`), der ein Produkt lädt
und dem Model hinzufügt. 

Zum Laden nutzen Sie die `ProductService` Bean.

Das Produkt können Sie über zwei Möglichkeiten identifizieren lassen:

- Query-Parameter (wie auf den Folien zu sehen)
- Path-Variable (dies ist die REST-konforme Variante) — dies geht über die Annotation `@PathVariable` und eine
  gemappte URL wie z. B. `/web/products/{productId}`

Der Controller soll die View mit Namen `"products/single-product"` rendern lassen.

## b) Template

Das HTML-Template `src/main/resources/templates/products/single-product.html` ist bereits als Grundgerüst
vorbereitet — inklusive Bootstrap-Einbindung und Layout.

Ihre Aufgabe ist die **Datenanbindung**: Ersetzen Sie die markierten Platzhalter (`<!-- TODO -->`) durch
Thymeleaf-Ausdrücke, die die Attribute des Produkts aus dem Model ziehen.

Der Key, unter dem Sie das Produkt dem Model hinzugefügt haben, ist der Einstiegspunkt zu den Daten im Template —
nutzen Sie diesen in den Thymeleaf-Ausdrücken, z. B. `th:text="${product.getName()}"`. 

Für den Preis bietet sich `th:text="${#numbers.formatCurrency(product.getPrice())}"` an.

## c) (Optional) Anwendung starten mit eingebettetem Tomcat

In dieser Ausbaustufe gibt es noch keinen laufenden Webserver — der `DispatcherServlet` aus `AppInitializer` wird
erst von einem Servlet-Container aufgerufen. Um die Anwendung ohne externen Tomcat starten und im Browser ansehen zu
können, betten Sie einen Tomcat programmatisch ein.

➡️ Ergänzen Sie in den Build-Skripten die Abhängigkeit auf einen eingebetteten Tomcat, z. B.:

- Maven:
  ```xml
  <dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-core</artifactId>
    <version>11.0.24</version>
  </dependency>
  ```
- Gradle:
  ```
  implementation 'org.apache.tomcat.embed:tomcat-embed-core:11.0.24'
  ```

➡️ Schreiben Sie eine kleine `main`-Methode (z. B. in einer neuen Klasse `WebLauncher` oder in `PizzaApp`), die einen
`Tomcat` auf Port `8080` startet. Wichtig: Der Container muss die `AppInitializer`-Klasse über den
`ServletContainerInitializer`-Mechanismus finden. Ein Grundgerüst:

```java
var tomcat = new Tomcat();
tomcat.setPort(8080);
tomcat.getConnector(); // erzwingt das Anlegen des Standard-Connectors

// Kontext ohne reales docBase-Verzeichnis
Context context = tomcat.addContext("", new java.io.File(".").getAbsolutePath());

// AppInitializer über Springs ServletContainerInitializer registrieren, damit
// dessen onStartup(...) den DispatcherServlet verdrahtet
context.addServletContainerInitializer(
        new org.springframework.web.SpringServletContainerInitializer(),
        java.util.Set.of(AppInitializer.class));

tomcat.start();
tomcat.getServer().await();
```

➡️ Starten Sie diese `main`-Methode und rufen Sie anschließend
`http://localhost:8080/web/products/P-10` auf, um das Produkt mit der ID "P-10" (Pizza Margarita) zu sehen.
