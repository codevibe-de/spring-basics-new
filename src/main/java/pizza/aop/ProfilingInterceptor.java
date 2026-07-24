package pizza.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Übung 027 a) -- Profiling-Aspekt.
 * <p>
 * Implementiert das AOP-Alliance Interface {@link MethodInterceptor}, um Code <b>vor und nach</b>
 * einem Methodenaufruf ausführen zu können ("Umwicklung" des Aufrufs). Damit lässt sich die
 * Ausführungsdauer der umwickelten Methode messen und ausgeben.
 */
public class ProfilingInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // TODO 027 a): Messen Sie die Zeit vor und nach dem Aufruf und geben Sie die Dauer aus.
        //              Der eigentliche (umwickelte) Aufruf erfolgt über invocation.proceed().
        return invocation.proceed();
    }

}
