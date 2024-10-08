package br.jus.tse.csadm.sessao;

public class WsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public WsException(String message, Throwable cause) {
        super(message, cause);
    }
}
