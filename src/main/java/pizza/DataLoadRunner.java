package pizza;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pizza.util.CommandLineRunner;

/**
 * Performs loading of sample data after the context has started up.
 */
@Component
@Order(1)
public class DataLoadRunner implements CommandLineRunner {

    private final DataLoader dataLoader;

    public DataLoadRunner(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @Override
    public void run(String... args) {
        this.dataLoader.run();
    }
}
