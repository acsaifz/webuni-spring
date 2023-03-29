package hu.webuni.airport.exception;

public class NonUniqueIataException extends RuntimeException {

    public NonUniqueIataException() {
    }

    public NonUniqueIataException(String iata) {
        super("Existing iata: " + iata);
    }
}
