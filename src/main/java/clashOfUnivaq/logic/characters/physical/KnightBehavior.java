package clashOfUnivaq.logic.characters.physical;

import java.io.Serializable;

import clashOfUnivaq.datamodel.cards.characters.Character;
import clashOfUnivaq.datamodel.users.Computer;
import clashOfUnivaq.datamodel.users.Player;
import clashOfUnivaq.datamodel.users.User;
import clashOfUnivaq.logic.characters.ActionService;
import clashOfUnivaq.logic.characters.CharacterBehavior;

public class KnightBehavior implements CharacterBehavior, Serializable {

    // Pattern Singleton
    private static KnightBehavior instance;
    private KnightBehavior(){}

    public static KnightBehavior getInstance(){
        if(instance == null) instance = new KnightBehavior();
        return instance;
    }

    /** Increase Physical Damage by 25 for one attack. Costs 25 Stamina */
    public void specialAttack(Character c, Player ally, User enemy){    
        if(c.getResource()>=25){

            c.setResource(c.getResource()-25);
            System.out.println("\nIl cavaliere impugna strettamente la sua spada aumentando il suo attacco solo per questo turno, e' ora di attaccare!");

            c.setDamage(c.getDamage()+25);
            ActionService.attack(c, ally, enemy);
            c.setDamage(c.getDamage()-25);
        }else{
            System.out.println("\nStamina insufficiente, verra' eseguito l'attacco normale.");
            ActionService.attack(c, ally, enemy);
        }
    }

    /** Increase Physical Damage by 25 for one attack. Costs 25 Stamina */
    public void specialAttack(Character c, Computer ally, User enemy){
        if(c.getResource()>=25){

            c.setResource(c.getResource()-25);
            System.out.println("\nIl cavaliere impugna strettamente la sua spada infliggendo piu' danni solo per questo turno, e' ora di attaccare!");

            c.setDamage(c.getDamage()+25);
            ActionService.attack(c, ally, enemy);
            c.setDamage(c.getDamage()-25);
        }else{
            System.out.println("\nStamina insufficiente, verra' eseguito l'attacco normale.");
            ActionService.attack(c, ally, enemy);
        }
    }
    
}