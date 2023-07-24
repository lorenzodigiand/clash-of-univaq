package clashOfUnivaq.logic.characters.magical;

import java.io.Serializable;
import java.util.Scanner;

import clashOfUnivaq.datamodel.cards.characters.Character;
import clashOfUnivaq.datamodel.map.Positions;
import clashOfUnivaq.datamodel.users.Computer;
import clashOfUnivaq.datamodel.users.Player;
import clashOfUnivaq.datamodel.users.User;
import clashOfUnivaq.logic.characters.ActionService;
import clashOfUnivaq.logic.characters.CharacterBehavior;
import clashOfUnivaq.logic.users.GameServices;
import clashOfUnivaq.logic.users.computer.ComputerService;

public class ClericBehavior implements CharacterBehavior, Serializable {

    // Pattern Singleton
    private static ClericBehavior instance;
    private ClericBehavior(){}

    public static ClericBehavior getInstance(){
        if(instance == null) instance = new ClericBehavior();
        return instance;
    }

    transient Scanner scanner = new Scanner(System.in);
    
    /** Heals an ally. Costs 50 Mana */
    public void specialAttack(Character c, Player ally, User enemy){

        if(GameServices.checkBG(ally)){
            System.out.println("\nNon hai nessuna unita' schierata in campo da curare, verra' eseguito l'attacco base.");
            ActionService.attack(c, ally, enemy);
            return;
        }

        System.out.println("\nSeleziona un bersaglio alleato da curare.\n");
        ally.getBg().bgInfo();

        Positions pos=null;

        do {
            String position = scanner.next().toUpperCase();
            try {
                pos = Positions.valueOf(position);
            } catch (IllegalArgumentException e) {
                System.out.println("\nPosizione non valida, riprova.\n");
                ally.getBg().bgInfo();
            }
        } while (pos==null);

        if(ally.getBg().getMap().get(pos)==null){
            System.out.println("\nHai selezionato una posizione vuota, riprova.");
            this.specialAttack(c, ally, enemy);
            return;
        } 

        if (c.getResource()>=50){
            c.setResource(c.getResource()-50);
            ally.getBg().getMap().get(pos).setHp(ally.getBg().getMap().get(pos).getHp()+c.getDamage());
            System.out.println("\nIl chierico cura un suo alleato di "+c.getDamage()+" punti vita!");
        }else System.out.println("\nMana insufficiente per curare l'alleato...");  
    }

    /** Heals an ally. Costs 50 Mana */
    public void specialAttack(Character c, Computer ally, User enemy){

        if(GameServices.checkBG(ally)){
            ActionService.attack(c, ally, enemy);
            return;
        }

        Positions pos = ComputerService.getTarget(enemy);

        if(c.getResource()>=50){
            
            if(ally.getBg().getMap().get(pos)!=null){
                c.setResource(c.getResource()-50);
                ally.getBg().getMap().get(pos).setHp(ally.getBg().getMap().get(pos).getHp()+c.getDamage());
                System.out.println("\nIl chierico cura un suo alleato di "+c.getDamage()+" punti vita!");
            }else System.out.println("\nIl Computer ha selezionato una posizione vuota...");

        }else System.out.println("\nMana insufficiente per curare l'alleato...");    
    }
    
}