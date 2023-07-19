package pl.dziewulskil.AplikacjaRestowa.exception;

public class RestApplicationException extends RuntimeException {

    public RestApplicationException(String message) {
        super(message);
    }
}
