package pizza;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.Locale;

/**
 * Spring MVC configuration for the (non-Spring-Boot) web layer.
 *
 * <p>Because this project does <em>not</em> use Spring Boot yet, none of the web
 * infrastructure is auto-configured — we have to declare it ourselves:
 * <ul>
 *     <li>{@link EnableWebMvc} switches on annotation-driven controllers,
 *         message converters, etc.</li>
 *     <li>the three Thymeleaf beans below form the classic
 *         {@code TemplateResolver → TemplateEngine → ViewResolver} chain that
 *         turns a view name like {@code "products/single-product"} into a rendered
 *         HTML response from {@code src/main/resources/templates/…}.</li>
 *     <li>{@link #addResourceHandlers} serves the static assets (e.g. the
 *         Bootstrap stylesheet) from {@code src/main/resources/static/}.</li>
 *     <li>{@link #localeResolver()} fixes the locale to Germany so that
 *         Thymeleaf's {@code #numbers.formatCurrency(...)} prints the Euro symbol.</li>
 * </ul>
 *
 * Picked up automatically by {@link PizzaApp}'s {@code @ComponentScan}.
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    // --- Thymeleaf view resolution ---

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        var resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        var engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        engine.setEnableSpringELCompiler(true);
        return engine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        var resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    // --- Static resources (CSS, JS, images) ---

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

    // --- Locale (required for the Euro currency symbol in templates) ---

    @Bean
    public LocaleResolver localeResolver() {
        var localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.GERMANY);
        return localeResolver;
    }
}
