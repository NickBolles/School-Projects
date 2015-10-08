package com.hmwk3;

/**
 * Created by Nicholas on 3/12/2015.
 */
public class EmptyQueueException extends RuntimeException {
    public EmptyQueueException(String message) {
        super(message);
    }
    public EmptyQueueException() {
        super();
    }
}
