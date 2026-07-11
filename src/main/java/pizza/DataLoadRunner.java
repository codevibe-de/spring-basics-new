package pizza;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pizza.util.CommandLineRunner;

import java.util.Map;

/**
 * Performs loading of sample data after the context has started up.
 */
@Component
@Order(1)
public class DataLoadRunner implements CommandLineRunner {

    private final DataLoader dataLoader;

    public DataLoadRunner(
            Map<String, DataLoader> dataLoaders,
            @Value("${app.data-loader:none}") String selected
    ) {
        this.dataLoader = dataLoaders.get(selected);
        if (this.dataLoader == null) {
            throw new IllegalArgumentException("No DataLoader bean for name: " + selected);
        }
    }

    @Override
    public void run(String... args) {
        this.dataLoader.run();
    }
}
