package gr.codehub.exceptions;

public class ProhibittedAddressException extends Exception{
    private String description;
    private int errorCode;

    public ProhibittedAddressException(String description, int errorCode) {
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
        return "ProhibittedAddressException{" +
                "description='" + description + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }
}
