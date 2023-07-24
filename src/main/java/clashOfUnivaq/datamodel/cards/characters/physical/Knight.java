package clashOfUnivaq.datamodel.cards.characters.physical;

import clashOfUnivaq.datamodel.cards.characters.Character;
import clashOfUnivaq.logic.characters.physical.KnightBehavior;

public class Knight extends Character {

    public Knight(){
        super();
        this.setName("Knight");
        this.setHp(300);
        this.setArmor(0.20);
        this.setEnergyCost(2);
        this.setResource(50);
        this.setDamage(175);
        this.setBehavior(KnightBehavior.getInstance());
    }

    public int setValue() {
        return 3;
    }

    public String getInfo(){
        return this.getName()+"   | "+super.getInfo()+" | Stamina: "+this.getResource();
    }
    
}