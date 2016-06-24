package com.extsoft.allure.docker.example;

public class TestClassRepositoryException extends RuntimeException {

    public TestClassRepositoryException() {
        super();
    }

    public TestClassRepositoryException(String message) {
        super(message);
    }

    public TestClassRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestClassRepositoryException(Throwable cause) {
        super(cause);
    }
}
