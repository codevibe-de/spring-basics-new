package pizza.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Aspekt, der <b>vor</b> jedem Aufruf einer Methode von {@code ProductService} eine Nachricht auf
 * {@link System#out} schreibt (z.&nbsp;B. {@code "About to execute getProduct(P-10)"}).
 */
@Aspect
@Component
public class TraceAspect {

    @Before("execution(* pizza.product.ProductService.*(..))")
    public void trace(JoinPoint joinPoint) {
        var argsString = StringUtils.arrayToDelimitedString(joinPoint.getArgs(), ", ");
        System.out.printf("About to execute %s(%s)%n", joinPoint.getSignature().getName(), argsString);
    }

}
