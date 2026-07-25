package pizza.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Implementiert Springs {@link MethodBeforeAdvice}, um <b>vor</b> jedem Methodenaufruf
 * eine Nachricht auf {@link System#out} zu schreiben (z.&nbsp;B. {@code "About to execute getProduct(P-10)"}).
 */
public class TraceBeforeMethodAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) {
        // TODO Geben Sie den Namen der aufgerufenen Methode (und optional die Argumente)
        //      auf der Konsole aus, bevor die eigentliche Methode ausgeführt wird.
    }

}
