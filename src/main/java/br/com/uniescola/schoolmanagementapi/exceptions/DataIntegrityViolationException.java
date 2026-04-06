package br.com.uniescola.schoolmanagementapi.exceptions;

public class DataIntegrityViolationException extends RuntimeException {
    public DataIntegrityViolationException(String message) {
        super(message);
    }
}
