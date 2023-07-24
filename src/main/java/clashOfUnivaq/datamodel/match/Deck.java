package clashOfUnivaq.datamodel.match;

import java.util.List;

import java.util.ArrayList;
import java.io.Serializable;

import clashOfUnivaq.logic.match.Casual;
import clashOfUnivaq.datamodel.cards.Card;
import clashOfUnivaq.datamodel.cards.spells.Spell;
import clashOfUnivaq.datamodel.cards.characters.Character;
import clashOfUnivaq.logic.match.exceptions.NotASpellException;
import lombok.Getter;
import lombok.Setter;
import clashOfUnivaq.logic.match.exceptions.NotACharacterException;

public class Deck implements Serializable {

    private @Getter @Setter List<Card> cards = new ArrayList<>(5);
 
    /** Creates a new unique deck where each card is different from one another 
     *  @author Lorenzo Di Giandomenico
    */
    public Deck(){
        for(int i=0; i<5; i++) this.addCard();
    }

    /** Adds a new random card to the end of the deck, different from the rest 
     *  @author Lorenzo Di Giandomenico
    */
    public void addCard(){
        Card newCard;
        do {
            newCard = Casual.randomizeCard();
        } while (cards.contains(newCard));

        cards.add(newCard);
    }

    /** For each element of the deck prints the info of the card on screen 
     *  @author Lorenzo Di Giandomenico
    */
    public void printDeck(){
        cards.forEach((c) -> System.out.println((cards.indexOf(c)+1)+": "+c.getInfo()));
    }

    /** Given an index verifies if the related card is a character 
     *  @param index The index of the card i want to verify is a character
     *  @return True if the card is a character, false otherwise
     *  @throws NotACharacterException if the card is a spell
     *  @author Lorenzo Di Giandomenico
    */
    public boolean verifyIsChar(int index) throws NotACharacterException{
        if(cards.get(index) instanceof Character) return true;
        else throw new NotACharacterException();
    }

    /** Given an index verifies if the related card is a spell 
     *  @param index The index of the card i want to verify is a character
     *  @return True if the card is a spell, false otherwise
     *  @throws NotASpellException if the card is a character
     *  @author Lorenzo Di Giandomenico
    */
    public boolean verifyIsSpell(int index) throws NotASpellException{
        if(cards.get(index) instanceof Spell) return true;
        else throw new NotASpellException();
    }

    /** Clones the deck, used for the undo function 
     *  @return A new List of cards which is exactly the clone of the deck at that exact time
     *  @author Lorenzo Di Giandomenico
    */
    public List<Card> cloneList(){
        List<Card> clone = new ArrayList<>();
        
        for (Card card : cards) {
            try {
                clone.add((Card)card.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return clone;
    }

}