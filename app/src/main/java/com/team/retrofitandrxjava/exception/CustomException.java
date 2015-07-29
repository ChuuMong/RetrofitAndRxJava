package com.team.retrofitandrxjava.exception;

/**
 * Created by leejonghun on 15. 7. 28.
 */
public class CustomException extends RuntimeException {

    public CustomException() {
    }

    public CustomException(String detailMessage) {
        super(detailMessage);
    }

    public CustomException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public CustomException(Throwable throwable) {
        super(throwable);
    }
}
