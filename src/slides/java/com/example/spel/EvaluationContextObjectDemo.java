// Folie 141 – EvaluationContext - Objekt
package com.example.spel;

import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class EvaluationContextObjectDemo {

    public static void main(String[] args) {
        var expressionParser = new SpelExpressionParser();
        var powCalculator = new PowerOfCalculator();
        var context = new StandardEvaluationContext(powCalculator);
        var result = expressionParser
                .parseExpression("powerOf(2,8)")
                .getValue(context, Integer.class);

        System.out.println(result); // 256
    }
}
