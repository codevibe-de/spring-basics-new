# Ablauf Bean Container Demo-Programmierung

Im Branch `011-beanContainer-demo` sind die Stellen im Code mit `TODO Demo <Schritt>` markiert.
In IntelliJ listet sie das Tool-Fenster „TODO“ (View → Tool Windows → TODO) der Reihe nach.

1. Ergänzung der Implementierung von `defineBean()` (`TODO Demo 1`)
2. Implementierung `getBean(String name)` (`TODO Demo 2`)
3. Instanziierung und Nutzung BeanContainer in PizzaApp,
   liefert noch null für z. B. HashMapProductRepository (`TODO Demo 3 und 5`)
4. erste Version von `refresh()` mit Loop über BeanDefs und
   `def.getType().newInstance()` (`TODO Demo 4`)
5. Nutzung in PizzaApp gibt nun erste Instanz aus, aber fehlt halt noch viel
6. Implementierung `getBean(Class<T> requiredType)`, Herausforderung
   Polymorphie, wie machen wir z. B. Lookup von Typ `ProductRepository`? Nutzung und Erklärung `resolveBeanName`
   (`TODO Demo 6`)
7. Refactoring von `newInstance()`, hin zu Nutzung des `Constructor` und dessen
   Types, die wir nun ja mit der neuen `getBean` auflösen können. Implementiert in
   neuer `createBean` Methode unter Nutzung von `resolveBeanNames()` (`TODO Demo 7`)
8. Demo mit weiterer Bean-Definition von `ProductService` -- wird dann nach dem
   `ProductRepo` erzeugt (noch definierte Erzeugungsreihenfolge), deswegen ist auch
   List an BeanDefinitions vorteilhaft (statt Set oder Map)
9. dann Definitionsreihenfolge umdrehen und nun gibts Exception
10. letztendlich Reihenfolge angehen, dazu `createBeanDependencyGraph()` aufrufen
    und abarbeiten (`TODO Demo 10`)
