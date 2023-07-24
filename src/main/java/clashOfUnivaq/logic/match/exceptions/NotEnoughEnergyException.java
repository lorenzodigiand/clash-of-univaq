package clashOfUnivaq.logic.match.exceptions;

public class NotEnoughEnergyException extends Exception {

    public NotEnoughEnergyException(){
        System.err.println("\nNon possiedi abbastanza energia, scegliere un altra carta.");
    }
    
}