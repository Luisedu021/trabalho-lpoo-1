package lpoo.stable;

/**
 * * @author // put your name(s) here
 */
public class DuplicateSymbolException extends Exception {
    
    public DuplicateSymbolException() {
        super("Erro semântico: símbolo duplicado encontrado.");
    }

    public DuplicateSymbolException(String message) {
        super(message);
    }
} // DuplicateSymbolException
