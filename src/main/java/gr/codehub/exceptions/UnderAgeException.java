package gr.codehub.exceptions;

public class UnderAgeException extends Exception{
    private String description;
    private int errorCode;

    public UnderAgeException(String description, int errorCode) {
        super();
        this.description = description;
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return description;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return "UnderAgeException{" +
                "description='" + description + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }
}
