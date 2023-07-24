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

public class FreezeBehavior implements SpellBehavior, Serializable {

    // Pattern Singleton
    private static FreezeBehavior instance;
    private FreezeBehavior(){}

    public static FreezeBehavior getInstance(){
        if (instance == null) instance = new FreezeBehavior();
        return instance;
    }

    transient Scanner scanner = new Scanner(System.in);

    /** Freeze an enemy for 1 turn */
    public void cast(Spell s, Player ally, User enemy){  
        
        if(GameServices.checkBG(enemy)){
            System.out.println("\nIl campo di battaglia del nemico e' vuoto, non puoi lanciare l'incantesimo.");
            return;
        }

        System.out.println("\nSeleziona l'unita' avversaria da congelare.\n");
        enemy.getBg().bgInfo();

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

        if(enemy.getBg().getMap().get(pos)==null){
            System.out.println("\nPosiziona selezionata non valida, riprova.");
            this.cast(s, ally, enemy);
            return;
        }

        enemy.getBg().getMap().get(pos).setFreeze(true);
        System.out.println("\nIl "+enemy.getBg().getMap().get(pos).getName()+" nemico e' stato congelato, non potra' attaccare il prossimo turno.");
    }
  
    /** Freeze an enemy for 1 turn */
    public void cast(Spell s, Computer ally, User enemy){   

        if(GameServices.checkBG(enemy)){
            System.out.println("Il tuo campo di battaglia e' vuoto, il Computer non puo lanciare l'incantesimo di "+s.getName());
        }else{
            Positions pos = ComputerService.getTarget(enemy);
            enemy.getBg().getMap().get(pos).setFreeze(true);
            System.out.println("Il Computer congela il tuo "+enemy.getBg().getMap().get(pos).getName()+", non potra' attaccare il prossimo turno.");
        }  
    }
    
}