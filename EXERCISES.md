# Übungen zu Kapitel "045 JDBC"

In diesem Kapitel persistieren wir Produkte über JDBC in einer H2-Datenbank.

Anders als man erwarten könnte, brauchen wir dafür **keinen extern gestarteten Datenbank-Server**: Die Klasse
`EmbeddedDataSourceConfig` stellt eine eingebettete (In-Memory) H2-Datenbank als `DataSource`-Bean bereit und
initialisiert deren Schema beim Hochfahren automatisch aus `src/main/resources/schema.sql`.

## a) Die eingebettete DataSource nutzen

Schauen Sie sich die Klasse `EmbeddedDataSourceConfig` an. Die `@Bean` Methode `dataSource()` baut über den
`EmbeddedDatabaseBuilder` eine In-Memory-H2-Datenbank auf und führt beim Start das Skript `schema.sql` aus (legt
u. a. die Tabelle `products` an).

Aktuell nutzt die Anwendung noch das `HashMapProductRepository`. Wir wollen stattdessen die JDBC-Variante verwenden,
die die soeben beschriebene `DataSource` injiziert bekommt.

➡️ Machen Sie `JdbcProductRepository` zur aktiven Spring-Bean: Annotieren Sie die Klasse mit `@Component` und
entfernen Sie im Gegenzug das `@Component` an `HashMapProductRepository` (es darf nur *eine* `ProductRepository`-Bean
im Context geben).

➡️ Starten Sie die Anwendung. Die Produkte werden nun über JDBC aus der H2-Datenbank geladen.

> **Optional / Bonus:** Wer stattdessen mit einer *extern* laufenden H2-Datenbank arbeiten möchte, findet im
> Projekt-Root die Skripte `start-h2-server.sh` / `start-h2-server.bat`, die einen H2-TCP-Server starten
> (JDBC-URL `jdbc:h2:tcp://localhost:9092/./pizzadb`, Web-Konsole unter `http://localhost:8082`). Dafür müsste man
> eine eigene `DataSource` definieren, die auf diese URL zeigt (statt der eingebetteten aus `EmbeddedDataSourceConfig`).

## b) Nutzung JdbcTemplate

Die Klasse `JdbcProductRepository` verwendet aktuell "nacktes" JDBC (`Connection`, `PreparedStatement`, `ResultSet`
inkl. manuellem Exception-Handling) — das ist viel Boilerplate. Wir wollen stattdessen das Konstrukt der Spring
`JdbcTemplate` Klasse nutzen.

➡️ Schreiben Sie die bestehende Klasse `JdbcProductRepository` um, sodass diese Klasse nun das `JdbcTemplate` nutzt.
Eine Instanz des Templates legen Sie sich in der Repository Klasse unter Nutzung der übergebenen `DataSource` an.

Zur Refaktorierung der einzelnen Repository-Methoden bietet sich Folgendes an:

- für `save()` nutzt man `jdbcTemplate.update()`
- für `existsById()` nutzt man `jdbcTemplate.queryForObject()`
- für `findAll()` nutzt man `jdbcTemplate.query()` und erstellt (z.B. als innere Klasse)
  eine Implementierung des `RowMapper` Interfaces
- für `findById()` nutzt man `jdbcTemplate.queryForObject()` und den soeben erstellten `RowMapper`
