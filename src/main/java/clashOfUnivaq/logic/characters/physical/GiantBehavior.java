package clashOfUnivaq.logic.characters.physical;

import java.io.Serializable;

import clashOfUnivaq.datamodel.cards.characters.Character;
import clashOfUnivaq.datamodel.users.Computer;
import clashOfUnivaq.datamodel.users.Player;
import clashOfUnivaq.datamodel.users.User;
import clashOfUnivaq.logic.characters.CharacterBehavior;

public class GiantBehavior implements CharacterBehavior, Serializable {

    // Pattern Singleton
    private static GiantBehavior instance;
    private GiantBehavior(){}

    public static GiantBehavior getInstance(){
        if(instance == null) instance = new GiantBehavior();
        return instance;
    }

    /** Increase his armor of 25. Costs 25 Stamina */
    public void specialAttack(Character c, Player ally, User enemy){               
        if(c.getResource()>=25){

            c.setResource(c.getResource()-25);
            c.setArmor(c.getArmor()+5);
            System.out.println("\nIl gigante incrementa leggermente la sua difesa!");
            
        }else System.out.println("\nIl Gigante prova ad aumentare la sua difesa ma fallisce...");    
    }

    /** Increase his armor of 25. Costs 25 Stamina */
    public void specialAttack(Character c, Computer ally, User enemy){
        if(c.getResource()>=25){

            c.setResource(c.getResource()-25);
            c.setArmor(c.getArmor()+5);
            System.out.println("\nIl gigante incrementa leggermente la sua difesa!");
            
        }else System.out.println("\nIl Gigante prova ad aumentare la sua difesa ma fallisce..."); 
    }
    
}