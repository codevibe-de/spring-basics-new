// Folie 84 – Constructor Injection - @Autowired
package com.example.annotationconfig.injection.constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MultiConstructorBean {

    private final SomeBean someBean;

    public MultiConstructorBean(SomeBean sb) {
        this.someBean = sb;
    }

    @Autowired
    public MultiConstructorBean(SomeBean sb, OtherBean o) {
        this(sb);
        // do something with OtherBean
    }
}

@Component
class SomeBean {
}

@Component
class OtherBean {
}
