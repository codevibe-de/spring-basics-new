// Folie 212 – Wie kommt Spring in Tomcat?
package com.example.web.mvc;

import jakarta.servlet.ServletContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import pizza.PizzaApp;

public class AppInitializer
        implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        var ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(PizzaApp.class, WebConfig.class);

        var servlet = new DispatcherServlet(ctx);
        var reg = servletContext.addServlet("dispatcher", servlet);
        reg.setLoadOnStartup(1);
        reg.addMapping("/");
    }
}
