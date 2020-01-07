package com.odilonjk.petshopcustomer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IncompleteCustomerException extends Exception {

    public IncompleteCustomerException(String message) {
        super(message);
    }

}
