package fr.codebusters.valocb.exceptions;

/**
 * Exception levée lorsque les arguments passés à la méthode Main.java sont
 * incorrect.
 */
public class BadArgumentsException extends Exception {

    /**
     * @param message Le message décrivant l'erreur.
     */
    public BadArgumentsException(String message) {
        super(message);
    }
}
