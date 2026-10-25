// Folie 242 – CommandLineRunner
package com.example.boot.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ArgsRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        // Start mit: --verbose --name=Pizza input.csv
        var verbose = args.containsOption("verbose"); // true
        var name = args.getOptionValues("name"); // [Pizza]
        var files = args.getNonOptionArgs(); // [input.csv]
    }

}
