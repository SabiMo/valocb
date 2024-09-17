package fr.codebusters.valocb.exceptions;

/**
 * Exception levée lorsque le nombre de colonnes est incorrect.
 */
public class BadFileContentException extends Exception {

    /**
     * @param message Le message décrivant l'erreur.
     */
    public BadFileContentException(String message) {
        super(message);
    }
}
