package clashOfUnivaq.logic.spells.damaging;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

import clashOfUnivaq.datamodel.cards.spells.Spell;
import clashOfUnivaq.datamodel.map.Positions;
import clashOfUnivaq.datamodel.users.Computer;
import clashOfUnivaq.datamodel.users.Player;
import clashOfUnivaq.datamodel.users.User;
import clashOfUnivaq.logic.spells.SpellBehavior;
import clashOfUnivaq.logic.users.GameServices;
import clashOfUnivaq.logic.users.computer.ComputerService;

public class ThunderBehavior implements SpellBehavior, Serializable {

    // Pattern Singleton
    private static ThunderBehavior instance;
    private ThunderBehavior(){}

    public static ThunderBehavior getInstance(){
        if (instance == null) instance = new ThunderBehavior();
        return  instance;
    }

    transient Scanner scanner = new Scanner(System.in);

    /** Throws a thunderwave to a tower or to an enemy */
    public void cast(Spell s, Player ally, User enemy){

        System.out.println("\nVuoi lanciare la saetta sulla torre o su un nemico?");
        System.out.println("1: Torre.");
        System.out.println("2: Nemico.");

        switch(scanner.next()){
            case "1":
            enemy.getTower().setHp(enemy.getTower().getHp()-s.getParam());
            System.out.println("\nLa saetta colpisce la torre avversaria infliggendo "+s.getParam()+" danni.");
            break;

            case "2":
            if(GameServices.checkBG(enemy)){
                System.out.println("\nIl campo di battaglia del nemico e' vuoto, prova a colpire la torre.");
                this.cast(s, ally, enemy);
                return;
            }
            
            System.out.println("\nSeleziona il nemico da colpire.\n");
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
                break;
            }

            enemy.getBg().getMap().get(pos).setHp(enemy.getBg().getMap().get(pos).getHp()-s.getParam());
            System.out.println("\nLa saetta colpisce il bersaglio infliggendo "+s.getParam()+" danni.");
            GameServices.remainingDamage(enemy.getBg().getMap().get(pos), enemy);
            break;

            default:
            System.out.println("\nBersaglio selezionato non valido, riprova.");
            this.cast(s, ally, enemy);
        }
    }

    /** Throws a thunderwave to a tower or to an enemy */
    public void cast(Spell s, Computer ally, User enemy){

        Random random = new Random();
        switch(random.nextInt(2)){
            case 0:
            enemy.getTower().setHp(enemy.getTower().getHp()-s.getParam());
            System.out.println("\nIl Computer scaglia una saetta infliggendo "+s.getParam()+" danni alla tua torre.");
            break;

            case 1:
            if(GameServices.checkBG(enemy)){
                System.out.println("\nIl tuo campo di battaglia e' vuoto, il Computer colpira' la tua torre.");
                enemy.getTower().setHp(enemy.getTower().getHp()-s.getParam());
                System.out.println("\nIl Computer scaglia una saetta infliggendo "+s.getParam()+" danni alla tua torre.");
            }else{
                Positions pos = ComputerService.getTarget(enemy);
                enemy.getBg().getMap().get(pos).setHp(enemy.getBg().getMap().get(pos).getHp()-s.getParam());
                System.out.println("\nIl Computer scaglia una saetta infliggendo "+s.getParam()+" danni al tuo "+enemy.getBg().getMap().get(pos).getName());
                GameServices.remainingDamage(enemy.getBg().getMap().get(pos), enemy);
            }
        }
    }
    
}