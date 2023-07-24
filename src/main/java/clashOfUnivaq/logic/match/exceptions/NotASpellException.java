package clashOfUnivaq.logic.match.exceptions;

public class NotASpellException extends Exception {

    public NotASpellException(){
        System.err.println("\nPuoi lanciare esclusivamente carte incantesimi, riprova.");
    }
    
}