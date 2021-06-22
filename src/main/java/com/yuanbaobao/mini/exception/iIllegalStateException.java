package com.codebaobao.exception;

public class iIllegalStateException extends RuntimeException {

    private static final long serialVersionUID = 0;

    public iIllegalStateException() {
        super();
    }

    public iIllegalStateException(final String message) {
        super(message);
    }

    public iIllegalStateException(final Throwable cause) {
        super(cause);
    }

    public iIllegalStateException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
