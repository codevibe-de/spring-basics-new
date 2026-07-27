package com.example.annotations;

public @interface PersonInfo {
    String name();
    int age();
    boolean active() default true;
}


@PersonInfo(
        name = "Enrico",
        age = 42
)
class Person {
}