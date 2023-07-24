package clashOfUnivaq.datamodel.cards.characters.physical;

import clashOfUnivaq.datamodel.cards.characters.Character;
import clashOfUnivaq.logic.characters.physical.RangerBehavior;

public class Ranger extends Character {

    public Ranger(){
        super();
        this.setName("Ranger");
        this.setHp(250);
        this.setArmor(0.15);
        this.setEnergyCost(3);
        this.setResource(75);
        this.setDamage(225);
        this.setBehavior(RangerBehavior.getInstance());
    }

    public int setValue(){
        return 7;
    }

    public String getInfo(){
        return this.getName()+"   | "+super.getInfo()+" | Stamina: "+this.getResource();
    }
    
}