package clashOfUnivaq.logic.match.exceptions;

public class InvalidPositionException extends Exception {

    public InvalidPositionException(){
        System.err.println("\nPosizione gia' occupata, riprova.");
    }
    
}