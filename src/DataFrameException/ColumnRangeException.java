package DataFrameException;

public class ColumnRangeException extends Exception {
    String message;
    public ColumnRangeException(String message){
        super(message);
    this.message = message;}
    @Override
    public String getMessage() {
        return this.message;
    }
}
