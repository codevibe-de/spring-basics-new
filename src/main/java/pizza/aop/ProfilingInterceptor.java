package pizza.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Implementiert das AOP-Alliance Interface {@link MethodInterceptor}, um die
 * Ausführungsdauer der umwickelten Methode messen und ausgeben zu können.
 */
public class ProfilingInterceptor {  // TODO: implements MethodInterceptor ergänzen (import ist bereits vorhanden)

    // TODO Die Zeitmess-Logik unten ist bereits fertig vorgegeben -- die Signatur der Methode muss
    //  aber noch angepasst werden, dann kann der Code durch Entfernung der Kommentare aktiviert werden.
    //  Denken Sie auch daran, die Methode mit @Override zu markieren.
    public void foo() {
//        long startMillis = System.currentTimeMillis();
//        try {
//            return invocation.proceed();
//        } finally {
//            long durationMillis = System.currentTimeMillis() - startMillis;
//            System.out.printf("Execution of %s() took %d ms%n",
//                    invocation.getMethod().getName(), durationMillis);
//        }
    }

}
