package clashOfUnivaq.datamodel.cards.spells;

import clashOfUnivaq.datamodel.cards.Card;
import clashOfUnivaq.datamodel.users.User;
import clashOfUnivaq.datamodel.users.Player;
import clashOfUnivaq.datamodel.users.Computer;
import clashOfUnivaq.logic.spells.SpellBehavior;

import lombok.Getter;
import lombok.Setter;

public abstract class Spell extends Card {

    private @Getter @Setter int param;
    private @Getter @Setter SpellBehavior behavior;

    public String getInfo(){
        return super.getInfo();
    }

    public void cast(Player x, User y){
        behavior.cast(this, x, y);
    }

    public void cast(Computer x, User y){
        behavior.cast(this, x, y);
    }

}