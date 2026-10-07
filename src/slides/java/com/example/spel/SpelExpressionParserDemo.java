// Folie 140 – SpelExpressionParser
package com.example.spel;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpelExpressionParserDemo {

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        String helloWorld = (String) parser.parseExpression("'Hello World'").getValue();
        int maxValue = (Integer) parser.parseExpression("0x7FFFFFFF").getValue();
        int sumValue = (Integer) parser.parseExpression("2 + 4").getValue();

        System.out.println(helloWorld + " " + maxValue + " " + sumValue);
    }
}
