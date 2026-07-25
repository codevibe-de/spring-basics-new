package pizza;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Kleine, eigenständige Anwendung zum Experimentieren mit der Spring Expression Language.
 * Der {@link StandardEvaluationContext} erhält über den {@link BeanFactoryResolver} Zugriff auf
 * die Beans des Containers, sodass Ausdrücke wie {@code @productService} Bean-Referenzen auflösen
 * können.
 */
public class SpelParserApp {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(PizzaApp.class)) {
            // Beispieldaten laden, damit es Produkte zum Auswerten gibt
            context.getBean("sample", DataLoader.class).run();

            // Parser und Evaluation-Context vorbereiten
            var parser = new SpelExpressionParser();
            var evaluationContext = new StandardEvaluationContext();
            evaluationContext.setBeanResolver(new BeanFactoryResolver(context));

            // Ausdruck auswerten:
            Object value = parser
                    // TODO Ersetzen Sie diesen Ausdruck durch einen SpEL-Ausdruck, der die Namen
                    //      aller Produkte liefert -- nutzen Sie eine Bean-Referenz (@productService),
                    //      Property-Zugriff (.allProducts) und eine Collection-Projection (.![...]).
                    // Experimentieren Sie danach mit weiteren Ausdrücken, z.B.:
                    //   @productService.allProducts.size()                               -> Anzahl
                    //   @productService.allProducts.?[price.doubleValue() < 7.0].![name] -> Selection + Projection
                    //   T(java.time.LocalDate).now()                                     -> statische Methode über Type-Reference
                    .parseExpression("23 + 42")
                    .getValue(evaluationContext);

            System.out.println(value);
        }
    }

}
