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

1. Neue Klasse `XmlConfigBeanContainer` anlegen, die von `BeanContainer` vererbt (da dessen Verhalten ergänzt wird)
2. Klassen `BeansElement` und `BeanElement` anlegen, welche die XML-Elemente der XML-Konfiguration repräsentieren (z.B.
   als nicht public top-level Klassen in `XmlConfigBeanContainer`)
3. Konstruktor von `XmlConfigBeanContainer` implementieren, der die XML-Datei einliest (`this.load(..)`), parst
   und die Bean-Definitionen in `BeanContainer` einträgt (über `defineBean(..)`)
