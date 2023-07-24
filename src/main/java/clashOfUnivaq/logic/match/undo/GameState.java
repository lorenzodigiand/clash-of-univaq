package clashOfUnivaq.logic.match.undo;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.BiMap;

import clashOfUnivaq.datamodel.cards.Card;
import clashOfUnivaq.datamodel.cards.characters.Character;
import clashOfUnivaq.datamodel.map.Positions;
import clashOfUnivaq.datamodel.match.Game;
import clashOfUnivaq.datamodel.users.User;
import lombok.Getter;

public class GameState implements Serializable {
    
    private @Getter List<Card> p1deck, p2deck;
    private @Getter BiMap<Positions, Character> p1bg, p2bg;
    private @Getter int p1tower, p2tower, p1energy, p2energy, moves;

    /** Creates a new GameState by cloning all the elements of the game in that specific turn
     *  @param g1 The first user of the game
     *  @param g2 The second user of the game
     *  @author Lorenzo Di Giandomenico
     */
    public GameState(User g1, User g2){

        p1bg=g1.getBg().cloneMap();
        p2bg=g2.getBg().cloneMap();

        p1deck=g1.getDeck().cloneList();
        p2deck=g2.getDeck().cloneList();
        
        p1tower=g1.getTower().getHp();
        p2tower=g2.getTower().getHp();
        
        p1energy=g1.getEnergy();
        p2energy=g2.getEnergy();

        moves = Game.getMoves();
    }
    
}