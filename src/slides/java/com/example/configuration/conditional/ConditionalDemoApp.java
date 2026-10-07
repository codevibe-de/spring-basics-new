// Folie „Bedingte Bean-Definitionen“ (030) – Demo: Die Bean existiert nur, wenn app.data-loader=csv gesetzt ist,
// z. B. per -Dapp.data-loader=csv oder Umgebungsvariable APP_DATA_LOADER=csv.
package com.example.configuration.conditional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConditionalDemoApp {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(CsvDataLoader.class)) {
            System.out.println("CsvDataLoader vorhanden: "
                    + (context.getBeanNamesForType(CsvDataLoader.class).length > 0));
        }
    }
}
