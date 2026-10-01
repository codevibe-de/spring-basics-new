package pizza.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * Implementiert das AOP-Alliance Interface {@link MethodInterceptor}, um die
 * Ausführungsdauer der umwickelten Methode messen und ausgeben zu können.
 */
public class ProfilingInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long startMillis = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            long durationMillis = System.currentTimeMillis() - startMillis;
            System.out.printf("Execution of %s() took %d ms%n",
                    invocation.getMethod().getName(), durationMillis);
        }
    }

}
