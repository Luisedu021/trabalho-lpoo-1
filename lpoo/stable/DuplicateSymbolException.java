package lpoo.stable;

/**
 *
 * @author Luís Eduardo Lopes dos Santos
 * Otávio Ferreira Augusto
 * Guilherme Escobar
 */
public class DuplicateSymbolException extends Exception {
    
    public DuplicateSymbolException() {
        super("Erro semântico: símbolo duplicado encontrado.");
    }

    public DuplicateSymbolException(String message) {
        super(message);
    }
} // DuplicateSymbolException
