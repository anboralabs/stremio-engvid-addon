package co.anbora.labs.engvid.exception;

public class UntrustedSSLException extends RuntimeException {
    public UntrustedSSLException(Exception e) {
        super(e);
    }
}
