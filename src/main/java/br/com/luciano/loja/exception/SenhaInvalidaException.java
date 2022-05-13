package br.com.luciano.loja.exception;

public class SenhaInvalidaException extends  RuntimeException{

    public SenhaInvalidaException(String message){
        super(message);
    }
}
