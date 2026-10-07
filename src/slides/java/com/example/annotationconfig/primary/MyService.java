// Folie 96 – Primäre Beans
package com.example.annotationconfig.primary;

import com.example.annotationconfig.naming.MyBean;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private final MyBean myBean;

    public MyService(MyBean myBean) {
        this.myBean = myBean;
    }
}
