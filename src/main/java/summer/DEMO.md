# Ablauf Bean Container Demo-Programmierung

Im Branch `011-beanContainer-demo` sind die Stellen im Code mit `TODO Demo <Schritt>` markiert.
In IntelliJ listet sie das Tool-Fenster „TODO“ (View → Tool Windows → TODO) der Reihe nach.

`defineBean()` ist vorgegeben: Es ersetzt eine bestehende Definition gleichen Namens.

1. Implementierung `getBean(String name)` (`TODO Demo 1`)
2. Instanziierung und Nutzung BeanContainer in PizzaApp,
   liefert noch null für z. B. HashMapProductRepository (`TODO Demo 2 und 4`)
3. erste Version von `refresh()` mit Loop über BeanDefs und
   `def.getType().newInstance()` (`TODO Demo 3`)
4. Nutzung in PizzaApp gibt nun erste Instanz aus, aber fehlt halt noch viel
5. Implementierung `getBean(Class<T> requiredType)`, Herausforderung
   Polymorphie, wie machen wir z. B. Lookup von Typ `ProductRepository`? Nutzung und Erklärung `resolveBeanName`
   (`TODO Demo 5`)
6. Refactoring von `newInstance()`, hin zu Nutzung des `Constructor` und dessen
   Types, die wir nun ja mit der neuen `getBean` auflösen können. Implementiert in
   neuer `createBean` Methode unter Nutzung von `resolveBeanNames()` (`TODO Demo 6`)
7. Demo mit weiterer Bean-Definition von `ProductService` -- wird dann nach dem
   `ProductRepo` erzeugt (noch definierte Erzeugungsreihenfolge), deswegen ist auch
   List an BeanDefinitions vorteilhaft (statt Set oder Map)
8. dann Definitionsreihenfolge umdrehen und nun gibts Exception
9. letztendlich Reihenfolge angehen, dazu `createBeanDependencyGraph()` aufrufen
   und abarbeiten (`TODO Demo 9`)

# 013 Ablauf der XmlConfigBeanContainer Demo-Programmierung

Im Branch `013-xmlBeanContainer-demo` sind die Stellen im Code mit `TODO Demo <Schritt>` markiert. Vorgegeben sind das
Gerüst von `XmlConfigBeanContainer` (erbt von `BeanContainer`, da dessen Verhalten nur ergänzt wird), die
`BeanDefinitionStoreException` und die JAXB-Dependencies in `pom.xml` bzw. `build.gradle`.

1. Klassen `BeansElement` und `BeanElement` anlegen, welche die XML-Elemente der XML-Konfiguration repräsentieren (als
   nicht public top-level Klassen in `XmlConfigBeanContainer`), mit `@XmlRootElement`, `@XmlElement` und
   `@XmlAttribute` (`TODO Demo 1`)
2. Konstruktor: erst `load(..)`, dann `refresh()` (`TODO Demo 2`)
3. `load(..)` implementieren: XML-Datei per `getResourceAsStream` öffnen, per JAXB parsen und die Bean-Definitionen
   über `defineBean(..)` eintragen; `Class.forName` macht aus dem Klassennamen das `Class`-Objekt (`TODO Demo 3`)
4. `@Disabled` in `XmlConfigBeanContainerTest` entfernen und die Tests laufen lassen, `init()` liest
   `src/test/resources/test-beans.xml` (`TODO Demo 4`)

Hinweis für die anschließende Übung: Innere statische Klassen brauchen beim Laden per Reflection ein `$` zwischen
äußerem und innerem Klassennamen (`pizza.DataLoader$Sample`).

# 014 Ablauf der Demo zur Umstellung auf Springs ClassPathXmlApplicationContext

Im Branch `014-xmlConfig-demo` sind die Stellen in `PizzaApp` und `src/main/resources/beans.xml` mit
`TODO Demo <Schritt>` markiert.

1. In der `PizzaApp` eine Instanz von `ClassPathXmlApplicationContext` anlegen (`TODO Demo 1 bis 3`)
2. Typ von `beanContainer` ändert sich entsprechend
3. Da `ClassPathXmlApplicationContext` bzw. `ConfigurableApplicationContext` ein `Closeable` ist, kann man auch try with
   resource nehmen:
    ```java
            try (ClassPathXmlApplicationContext beanContainer = new ClassPathXmlApplicationContext("/beans.xml")) {
            }
    ```
4. Die beans.xml Datei braucht nun noch Xml-Schema Deklaration, kann man gut von hier nehmen:
   https://docs.spring.io/spring-framework/reference/core/appendix/xsd-schemas.html (`TODO Demo 4`)
5. Start der App zeigt aber noch Fehler -- manche Klassen brauchen Konstruktor-Args, daher Ergänzung
   um `autowire="constructor"` in entsprechenden `<bean>` Elementen (`TODO Demo 5`)
