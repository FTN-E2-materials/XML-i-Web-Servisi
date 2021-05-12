package rs.ac.uns.ftn.consumer.consumerservice.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {}

    public NotFoundException(String message) {
        super(message);
    }

}
