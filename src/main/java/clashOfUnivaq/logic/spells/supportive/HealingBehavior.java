package clashOfUnivaq.logic.spells.supportive;

import java.io.Serializable;
import java.util.Scanner;

import clashOfUnivaq.datamodel.cards.spells.Spell;
import clashOfUnivaq.datamodel.map.Positions;
import clashOfUnivaq.datamodel.users.Computer;
import clashOfUnivaq.datamodel.users.Player;
import clashOfUnivaq.datamodel.users.User;
import clashOfUnivaq.logic.spells.SpellBehavior;
import clashOfUnivaq.logic.users.GameServices;
import clashOfUnivaq.logic.users.computer.ComputerService;

public class HealingBehavior implements SpellBehavior, Serializable {

    // Pattern Singleton
    private static HealingBehavior instance;
    private HealingBehavior(){}

    public static HealingBehavior getInstance(){
        if (instance == null) instance = new HealingBehavior();
        return instance;
    }

    transient Scanner scanner = new Scanner(System.in);

    /** Heals an ally */
    public void cast(Spell s, Player ally, User enemy){

        if(GameServices.checkBG(ally)){
            System.out.println("\nIl tuo campo di battaglia e' vuoto, non puoi lanciare l'incantesimo.");
            return;
        }

        System.out.println("\nSeleziona l'unita' alleata da curare.\n");
        ally.getBg().bgInfo();

        Positions pos = null;

        do {
            String position = scanner.next().toUpperCase();
            try {
                pos = Positions.valueOf(position);
            } catch (IllegalArgumentException e) {
                System.out.println("\nPosizione non valida, riprova.\n");
                enemy.getBg().bgInfo();
            }
        } while (pos==null);


        if(ally.getBg().getMap().get(pos)==null){
            System.out.println("\nPosiziona selezionata non valida, riprova.");
            this.cast(s, ally, enemy);
            return;
        }

        ally.getBg().getMap().get(pos).setHp(ally.getBg().getMap().get(pos).getHp()+s.getParam());
        System.out.println("\nLa cura rimargina le ferite dell'alleato curandolo di "+s.getParam()+" punti vita.");
    }

    /** Heals an ally */
    public void cast(Spell s, Computer ally, User enemy){

        if(GameServices.checkBG(ally)){
            System.out.println("Il campo di battaglia del Computer e' vuoto, non puo' lanciare l'incantesimo di "+s.getName());
        }else{
            Positions pos = ComputerService.getTarget(ally);
            ally.getBg().getMap().get(pos).setHp(ally.getBg().getMap().get(pos).getHp()+s.getParam());
            System.out.println("Il Computer cura il suo "+ally.getBg().getMap().get(pos).getName()+" di "+s.getParam()+" punti vita.");
        } 
    }

}