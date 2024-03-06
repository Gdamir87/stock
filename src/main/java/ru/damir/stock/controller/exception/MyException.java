package ru.damir.stock.controller.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MyException extends Exception{

    public MyException(String message) {
        super(message);
    }

}
