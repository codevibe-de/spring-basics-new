// Folie 100 – Listen von Beans
package com.example.annotationconfig.collections;

import com.example.annotationconfig.naming.MyBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {
    public MyService(List<MyBean> listOfMyBeans) {
        // ...
    }
}
