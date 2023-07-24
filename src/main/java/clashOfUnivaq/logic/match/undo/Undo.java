package clashOfUnivaq.logic.match.undo;

import java.util.ArrayList;
import java.util.List;

import clashOfUnivaq.datamodel.match.Game;
import clashOfUnivaq.datamodel.users.User;
import lombok.Getter;
import lombok.Setter;

public class Undo {

    private static @Getter @Setter List <GameState> list = new ArrayList<>();
    private static @Getter @Setter int index=0;

    /** Adds a new GameState to the top of the list, then increases the index 
     *  @param gs The GameState which I want to add to the list
     *  @author Lorenzo Di Giandomenico
    */
    public static void save(GameState gs){
        list.add(index++, gs);
    }

    /** Given two users restores them to the conditions of the first user previous turn 
     *  @param g1 The first user of the game
     *  @param g2 The second user of the game
     *  @author Lorenzo Di Giandomenico
    */
    public static void restore(User g1, User g2){
        GameState previous = list.get(index-2);
        index-=2;

        g1.getBg().setMap(previous.getP1bg());
        g1.getDeck().setCards(previous.getP1deck());
        g1.setEnergy(previous.getP1energy());
        g1.getTower().setHp(previous.getP1tower());

        g2.getBg().setMap(previous.getP2bg());
        g2.getDeck().setCards(previous.getP2deck());
        g2.setEnergy(previous.getP2energy());
        g2.getTower().setHp(previous.getP2tower());
                
        Game.setMoves(previous.getMoves());
        Game.setTurn(!Game.isTurn());
    }
    
}