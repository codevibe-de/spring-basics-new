package pizza;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * The last remnant of the former hand-written {@code WebConfig}.
 *
 * <p>Spring Boot auto-configures Spring MVC, Thymeleaf view resolution and static
 * resource handling — so {@code @EnableWebMvc}, the {@code TemplateResolver → TemplateEngine
 * → ViewResolver} chain and {@code addResourceHandlers(...)} are all gone.
 *
 * <p>Only the locale needs an explicit bean: Boot's default resolves the locale from the
 * request's {@code Accept-Language} header, but the product template relies on
 * {@code #numbers.formatCurrency(...)} rendering the Euro symbol, so we pin it to Germany.
 * Declaring a {@link LocaleResolver} bean makes Boot back off its own default.
 */
@Configuration
public class LocaleConfig {

    @Bean
    public LocaleResolver localeResolver() {
        var localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.GERMANY);
        return localeResolver;
    }
}
