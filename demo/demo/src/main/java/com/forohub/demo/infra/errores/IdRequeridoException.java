package com.forohub.demo.infra.errores;

public class IdRequeridoException extends RuntimeException {
    public IdRequeridoException(String mensaje) {
        super(mensaje);
    }
}
