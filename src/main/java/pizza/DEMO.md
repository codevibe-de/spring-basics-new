# Ablauf der Demo zur Umstellung auf XML-Config

1. In der `PizzaApp` legen Instanz vom ClassPathXmlApplicationContext anlegen
2. Typ von `beanContainer` ändert sich entsprechend
3. Da `ClassPathXmlApplicationContext` bzw. `ConfigurableApplicationContext` ein `Closable` ist, kann man auch try with
   resource nehmen:
    ```java
            try (ClassPathXmlApplicationContext beanContainer = new ClassPathXmlApplicationContext("/beans.xml")) {
            }
    ```
4. Die beans.xml Datei braucht nun noch Xml-Schema Deklaration, kann man gut von hier nehmen:
   https://docs.spring.io/spring-framework/reference/core/appendix/xsd-schemas.html
5. Start der App zeigt aber noch Fehler -- manche Klassen brauchen Konstruktor-Args, daher Ergänzung
   um `autowire="constructor"` in entsprechenden `<bean>` Elementen