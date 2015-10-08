package com.hmwk3;

/**
 * Created by Nicholas on 3/12/2015.
 */
public class EmptyStackException extends RuntimeException {
    public EmptyStackException(String message) {
        super(message);
    }
    public EmptyStackException() {
        super();
    }
}
