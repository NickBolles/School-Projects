package lab5;

public class NumberNotFoundException extends Exception {

public NumberNotFoundException() {

super("Number not found in array");

}

public NumberNotFoundException(String message) {

super(message);

}

}
