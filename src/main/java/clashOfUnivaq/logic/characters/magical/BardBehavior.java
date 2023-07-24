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

public class BardBehavior implements CharacterBehavior, Serializable {

    // Pattern Singleton
    private static BardBehavior instance;
    private BardBehavior(){}

    public static BardBehavior getInstance(){
        if(instance == null) instance = new BardBehavior();
        return instance;
    }

    transient Scanner scanner = new Scanner(System.in);

    /** Given two character, attacks both of them ignoring their armor. Costs 50 Mana */
    public void specialAttack(Character c, Player ally, User enemy){

        if(GameServices.checkBG(enemy)){
            System.out.println("\nL'avversario non ha nessuna unita' schierata in campo, verra' eseguito l'attacco base.");
            ActionService.attack(c, ally, enemy);
            return;
        }

        System.out.println("\nIl Bardo scatena un onda sonora in grado di penetrare l'armatura nemica.");
        System.out.println("Seleziona due unita' nemiche da colpire.");

        Positions pos1=null, pos2=null;

        do {
            System.out.println("\nInserisci la posizione del primo nemico.\n");
            enemy.getBg().bgInfo();
            String position1 = scanner.next().toUpperCase();
            System.out.println("\nInserisci la posizione del secondo nemico.\n");
            enemy.getBg().bgInfo();
            String position2 = scanner.next().toUpperCase();
            try {
                pos1 = Positions.valueOf(position1);
                pos2 = Positions.valueOf(position2);
            } catch (IllegalArgumentException e) {
                System.out.println("\nPosizioni selezionate non valide, riprova.");
            }
        } while (pos1==null && pos2==null);

        if(enemy.getBg().getMap().get(pos1)==null || enemy.getBg().getMap().get(pos2)==null){
            System.out.println("\nAlmeno una delle due posizioni selezionate e' vuota, riprova.");
            this.specialAttack(c, ally, enemy);
            return;
        }

        if (c.getResource()>=50){
            c.setResource(c.getResource()-50);
            enemy.getBg().getMap().get(pos1).setHp(enemy.getBg().getMap().get(pos1).getHp()-c.getDamage());
            enemy.getBg().getMap().get(pos2).setHp(enemy.getBg().getMap().get(pos2).getHp()-c.getDamage());
            System.out.println("\nIl bardo con la sua melodia crea un'ampia onda sonora, infliggendo ad entrambi i bersagli "+c.getDamage()+" danni!");
            GameServices.remainingDamage(enemy.getBg().getMap().get(pos1), enemy);
            GameServices.remainingDamage(enemy.getBg().getMap().get(pos2), enemy);
        }else{
            System.out.println("\nMana insufficiente, verra' eseguito l'attacco normale.");
            ActionService.attack(c, ally, enemy);
        }
    }

    /** Given two character, attacks both of them ignoring their armor. Costs 50 Mana */
    public void specialAttack(Character c, Computer ally, User enemy){

        if(GameServices.checkBG(enemy)){
            System.out.println("\nIl tuo campo di battaglia e' vuoto, il Computer attacchera' la tua torre");
            ActionService.attack(c, ally, enemy);
        }

        Positions pos1 = ComputerService.getTarget(enemy);
        Positions pos2 = ComputerService.getTarget(enemy);

        if(c.getResource()>=50){

            c.setResource(c.getResource()-50);
            if(enemy.getBg().getMap().get(pos1)!=null){
                enemy.getBg().getMap().get(pos1).setHp(enemy.getBg().getMap().get(pos1).getHp()-c.getDamage());
                System.out.println("\nIl bardo con la sua melodia crea un'ampia onda sonora, infliggendo alla tua unita' "+c.getDamage()+" danni!");
                GameServices.remainingDamage(enemy.getBg().getMap().get(pos1), enemy);
            }else System.out.println("\nIl Computer ha selezionato una posiziona vuota, attacco annullato.");
            
            if(enemy.getBg().getMap().get(pos2)!=null){
                enemy.getBg().getMap().get(pos2).setHp(enemy.getBg().getMap().get(pos2).getHp()-c.getDamage());
                System.out.println("\nIl bardo con la sua melodia crea un'ampia onda sonora, infliggendo alla tua unita' "+c.getDamage()+" danni!");
                GameServices.remainingDamage(enemy.getBg().getMap().get(pos2), enemy);
            }else System.out.println("\nIl Computer ha selezionato una posiziona vuota, attacco annullato.");

        }else{
            System.out.println("\nMana insufficiente, verra' eseguito l'attacco normale.");
            ActionService.attack(c, ally, enemy);
        }
    }
    
}