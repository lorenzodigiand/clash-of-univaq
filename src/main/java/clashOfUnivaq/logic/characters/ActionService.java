package clashOfUnivaq.logic.characters;

import java.io.Serializable;
import java.util.Scanner;

import clashOfUnivaq.datamodel.cards.characters.Character;
import clashOfUnivaq.datamodel.map.Positions;
import clashOfUnivaq.datamodel.users.Computer;
import clashOfUnivaq.datamodel.users.Player;
import clashOfUnivaq.datamodel.users.User;
import clashOfUnivaq.logic.users.GameServices;

public class ActionService implements Serializable {

    transient static Scanner scanner = new Scanner(System.in);

    /** Standard attack method for every character, the damage is determined by the enemy's armor, the method is static because the formula doesn't change between characters 
     *  @param c The character who's gonna attack
     *  @param ally The player to which the character belongs to
     *  @param enemy The user who's gonna get attacked
     *  @author Lorenzo Di Giandomenico
    */
    public static void attack(Character c, Player ally, User enemy){

        Positions posAlly = ally.getBg().getMap().inverse().get(c);

        if(enemy.getBg().getMap().get(posAlly)==null && enemy.getBg().getMap().get(Positions.getPosition(posAlly.ordinal()+1))==null){
            System.out.println("\nLa linea avversaria e' vuota, verra' attaccata direttamente la torre.");
            enemy.getTower().setHp(enemy.getTower().getHp()-c.getDamage());
            System.out.println("\nL'unita' alleata infligge "+c.getDamage()+" danni alla torre.");
            System.out.println(enemy.getTower().getInfo());
            return;
        }

        System.out.println("\nQuale unita' nemica desideri attaccare?\n");
        System.out.println(enemy.getBg().positionDetails(posAlly));
        System.out.println(enemy.getBg().positionDetails(Positions.getPosition(posAlly.ordinal()+1)));

        Positions posEnemy=null;

        do {
            String position=scanner.next().toUpperCase();
            try {
                posEnemy =Positions.valueOf(position);
            } catch (IllegalArgumentException e) {
                System.out.println("\nPosizione non valida, riprova.\n");
                System.out.println(enemy.getBg().positionDetails(posAlly));
                System.out.println(enemy.getBg().positionDetails(Positions.getPosition(posAlly.ordinal()+1)));
            }
        } while (posEnemy == null);

        if(posEnemy==posAlly){
            if(enemy.getBg().getMap().get(posEnemy)!=null){
                int dmg = (int)(c.getDamage()-c.getDamage()*enemy.getBg().getMap().get(posEnemy).getArmor());
                enemy.getBg().getMap().get(posEnemy).setHp(enemy.getBg().getMap().get(posEnemy).getHp()-dmg);
                System.out.printf("\nL'unita' alleata infligge "+dmg+" danni.\n");

                GameServices.remainingDamage(enemy.getBg().getMap().get(posEnemy), enemy);
                return;
            }else{
                System.out.println("\nLa Posizione selezionata e' vuota, riprova.");
                ActionService.attack(c, ally, enemy);
                return;
            }
        }else{
            if(posEnemy==Positions.getPosition(posAlly.ordinal()+1)){
                if(enemy.getBg().getMap().get(posEnemy)!=null && enemy.getBg().getMap().get(Positions.getPosition(posEnemy.ordinal()-1))==null){

                    int dmg = (int)(c.getDamage()-c.getDamage()*enemy.getBg().getMap().get(posEnemy).getArmor());
                    enemy.getBg().getMap().get(posEnemy).setHp(enemy.getBg().getMap().get(posEnemy).getHp()-dmg);
                    System.out.printf("\nL'unita' alleata infligge "+dmg+" danni.\n");

                    GameServices.remainingDamage(enemy.getBg().getMap().get(posEnemy), enemy);
                    return;
                }else{
                    if(enemy.getBg().getMap().get(posEnemy)==null){
                        System.out.println("\nLa Posizione selezionata e' vuota, riprova.");
                        ActionService.attack(c, ally, enemy);
                        return;
                    }else{
                        System.out.println("\nL'unita' davanti al bersaglio che vuoi colpire e' ancora in piedi, riprova.");
                        ActionService.attack(c, ally, enemy);
                        return;
                    }
                }
            }else{
                System.out.println("\nNon puoi attaccare all'infuori della linea del personaggio, riprova.");
                ActionService.attack(c, ally, enemy);
                return;
            }
        }
    }

    /** Standard attack method for every character, the damage is determined by the enemy's armor, the method is static because the formula doesn't change between characters 
     *  @param c The character who's gonna attack
     *  @param ally The computer to which the character belongs to
     *  @param enemy The user who's gonna get attacked
     *  @author Lorenzo Di Giandomenico
    */
    public static void attack(Character c, Computer ally, User enemy){

        Positions posAlly = ally.getBg().getMap().inverse().get(c);

        if(enemy.getBg().getMap().get(posAlly)==null && enemy.getBg().getMap().get(Positions.getPosition(posAlly.ordinal()+1))==null){
            System.out.println("La linea avversaria e' vuota, il Computer attacchera' la torre.");
            enemy.getTower().setHp(enemy.getTower().getHp()-c.getDamage());
            System.out.println("\nL'unita' avversaria infligge "+c.getDamage()+" danni alla torre.");
            System.out.println(enemy.getTower().getInfo());
            return;
        }

        Positions posEnemy = posAlly;

        if(enemy.getBg().getMap().get(posEnemy)!=null){
            int dmg = (int)(c.getDamage()-c.getDamage()*enemy.getBg().getMap().get(posEnemy).getArmor());
            enemy.getBg().getMap().get(posEnemy).setHp(enemy.getBg().getMap().get(posEnemy).getHp()-dmg);
            System.out.printf("\nL'unita' avversaria infligge "+dmg+" danni al tuo "+enemy.getBg().getMap().get(posEnemy).getName()+".\n");

            GameServices.remainingDamage(enemy.getBg().getMap().get(posEnemy), enemy);
            return;
        } else {
            if(enemy.getBg().getMap().get(Positions.getPosition(posEnemy.ordinal()+1))!=null){
                posEnemy=Positions.getPosition(posEnemy.ordinal()+1);   
                
                int dmg = (int)(c.getDamage()-c.getDamage()*enemy.getBg().getMap().get(posEnemy).getArmor());
                enemy.getBg().getMap().get(posEnemy).setHp(enemy.getBg().getMap().get(posEnemy).getHp()-dmg);
                System.out.printf("\nL'unita' avversaria infligge "+dmg+" danni al tuo "+enemy.getBg().getMap().get(posEnemy).getName()+".\n");

                GameServices.remainingDamage(enemy.getBg().getMap().get(posEnemy), enemy);
                return;
            }else return;
        }
    }
    
}