package com.example.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Role {
    String value() default "";
}

@Role("Admin")
class User {
}

class Employee extends User {
}


