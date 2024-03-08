package ru.damir.stock.controller.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MyException extends RuntimeException{

    public MyException(String message) {
        super(message);
    }

}
