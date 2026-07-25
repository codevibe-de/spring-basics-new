# Übungen zum Kapitel "030 - Konfiguration"

## a) OrderService konfigurierbar machen

Verändern Sie den `OrderService`, sodass die dort definierten Eigenschaften mittels Konfiguration
gesetzt werden können:

* erwartete Lieferzeit in Minuten
* die Wochentage, an denen der Rabatt gilt
* der Rabattsatz (in %)

Hinweis: Hierfür gibt es zwei Möglichkeiten:

1. mittels `@Value`-Annotation am jeweiligen Feld
2. oder über eine neue `OrderProperties` Klasse, welche die Werte aus dem `Environment` liest

Bei letzterem Ansatz brauchen Sie die Felder der Klasse nicht mehr, da Sie ja dann diese über
eine neue `OrderProperties` Klasse injectet bekommen.

In der nächsten Übung b) werden die Werte in der `application.properties` Datei gesetzt und
probeweise ausgegeben.

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

## d) optionaler DataLoader

Machen Sie die Ausführung des DataLoaders konfigurierbar (an/aus) - oder sogar, welche DataLoader Bean ausgeführt werden
soll (z.B. "none", "sample" or "csv").

Hierfür gibt es mehrere Wege, die wir kennengelernt haben.

> Die Ausführung der `DataLoader` Bean wurde in einen neuen `DataLoadRunner` ausgelagert, um die Konfiguration zu
> vereinfachen.

## e) Resource Inject

Eine Kleinigkeit kann noch verbessert werden:

Der CSV-DataLoader nutzt noch einen `ResourceLoader` um die Ressource der CSV-Datei zu bekommen. Eine Resource kann aber
auch direkt in eine Bean injectet werden. Ändern Sie den CSV-DataLoader so ab, dass die Ressource direkt injectet wird.

Dazu nutzen Sie die `@Value` Annotation und den `classpath:` Prefix.
