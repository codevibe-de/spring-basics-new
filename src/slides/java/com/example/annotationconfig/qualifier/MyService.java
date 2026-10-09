// Folie 97 – Beans qualifizieren
package com.example.annotationconfig.qualifier;

import com.example.annotationconfig.naming.MyBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private final MyBean myBean;

    public MyService(@Qualifier("myBean42") MyBean myBean) {
        this.myBean = myBean;
    }
}
