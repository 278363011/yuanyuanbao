package com.codebaobao.exception;

public class SelectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 0;

    public SelectNotFoundException() {
        super();
    }

    public SelectNotFoundException(final String message) {
        super(message);
    }

    public SelectNotFoundException(final Throwable cause) {
        super(cause);
    }

    public SelectNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
