package clashOfUnivaq.logic.users;

import java.io.Serializable;

import clashOfUnivaq.datamodel.cards.characters.Character;
import clashOfUnivaq.datamodel.map.Positions;
import clashOfUnivaq.datamodel.match.Game;
import clashOfUnivaq.datamodel.users.User;
import clashOfUnivaq.logic.match.undo.GameState;
import clashOfUnivaq.logic.match.undo.GameStats;
import clashOfUnivaq.logic.match.undo.Undo;

public class GameServices implements Serializable {

    /** After every attack checks if the enemy is dead, if so the remaining damage goes to the tower 
     *  @param c The character to check if it's dead or not
     *  @param owner The user to which the character belongs to
     *  @author Lorenzo Di Giandomenico
    */
    public static void remainingDamage(Character c, User owner){
        
        if(c.getHp()<=0){
            owner.getBg().getMap().remove(owner.getBg().getMap().inverse().get(c));
            System.out.println("\n"+c.getName()+" nemico ucciso!");
            owner.getTower().setHp(owner.getTower().getHp()+c.getHp());
            System.out.println("Punti vita torre nemica: "+owner.getTower().getHp());
        }
    }

    /** After every turn removes the freeze status effect from every character on the battleground 
     *  @param x The user from which the method removes from his battleground the freeze status effect
     *  @author Lorenzo Di Giandomenico
    */
    public static void removeFreeze(User x){
        
        x.getBg().getMap().forEach((p, c) -> c.setFreeze(false));
    }

    /** Given a user, checks if his entire battleground is empty 
     *  @param x The user from which the method gets the battleground to check
     *  @return True if the battleground of the user is empty, false otherwise
     *  @author Lorenzo Di Giandomenico
    */
    public static boolean checkBG(User x){System.err.println();

        for (Positions pos : Positions.values()) {
            if(x.getBg().getMap().containsKey(pos)) return false;
        }
        return true;

    }

    /** When a player wants to quit a game, clears the variables that need to be used in the next game and adds a new GameState for stats
     *  @param g1 The first user of the game
     *  @param g2 The second user of the game
     *  @author Lorenzo Di Giandomenico
    */
    public static void endGame(User g1, User g2){
        GameStats.getStats().add(new GameState(g1, g2));
        Undo.getList().clear();
        Undo.setIndex(0);
        Game.setMoves(0);
        Game.setEnd(false);
    }

}