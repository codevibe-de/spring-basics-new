package pizza;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Servlet 3.0+ programmatic bootstrap (no web.xml). The servlet container
 * discovers this via {@code jakarta.servlet.ServletContainerInitializer} and
 * calls {@link #onStartup} on deployment, wiring a {@link DispatcherServlet}
 * around the existing {@link PizzaApp} Spring context.
 */
public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        // Reuse the existing annotation-driven configuration (component scan,
        // data loaders, WebConfig) as the DispatcherServlet's application context.
        var context = new AnnotationConfigWebApplicationContext();
        context.register(PizzaApp.class);

        var dispatcher = new DispatcherServlet(context);
        ServletRegistration.Dynamic registration =
                servletContext.addServlet("dispatcher", dispatcher);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
