package clashOfUnivaq.logic.spells.damaging;

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

public class PoisonBehavior implements SpellBehavior, Serializable {

    // Pattern Singleton
    private static PoisonBehavior instance;
    private PoisonBehavior(){}

    public static PoisonBehavior getInstance(){
        if (instance == null) instance = new PoisonBehavior();
        return instance;
    }

    transient Scanner scanner = new Scanner(System.in);

    /** Deals damage to an enemy and reduces their armor */
    public void cast(Spell s, Player ally, User enemy){
        
        if(GameServices.checkBG(enemy)){
            System.out.println("\nIl campo di battaglia del nemico e' vuoto, non puoi lanciare l'incantesimo.");
            return;
        }

        System.out.println("\nSeleziona l'unita' avversaria da colpire.\n");
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
            System.out.println("\nPosiziona selezionata vuota, riprova.");
            this.cast(s, ally, enemy);
            return;
        }

        enemy.getBg().getMap().get(pos).setHp(enemy.getBg().getMap().get(pos).getHp()-s.getParam());
        enemy.getBg().getMap().get(pos).setArmor(enemy.getBg().getMap().get(pos).getArmor()-0.05);
        System.out.println("\nIl veleno corrode l'avversario infliggendo "+s.getParam()+" danni e riducendo la sua armatura.");
        GameServices.remainingDamage(enemy.getBg().getMap().get(pos), enemy);
    }

    /** Deals damage to an enemy and reduces their armor */
    public void cast(Spell s, Computer ally, User enemy){

        if(GameServices.checkBG(enemy)){
            System.out.println("Il tuo campo di battaglia e' vuoto, il Computer non puo lanciare l'incantesimo di "+s.getName());
        }else{
            Positions pos = ComputerService.getTarget(enemy);
            enemy.getBg().getMap().get(pos).setHp(enemy.getBg().getMap().get(pos).getHp()-s.getParam());
            enemy.getBg().getMap().get(pos).setArmor(enemy.getBg().getMap().get(pos).getArmor()-0.05);
            System.out.println("Il Computer avvelena il tuo "+enemy.getBg().getMap().get(pos).getName()+" infliggendo "+s.getParam()+" danni e riducendo la sua armatura.");
            GameServices.remainingDamage(enemy.getBg().getMap().get(pos), enemy);
        }
    }
    
}