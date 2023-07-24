package clashOfUnivaq.datamodel.match;

import java.io.IOException;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import clashOfUnivaq.datamodel.users.User;
import clashOfUnivaq.datamodel.users.Player;
import clashOfUnivaq.datamodel.users.Computer;
import clashOfUnivaq.logic.users.GameServices;
import clashOfUnivaq.logic.users.player.PlayerService;
import clashOfUnivaq.logic.users.computer.ComputerService;

import lombok.Getter;
import lombok.Setter;

public class Game implements Serializable {

    private static @Getter @Setter PlayerService ps=new PlayerService();
    private static @Getter @Setter ComputerService cs=new ComputerService();
    private static @Getter @Setter int moves=0;  
    private static @Getter @Setter boolean turn=true, end=false;

    /** Starts a new PvP/PvC game and checks each turn if the game is still on or if it's ended 
     *  @param g1 The first user of the game
     *  @param g2 The second user of the game
     *  @author Lorenzo Di Giandomenico
    */
    public void start(Player g1, User g2){

        while(!gameFinished(g1, g2)){

            if(turn){
                Game.next(g1, g2);
                g1.addEnergy();
                turn=!turn;
            }else{
                Game.next(g2, g1);
                g2.addEnergy();
                turn=!turn;
            }
            
            if(end){
                System.out.println("\nLa Partita e' stata annullata.");
                System.out.println("Stai per tornare al menu' principale.");
                GameServices.endGame(g1, g2);
                Menu.start();
                return;
            }
            moves++;
        }

        if(g1.getTower().getHp()>0) System.out.println("\n\t"+g1.getName()+" e' il vincitore!");
        else{
            if(g2 instanceof Computer) System.out.println("\n\tIl Computer e' il vincitore!");
            else if(g2 instanceof Player) System.out.println("\n\t"+((Player)g2).getName()+" e' il vincitore!");
        } 
        
        GameServices.endGame(g1, g2);
        Menu.start();
    }

    /** Returns true when the game finishes, false otherwise 
     *  @param g1 The first user of the game
     *  @param g2 The second user of the game
     *  @return True if one of the tower's hp is less or equal to 0, false otherwise
     *  @author Lorenzo Di Giandomenico
     *  
    */
    public boolean gameFinished(User g1, User g2){
        if (g1.getTower().getHp()<=0 || g2.getTower().getHp()<=0) return true;
        return false;
    }

    /** When the player wants to quit, saves the state of the game during the last played turn 
     *  @param g1 The first user of the game
     *  @param g2 The second user of the game
     *  @author Lorenzo Di Giandomenico
    */
    public static void quit(User g1, User g2){
        try {
            FileOutputStream fos = new FileOutputStream("save.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(g1);
            oos.writeObject(g2);
            oos.writeObject(Game.getMoves());

            fos.close();
            oos.close();
            System.out.println("\nPartita salvata con successo.");
            System.out.println("Stai per tornare al Menu' Principale.");

            GameServices.endGame(g1, g2);
            Menu.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** For each turn decides what engine to use for the related user 
     *  @param g1 The first user of the game
     *  @param g2 The second user of the game
     *  @author Lorenzo Di Giandomenico
    */
    public static void next(User g1, User g2){
        if(g1 instanceof Player) ps.nextTurn((Player)g1, g2);
        if(g1 instanceof Computer) cs.nextTurn((Computer)g1, g2);
    }
    
}