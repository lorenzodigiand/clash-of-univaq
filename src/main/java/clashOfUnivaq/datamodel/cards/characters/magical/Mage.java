package clashOfUnivaq.datamodel.cards.characters.magical;

import clashOfUnivaq.datamodel.cards.characters.Character;
import clashOfUnivaq.logic.characters.magical.MageBehavior;

public class Mage extends Character {

    public Mage(){
        super();
        this.setName("Mage");
        this.setHp(150);
        this.setArmor(0.10);
        this.setEnergyCost(4);
        this.setResource(150);
        this.setDamage(275);
        this.setBehavior(MageBehavior.getInstance());
    }

    public int setValue(){
        return 7;
    }

    public String getInfo(){
        return this.getName()+"     | "+super.getInfo()+" | Mana: "+this.getResource();
    }
    
}