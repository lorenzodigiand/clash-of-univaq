package clashOfUnivaq.datamodel.map;

import clashOfUnivaq.datamodel.cards.characters.Character;
import clashOfUnivaq.logic.match.exceptions.InvalidPositionException;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import com.google.common.collect.BiMap;
import com.google.common.collect.EnumHashBiMap;

public class Battleground implements Serializable {

    private @Getter @Setter BiMap<Positions, Character> map = EnumHashBiMap.create(Positions.class);

    /** Prints on screen for each position if it's empty or if it's occupied, if there is a character it displays it's info.
     *  @author Lorenzo Di Giandomenico
    */
    public void bgInfo(){
        for(Positions position: Positions.values()) System.out.println(this.positionDetails(position));
    }

    /** Alternative method for printing the ally battleground from the ally side 
     *  @author Lorenzo Di Giandomenico
    */
    public void bgInfoAlly(){
        System.out.println(this.positionInfo(Positions.LA)
                    +" | "+this.positionInfo(Positions.CA)
                    +" | "+this.positionInfo(Positions.RA));

        System.out.println(this.positionInfo(Positions.LD)
                    +" | "+this.positionInfo(Positions.CD)
                    +" | "+this.positionInfo(Positions.RD));
    }

    /** Alternative method for printing the enemy battleground  from the enemy side 
     *  @author Lorenzo Di Giandomenico
    */
    public void bgInfoEnemy(){
        System.out.println(this.positionInfo(Positions.LD)
                    +" | "+this.positionInfo(Positions.CD)
                    +" | "+this.positionInfo(Positions.RD));

        System.out.println(this.positionInfo(Positions.LA)
                    +" | "+this.positionInfo(Positions.CA)
                    +" | "+this.positionInfo(Positions.RA));
    }

    /** Given a position, returns as a String the name of the character on it 
     *  @param position The position where I'll get the info
     *  @return A string with the name of the character on it, if there's one
     *  @author Lorenzo Di Giandomenico
    */
    public String positionInfo(Positions position){
        if(map.containsKey(position)) return "Posizione " + position + ": " + map.get(position).getName();
        return "Posizione " + position + ": Vuota";
    }

    /** Given a position, returns as a String the info of the character on it 
     *  @param position The position where I'll get the info
     *  @return A string with the info of the character on it, if there's one
     *  @author Lorenzo Di Giandomenico
    */
    public String positionDetails(Positions position){
        if(map.containsKey(position)) return "Posizione " + position + ": " + map.get(position).getInfo();
        return "Posizione " + position + ": Vuota";
    }

    /** Given a character and a position, puts the character on the battleground if the position is empty 
     *  @param c The character that I want to place on the battelground
     *  @param pos The position on which I want to place the character
     *  @throws InvalidPositionException When the position I give is already occupied
     *  @author Lorenzo Di Giandomenico
    */
    public void setCharacter(Character c, Positions pos) throws InvalidPositionException{
        if(map.containsKey(pos)) throw new InvalidPositionException();
        else{
            if(pos.ordinal()%2!=0) c.setArmor(c.getArmor()*2); // Double armor if it's in defense
            this.map.put(pos, c);
        }
    }

    /** Clones the battleground, used for the undo function 
     *  @return A new BiMap which is exactly the clone of the battleground at that exact time
     *  @author Lorenzo Di Giandomenico
    */
    public BiMap<Positions, Character> cloneMap(){
        BiMap<Positions, Character> clone = EnumHashBiMap.create(Positions.class);

        for (Positions pos : this.getMap().keySet()) {
            Character c = this.getMap().get(pos);
            try {
                clone.put(pos, (Character)c.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return clone;
    }

}