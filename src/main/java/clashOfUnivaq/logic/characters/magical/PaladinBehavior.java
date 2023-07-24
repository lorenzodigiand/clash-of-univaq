package clashOfUnivaq.logic.characters.magical;

import java.io.Serializable;

import clashOfUnivaq.datamodel.cards.characters.Character;
import clashOfUnivaq.datamodel.users.Computer;
import clashOfUnivaq.datamodel.users.Player;
import clashOfUnivaq.datamodel.users.User;
import clashOfUnivaq.logic.characters.CharacterBehavior;

public class PaladinBehavior implements CharacterBehavior, Serializable {

    // Pattern Singleton
    private static PaladinBehavior instance;
    private PaladinBehavior(){}

    public static PaladinBehavior getInstance(){
        if(instance == null) instance = new PaladinBehavior();
        return instance;
    }

    /** Heal himself of 50 hp and increase his Magical Damage by 10. Costs 50 Mana */
    public void specialAttack(Character c, Player ally, User enemy){    
        if(c.getResource()>=50){

            c.setResource(c.getResource()-50);
            c.setHp(c.getHp()+50);
            c.setDamage(c.getDamage()+10);  

            System.out.println("\nIl paladino infonde la sua spada e armatura con la luce del sole ottenendo attacco extra e punti vita bonus!");  
            
        }else System.out.println("\nC'e' brutto tempo e il paladino non riesce ad infondersi della luce del sole...");
    }

    /** Heal himself of 50 hp and increase his Magical Damage by 10. Costs 50 Mana */
    public void specialAttack(Character c, Computer ally, User enemy){
        if(c.getResource()>=50){

            c.setResource(c.getResource()-50);
            c.setHp(c.getHp()+50);
            c.setDamage(c.getDamage()+10);  

            System.out.println("\nIl paladino infonde la sua spada e armatura con la luce del sole ottenendo attacco extra e punti vita bonus!");  
            
        }else System.out.println("\nC'e' brutto tempo e il paladino non riesce ad infondersi della luce del sole...");
    }
    
}