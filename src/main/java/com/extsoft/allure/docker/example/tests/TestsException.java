package com.extsoft.allure.docker.example.tests;

public class TestsException extends RuntimeException {

    public TestsException() {
        super();
    }

    public TestsException(String message) {
        super(message);
    }

    public TestsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestsException(Throwable cause) {
        super(cause);
    }
}
