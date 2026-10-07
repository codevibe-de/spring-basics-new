// Folie 63 – Annotationen per Reflection auslesen
package com.example.annotations;

public class ReflectionDemo {

    public static void main(String[] args) throws Exception {
        var method = SomeClass.class.getMethod("someMethod");
        var benchmark = method.getAnnotation(Benchmark.class);
        System.out.println(benchmark.value());   // "schnell"

        var info = Person.class.getAnnotation(PersonInfo.class);
        System.out.println(info);                // null!
    }
}
