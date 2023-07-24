package clashOfUnivaq.datamodel.users;

import java.io.Serializable;

import clashOfUnivaq.datamodel.cards.Card;
import clashOfUnivaq.datamodel.map.Battleground;
import clashOfUnivaq.datamodel.map.Tower;
import clashOfUnivaq.datamodel.match.Deck;
import clashOfUnivaq.logic.match.exceptions.NotEnoughEnergyException;
import lombok.Getter;
import lombok.Setter;

public abstract class User implements Serializable {

    private @Getter @Setter int energy;
    private @Getter @Setter Deck deck;
    private @Getter @Setter Tower tower;
    private @Getter @Setter Battleground bg;

    public User(){
        energy=10;
        deck=new Deck();
        tower=new Tower();
        bg=new Battleground();
    }

    /** Adds energy to the user at the end of his turn
     *  @author Lorenzo Di Giandomenico
    */
    public void addEnergy(){
        if(this.energy==10) return;
        if(this.energy==9) this.energy+=1;
        if(this.energy==8) this.energy+=2;
        else this.energy+=3;
    }

    /** Given a card checks if the user has enough energy to deploy/throw the character/spell 
     *  @param c The card which i want to check the energy cost
     *  @return True if the user has enough energy to use the card, false otherwise
     *  @throws NotEnoughEnergyException if the user's energy is too low to use the card
     *  @author Lorenzo Di Giandomenico
    */
    public boolean checkEnergy(Card c) throws NotEnoughEnergyException{
        if(this.energy>=c.getEnergyCost()) return true;
        else throw new NotEnoughEnergyException();
    }
    
}