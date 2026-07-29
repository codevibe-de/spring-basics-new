# Übungen zum Kapitel "077 - Spring Boot"

In diesem Kapitel migrieren wir die bestehende Pizza-Anwendung auf **Spring Boot**. Bisher haben wir sämtliche
Infrastruktur *von Hand* verdrahtet: die Erzeugung des `ApplicationContext`, das Hochziehen eines `DispatcherServlet`
über den `AppInitializer`, die embedded `DataSource`, die komplette Thymeleaf-View-Auflösung sowie das Einlesen der
Properties. Genau diesen handgeschriebenen "Boilerplate" nimmt uns Spring Boot über **Starter-Dependencies** und
**Auto-Konfiguration** ab.

Das Ziel dieser Übung ist es daher weniger, *neuen* Code zu schreiben, sondern vor allem **bestehenden Code wieder zu
löschen** und durch Boot-Bordmittel zu ersetzen. Achten Sie beim Aufräumen darauf, was jeweils *automatisch* passiert,
sobald der entsprechende Starter auf dem Classpath liegt.

> **Hinweis:** Wir konzentrieren uns bewusst auf einige **Kern-Themen** der Migration (Einstiegspunkt, Web-Layer,
> DataSource, Runner/Properties). Das im nächsten Kapitel folgende REST-Thema klammern wir hier noch aus — der
> vorhandene Thymeleaf-Controller bleibt als anschaulicher Kontrast erhalten.

## Setup (bereits vorbereitet)

Folgendes wurde für Sie **bereits erledigt** — Sie müssen an den Build-Skripten nichts mehr ändern:

- Die Build-Skripte (`pom.xml` / `build.gradle`) wurden auf Spring Boot umgestellt:
  - Maven nutzt jetzt `spring-boot-starter-parent` als Parent-POM, Gradle das `org.springframework.boot`- und das
    `io.spring.dependency-management`-Plugin.
  - Die einzelnen `spring-*`-Abhängigkeiten und deren Versionen wurden durch **Starter** ersetzt:
    `spring-boot-starter-web`, `spring-boot-starter-thymeleaf`, `spring-boot-starter-jdbc` sowie (für Tests)
    `spring-boot-starter-test`. Die Versionen verwaltet nun das Boot-BOM — explizite `<version>`-Angaben entfallen.
  - Das Packaging wurde von `war` auf `jar` umgestellt; die `jakarta.servlet-api` (Scope `provided`),
    `failOnMissingWebXml` sowie der explizite `-parameters`-Compiler-Schalter werden nicht mehr benötigt
    (Boot bringt all das mit).

Ab hier sind **Sie** dran. Arbeiten Sie die Aufgaben am besten der Reihe nach ab und starten Sie nach jedem Schritt
die Anwendung bzw. die Tests, um zu sehen, was Boot Ihnen bereits abgenommen hat.

## a) Einstiegspunkt: `@SpringBootApplication`

Machen Sie aus `PizzaApp` eine echte Spring-Boot-Anwendung:

➡️ Ersetzen Sie an der Klasse die Kombination aus `@Configuration` + `@ComponentScan` durch die eine
Annotation `@SpringBootApplication` (sie enthält beides — plus `@EnableAutoConfiguration`).

➡️ Ersetzen Sie in `main(...)` das manuelle Erzeugen des `AnnotationConfigApplicationContext` durch

```java
SpringApplication.run(PizzaApp.class, args);
```

➡️ Löschen Sie die Klasse `AppInitializer`. Ihren Job — einen `DispatcherServlet` samt eingebettetem Servlet-Container
hochzuziehen — übernimmt jetzt die Auto-Konfiguration von `spring-boot-starter-web` (embedded Tomcat auf Port `8080`).

## b) `DataSource` per Auto-Konfiguration

➡️ Löschen Sie die Klasse `EmbeddedDataSourceConfig`.

Sobald H2 auf dem Classpath liegt (bringt `spring-boot-starter-jdbc` in Kombination mit der H2-Dependency mit),
konfiguriert Boot automatisch eine embedded In-Memory-`DataSource`. Zusätzlich führt Boot beim Start ein im
Classpath-Root liegendes `schema.sql` (und optional `data.sql`) automatisch aus — genau das, was wir vorher von Hand
über den `EmbeddedDatabaseBuilder` gemacht haben.

> **Tipp:** Bleiben Sie skeptisch und schauen Sie beim Start ins Log — Boot meldet dort die angelegte H2-`DataSource`
> und die Ausführung von `schema.sql`.

## c) Web-Layer verschlanken

Die Klasse `WebConfig` ist der größte Brocken — und lässt sich zum größten Teil **löschen**:

➡️ Entfernen Sie die Annotation `@EnableWebMvc`.

> ⚠️ **Wichtig / klassische Falle:** `@EnableWebMvc` schaltet Boots MVC-Auto-Konfiguration **ab**. Solange die
> Annotation stehen bleibt, werden weder die Thymeleaf-Views noch die statischen Ressourcen automatisch konfiguriert.

➡️ Löschen Sie die drei Thymeleaf-Beans (`templateResolver`, `templateEngine`, `viewResolver`). Der Starter
`spring-boot-starter-thymeleaf` konfiguriert die komplette Kette `TemplateResolver → TemplateEngine → ViewResolver`
selbst — und erwartet die Templates standardmäßig genau dort, wo sie schon liegen: `src/main/resources/templates/`
(Präfix `classpath:/templates/`, Suffix `.html`).

➡️ Löschen Sie die Methode `addResourceHandlers(...)`. Boot serviert statische Inhalte aus
`src/main/resources/static/` automatisch — die `bootstrap-5.3.3.css` ist damit ohne weiteres Zutun erreichbar.

➡️ **Übrig bleibt** einzig die `LocaleResolver`-Bean (feste Locale `Locale.GERMANY`, damit das Euro-Zeichen korrekt
gerendert wird). Für diese eine Bean gibt es keine passende Auto-Konfiguration — sie bleibt also erhalten. Überlegen
Sie, ob die verbleibende Konfigurationsklasse noch `WebConfig` heißen oder z. B. in eine schlanke
`LocaleConfig` umbenannt werden sollte.

Rufen Sie nach diesem Schritt `http://localhost:8080/web/products/P-10` auf — die Seite sollte unverändert erscheinen,
obwohl fast die gesamte Web-Konfiguration verschwunden ist.

## d) Runner & Properties auf Boot-Bordmittel umstellen

Auch die selbstgebaute Runner-Mechanik und das manuelle Property-Handling ersetzen wir durch Boot-Standards.

### Runner

Bisher gibt es ein **eigenes** Interface `pizza.util.CommandLineRunner`, das in `PizzaApp` über einen
`@EventListener(ContextRefreshedEvent.class)` aufgerufen wird; die CLI-Argumente werden dafür umständlich über das
Record `CliArgs` als Bean in den Context "geparkt".

➡️ Ersetzen Sie das eigene Interface durch Boots `org.springframework.boot.CommandLineRunner`. Boot ruft alle Beans
dieses Typs nach dem Start automatisch auf und übergibt dabei die CLI-Argumente. Damit werden **überflüssig** und
können gelöscht werden:

- der `@EventListener(ContextRefreshedEvent.class)` in `PizzaApp`,
- das Record `pizza.util.CliArgs`,
- die Bedingung `pizza.util.CliArgsPresentCondition`.

➡️ `DataLoadRunner` und `LogicRunner` implementieren nun einfach `org.springframework.boot.CommandLineRunner`. Die
`@Order`-Annotationen können Sie zur Steuerung der Reihenfolge beibehalten.

> **Tipp:** `LogicRunner` gibt ASCII-Tabellen aus und gibt eine Bestellung auf — das wollen wir in den Tests **nicht**.
> Früher verhinderte das die `CliArgsPresentCondition`. Ein Boot-idiomatischer Ersatz ist ein Profil: annotieren Sie
> `LogicRunner` mit `@Profile("!test")` und aktivieren Sie in den Tests das Profil `test`
> (`@ActiveProfiles("test")`).

### Properties

➡️ Wandeln Sie das Record `pizza.order.OrderProperties` in eine `@ConfigurationProperties`-Klasse um
(`prefix = "app.order"`) und aktivieren Sie sie per `@EnableConfigurationProperties(OrderProperties.class)` (oder
`@ConfigurationPropertiesScan`). Boot bindet die Werte aus `application.properties` dann automatisch an die
Record-Komponenten (`delivery-time-in-minutes`, `discount-days`, `discount-rate`).

➡️ Löschen Sie daraufhin die Klasse `PropertiesConfig` samt der `@Bean`-Methode und dem `@PropertySource`. Boot lädt
`application.properties` ohnehin automatisch — ein explizites `@PropertySource` ist nicht mehr nötig.

## e) Tests anpassen

Die vorhandenen Service-Tests (`CustomerServiceTest`, `OrderServiceTest`) booten den Context bisher mit
`@SpringJUnitConfig(PizzaApp.class)`. Dieser Mechanismus ruft die (Boot-)`CommandLineRunner` **nicht** auf — die
Testdaten würden also fehlen und `OrderServiceTest` scheitern.

➡️ Stellen Sie die Tests auf `@SpringBootTest` um (ggf. mit `@ActiveProfiles("test")`, siehe Tipp in Aufgabe d).
`@SpringBootTest` startet den Context wie die echte Anwendung über `SpringApplication` und führt damit auch den
`DataLoadRunner` aus — die Testdaten stehen wieder zur Verfügung, während `LogicRunner` dank `@Profile("!test")`
stumm bleibt.

## Verifikation

Wenn alles migriert ist:

```bash
./mvnw spring-boot:run      # oder: ./gradlew bootRun
```

- Die Anwendung startet mit **eingebettetem Tomcat** auf Port `8080` (kein externer Server, kein `AppInitializer`
  mehr).
- `http://localhost:8080/web/products/P-10` zeigt weiterhin die Produktseite.
- `./mvnw test` (bzw. `./gradlew test`) läuft grün.

Vergleichen Sie zum Abschluss, **wie viel Code Sie gelöscht** haben — das ist die eigentliche Botschaft dieses
Kapitels: Spring Boot ersetzt Ihren handgeschriebenen Infrastruktur-Code durch Konvention und Auto-Konfiguration.
