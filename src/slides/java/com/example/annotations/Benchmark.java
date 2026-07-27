package com.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Benchmark {
    String value();
}


// Annotieren einer Methode mit eigener Annotation
class SomeClass {
    @Benchmark("schnell")
    public void someMethod() {
        // do something
    }
}