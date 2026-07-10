# Übungen zum Kapitel "060 - Konfiguration"

## a) OrderService konfigurierbar machen

Verändern Sie den `OrderService`, sodass die dort definierten Eigenschaften mittels Konfiguration
gesetzt werden können:

* erwartete Lieferzeit in Minuten
* die Wochentage, an denen der Rabatt gilt
* der Rabattsatz (in %)

Hinweis: Hierfür gibt es zwei Möglichkeiten (mittels `@Value` oder `@ConfigurationProperties`).
Bei letzterem Ansatz brauchen Sie die Felder der Klasse nicht mehr, da Sie ja dann diese über
eine neue `OrderProperties` Klasse injectet bekommen.

## b) Ausgabe der Konfiguration

Ergänzen Sie den `OrderService` um eine Methode, in der die Konfiguration via `System.out`
ausgegeben wird (damit wir sehen können, was gerade gilt).

Lassen Sie Spring diese Methode automatisch beim Start ausführen. Wie ging das nochmal ...? :)

Setzen Sie Werte für die Konfiguration des `OrderService` in der `application.properties` Datei.

Starten Sie nun die Anwendung und prüfen Sie die tatsächlich vorliegende Konfiguration --
wird der Wert aus der `application.properties` genutzt?

## c) Konfiguration von außen

Starten Sie Ihre Anwendung auf eine Art und Weise, dass nicht die Lieferzeit in Minuten
aus den `application.properties` genutzt wird, sondern von außen durch einen anderen Wert
überschrieben wird.

Hierfür können Sie eine Umgebungsvariable, ein VM System Property oder ein Programmargument nutzen.

Wird der erwartete Wert ausgegeben?

## d) Neuer CSV DataLoader

Es gibt einen neuen DataLoader, der die Daten aus einer CSV-Datei lädt. Dafür wird ein Spring Konzept
genutzt, das wir bisher noch nicht kennengelernt haben: Ressourcen.

Vorerst reicht es aber, wenn Sie ein Property mit folgendem Wert anlegen:

```properties
app.data-loader.csv.product-data=classpath:/products.csv
```

Lassen Sie den Wert dieses Properties mittels `@Value` der `productsResource` Instanzvariable zuweisen.

## e) optionaler DataLoader

Es gibt eine neue `DataLoadRunner` Bean, in die der `run()`-Aufruf des DataLoaders ausgelagert wurde (vorher war dies
Teil des LogicRunners)

Machen Sie die Ausführung der `DataLoadRunner`-Bean konfigurierbar (an/aus), z.B. mittels einer `@ConditionalOn...`
Annotation.

Alternativ können Sie sogar implementieren, dass über ein Property bestimmt werden kann, **welche** DataLoader Bean
ausgeführt werden soll (z.B. "none", "sample" oder der neue "csv").