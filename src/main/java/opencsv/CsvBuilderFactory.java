package opencsv;

public class CsvBuilderFactory {
    public static ICsvBuilder createCsvBuilder(){
        return new OpenCSVBuilder();
    }
}
