package clashOfUnivaq.datamodel.cards.characters;

import clashOfUnivaq.datamodel.cards.Card;
import clashOfUnivaq.datamodel.users.User;
import clashOfUnivaq.datamodel.users.Player;
import clashOfUnivaq.datamodel.users.Computer;
import clashOfUnivaq.logic.characters.CharacterBehavior;

import lombok.Getter;
import lombok.Setter;

public abstract class Character extends Card {

    private final @Getter int value;
    private @Getter @Setter int hp, damage, resource;
    private @Getter @Setter double armor;
    private @Getter @Setter boolean freeze=false;
    private @Getter @Setter CharacterBehavior behavior;

    public Character(){
        this.value=setValue();
    }

    public abstract int setValue();

    public String getInfo(){            
        return super.getInfo() + "HP: "+this.hp+ " | Danno: "+this.damage+" | Armor: "+(int)(this.armor*100);
    }

    public void specialAttack(Player x, User y){
        behavior.specialAttack(this, x, y);
    }

    public void specialAttack(Computer x, User y){
        behavior.specialAttack(this, x, y);
    }
  
}