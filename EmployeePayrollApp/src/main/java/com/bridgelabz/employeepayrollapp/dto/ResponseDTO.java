package com.bridgelabz.employeepayrollapp.dto;

/**
 * ResponseDTO is a generic wrapper class used to send structured HTTP responses
 * back to the client with a consistent format.
 *
 * It wraps any data payload along with a human-readable message,
 * making API responses cleaner and easier to consume on the frontend.
 *
 * @author Manas
 * @version 1.0
 * @param <T> the type of the data payload
 */
public class ResponseDTO<T> {

    /** Human-readable message describing the result of the operation */
    public String message;

    /** The actual data payload returned to the client */
    public T data;

    /** Constructs a ResponseDTO with a message and data payload */
    public ResponseDTO(String message, T data) {
        this.message = message;
        this.data = data;
    }

    /** Returns a readable string representation of the ResponseDTO */
    @Override
    public String toString() {
        return "ResponseDTO{message='" + message + "', data=" + data + "}";
    }
}
