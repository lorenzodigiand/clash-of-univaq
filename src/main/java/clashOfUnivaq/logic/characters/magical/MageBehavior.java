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

public class MageBehavior implements CharacterBehavior, Serializable {

    // Pattern Singleton
    private static MageBehavior instance;
    private MageBehavior(){}

    public static MageBehavior getInstance(){
        if(instance == null) instance = new MageBehavior();
        return instance;
    }

    transient Scanner scanner = new Scanner(System.in);

    /** Attacks the given character ignoring his armor. Costs 50 Mana */
    public void specialAttack(Character c, Player ally, User enemy){

        if(GameServices.checkBG(enemy)){
            System.out.println("\nL'avversario non ha nessuna unita' schierata in campo, verra' eseguito l'attacco base.");
            ActionService.attack(c, ally, enemy);
            return;
        }

        System.out.println("\nIl mago canalizza la sua energia sferrando un missile magico in grado di ignorare l'armatura nemica.");
        System.out.println("Seleziona un unita' nemica da colpire.\n");
        enemy.getBg().bgInfo();

        Positions pos=null;

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
            System.out.println("\nHai selezionato una posizione vuota, riprova.");
            this.specialAttack(c, ally, enemy);
            return;
        } 

        if (c.getResource()>=50){
            c.setResource(c.getResource()-50);
            enemy.getBg().getMap().get(pos).setHp(enemy.getBg().getMap().get(pos).getHp()-(c.getDamage()+15));
            System.out.println("\nIl mago lancia un attacco magico che riesce ad ignorare l'armatura del nemico, infliggendo "+(c.getDamage()+15)+" danni.");
            GameServices.remainingDamage(enemy.getBg().getMap().get(pos), enemy);
        }else{
            System.out.println("\nMana insufficiente, verra' eseguito l'attacco normale.");
            ActionService.attack(c, ally, enemy);
        }
    }

    /** Attacks the given character ignoring his armor. Costs 50 Mana */
    public void specialAttack(Character c, Computer ally, User enemy){

        if(GameServices.checkBG(enemy)){
            System.out.println("\nIl tuo campo di battaglia e' vuoto, il Computer attacchera' la tua torre");
            ActionService.attack(c, ally, enemy);
        }

        Positions pos = ComputerService.getTarget(enemy);

        if(c.getResource()>=100){
            
            if(enemy.getBg().getMap().get(pos)!=null){
                c.setResource(c.getResource()-50);
                enemy.getBg().getMap().get(pos).setHp(enemy.getBg().getMap().get(pos).getHp()-(c.getDamage()+15));
                System.out.println("\nIl mago lancia un attacco magico che riesce ad ignorare l'armatura del nemico, infliggendo "+(c.getDamage()+15)+" danni.");
                GameServices.remainingDamage(enemy.getBg().getMap().get(pos), enemy);
            }else System.out.println("\nIl Computer ha selezionato una posizione vuota...");

        }else{
            System.out.println("\nMana insufficiente, verra' eseguito l'attacco normale.");
            ActionService.attack(c, ally, enemy);
        }
    }
    
}