package pizza.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Aspekt, der die Ausführungsdauer der Methoden von {@code ProductService} misst und ausgibt.
 */
public class ProfilingAspect {  // TODO: als Aspekt und als Bean markieren

    // TODO Die Zeitmessung ist bereits fertig. Machen Sie die Methode zu einem Around-Advice, dessen
    //  Pointcut-Expression alle Methoden von pizza.product.ProductService erfasst.
    public Object profile(ProceedingJoinPoint joinPoint) throws Throwable {
        long startMillis = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long durationMillis = System.currentTimeMillis() - startMillis;
            System.out.printf("Execution of %s() took %d ms%n",
                    joinPoint.getSignature().getName(), durationMillis);
        }
    }

}
