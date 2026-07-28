package demo;

public class ImportantDataReader {

    private String cache;

    public String read() {
        return this.cache;
    }

    void initCache() {
        System.out.println("Warming up cache...");
        this.cache = "abc-123";
    }

}
