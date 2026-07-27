package com.example.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
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