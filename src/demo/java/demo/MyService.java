package demo;

public class MyService {

    private ImportantDataReader importantDataReader;

    public String getImportantData() {
        var data = importantDataReader.read();
        data = data.toUpperCase();
        return data;
    }

}

