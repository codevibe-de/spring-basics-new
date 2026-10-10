package pizza.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Aspekt, der die Ausführungsdauer der Methoden von {@code ProductService} misst und ausgibt.
 */
@Aspect
@Component
public class ProfilingAspect {

    @Around("execution(* pizza.product.ProductService.*(..))")
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
