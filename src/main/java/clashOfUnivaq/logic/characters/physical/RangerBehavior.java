package clashOfUnivaq.logic.characters.physical;

import java.io.Serializable;

import clashOfUnivaq.datamodel.cards.characters.Character;
import clashOfUnivaq.datamodel.users.Computer;
import clashOfUnivaq.datamodel.users.Player;
import clashOfUnivaq.datamodel.users.User;
import clashOfUnivaq.logic.characters.ActionService;
import clashOfUnivaq.logic.characters.CharacterBehavior;

public class RangerBehavior implements CharacterBehavior, Serializable {

    // Pattern Singleton
    private static RangerBehavior instance;
    private RangerBehavior(){}

    public static RangerBehavior getInstance(){
        if(instance == null) instance = new RangerBehavior();
        return instance;
    }

    /** Given two characters, attacks both of them. Costs 25 Stamina */
    public void specialAttack(Character c, Player ally, User enemy){   
        if(c.getResource()>=25){

            c.setResource(c.getResource()-25);
            System.out.println("\nIl ranger scocca due freccie simultaneamente. Seleziona due bersagli da colpire.");

            ActionService.attack(c, ally, enemy);
            ActionService.attack(c, ally, enemy);
            
        }else{
            System.out.println("\nStamina insufficiente, verra' eseguito l'attacco normale.");
            ActionService.attack(c, ally, enemy);
        } 
    }

    /** Given two characters, attacks both of them. Costs 25 Stamina */
    public void specialAttack(Character c, Computer ally, User enemy){
        if(c.getResource()>=25){

            c.setResource(c.getResource()-25);
            System.out.println("\nIl ranger scocca due freccie simultaneamente. Seleziona due bersagli da colpire.");

            ActionService.attack(c, ally, enemy);
            ActionService.attack(c, ally, enemy);
            
        }else{
            System.out.println("\nStamina insufficiente, verra' eseguito l'attacco normale.");
            ActionService.attack(c, ally, enemy);
        } 
    }
    
}