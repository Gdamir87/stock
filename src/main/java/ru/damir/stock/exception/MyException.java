package ru.damir.stock.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MyException extends RuntimeException{

    public MyException(String message) {
        super(message);
    }

}
