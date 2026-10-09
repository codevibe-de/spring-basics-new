// Folie 79 – Konfigurationen zusammensetzen: @Import
package com.example.annotationconfig.importing;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pizza.customer.CustomerService;

@Configuration
@Import({ProductConfig.class, CustomerService.class})
public class AppConfig {
}
