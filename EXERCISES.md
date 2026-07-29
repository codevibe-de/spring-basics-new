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

- Maven: `org.apache.tomcat.embed:tomcat-embed-core:10.1.57`
- Gradle: `implementation 'org.apache.tomcat.embed:tomcat-embed-core:10.1.57'`

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
