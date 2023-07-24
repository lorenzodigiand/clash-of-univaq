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

public class RageBehavior implements SpellBehavior, Serializable {

    // Pattern Singleton
    private static RageBehavior instance;
    private RageBehavior(){}

    public static RageBehavior getInstance(){
        if (instance == null) instance = new RageBehavior();
        return instance;
    }

    transient Scanner scanner = new Scanner(System.in);

    /** Boosts permanently an ally's attack */
    public void cast(Spell s, Player ally, User enemy){

        if(GameServices.checkBG(ally)){
            System.out.println("\nIl tuo campo di battaglia e' vuoto, non puoi lanciare l'incantesimo.");
            return;
        }
        
        System.out.println("\nSeleziona l'unità alleata da potenziare.\n");
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

        ally.getBg().getMap().get(pos).setDamage(ally.getBg().getMap().get(pos).getDamage()+s.getParam());
        System.out.println("\nIl tuo "+ally.getBg().getMap().get(pos).getName()+" ha il sangue agli occhi! Attacco potenziato di "+s.getParam()+".");
    }

    /** Boosts permanently an ally's attack */
    public void cast(Spell s, Computer ally, User enemy){

        if(GameServices.checkBG(ally)){
            System.out.println("Il campo di battaglia del Computer e' vuoto, non puo' lanciare l'incantesimo di "+s.getName());
        }else{
            Positions pos = ComputerService.getTarget(ally);
            ally.getBg().getMap().get(pos).setDamage(ally.getBg().getMap().get(pos).getDamage()+s.getParam());
            System.out.println("Il Computer potenzia il suo "+ally.getBg().getMap().get(pos).getName()+" incrementando il suo attacco di "+s.getParam()+".");
        }
    }
    
}