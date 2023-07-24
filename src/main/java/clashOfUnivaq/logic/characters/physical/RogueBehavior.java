package clashOfUnivaq.logic.characters.physical;

import java.io.Serializable;

import clashOfUnivaq.datamodel.cards.characters.Character;
import clashOfUnivaq.datamodel.users.Computer;
import clashOfUnivaq.datamodel.users.Player;
import clashOfUnivaq.datamodel.users.User;
import clashOfUnivaq.logic.characters.ActionService;
import clashOfUnivaq.logic.characters.CharacterBehavior;

public class RogueBehavior implements CharacterBehavior, Serializable {

    // Pattern Singleton
    private static RogueBehavior instance;
    private RogueBehavior(){}
    
    public static RogueBehavior getInstance(){
        if(instance == null) instance = new RogueBehavior();
        return instance;
    }

    /** Attacks the tower directly. Costs 25 Stamina */
    public void specialAttack(Character c, Player ally, User enemy){      
        if(c.getResource()>=25){

            c.setResource(c.getResource()-25);
            enemy.getTower().setHp(enemy.getTower().getHp()-c.getDamage());
            System.out.println("\nIl ladro passa inosservato attraverso le truppe nemiche e infligge "+c.getDamage()+" danni alla torre nemica!");
            System.out.println(enemy.getTower().getInfo());
        }else{
            System.out.println("\nStamina insufficiente, verra' eseguito l'attacco normale.");
            ActionService.attack(c, ally, enemy);
        }
    }

    /** Attacks the tower directly. Costs 25 Stamina */
    public void specialAttack(Character c, Computer ally, User enemy){
        if(c.getResource()>=25){

            c.setResource(c.getResource()-25);
            enemy.getTower().setHp(enemy.getTower().getHp()-c.getDamage());
            System.out.println("\nIl ladro passa inosservato attraverso le truppe nemiche e infligge "+c.getDamage()+" danni alla torre nemica!");
            System.out.println(enemy.getTower().getInfo());
        }else{
            System.out.println("\nStamina insufficiente, verra' eseguito l'attacco normale.");
            ActionService.attack(c, ally, enemy);
        }
    }
    
}