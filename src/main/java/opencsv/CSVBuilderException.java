package opencsv;

public class CSVBuilderException extends Exception{
    public enum ExceptionType{
        IPL_FILE_PROBLEM,UNABLE_TO_PARSE,NO_IPL_DATA,INCORRECT_PLAYER_TYPE;
    }
    public ExceptionType type;
    public CSVBuilderException(String message, ExceptionType type){
        super(message);
        this.type = type;
    }
}
