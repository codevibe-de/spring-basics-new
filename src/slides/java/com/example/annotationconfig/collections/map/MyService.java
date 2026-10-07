// Folie 101 – Map von Beans
package com.example.annotationconfig.collections.map;

import com.example.annotationconfig.naming.MyBean;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MyService {
    public MyService(Map<String, MyBean> myBeansByName) {
        // ...
    }
}
