package com.codebaobao.exception;

public class UserNameAndPwdErrorException extends RuntimeException {

    private static final long serialVersionUID = 0;

    public UserNameAndPwdErrorException() {
        super();
    }

    public UserNameAndPwdErrorException(final String message) {
        super(message);
    }

    public UserNameAndPwdErrorException(final Throwable cause) {
        super(cause);
    }

    public UserNameAndPwdErrorException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
