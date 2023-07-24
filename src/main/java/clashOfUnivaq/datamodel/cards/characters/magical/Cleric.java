package clashOfUnivaq.datamodel.cards.characters.magical;

import clashOfUnivaq.datamodel.cards.characters.Character;
import clashOfUnivaq.logic.characters.magical.ClericBehavior;

public class Cleric extends Character {

    public Cleric(){
        super();
        this.setName("Cleric");
        this.setHp(250);
        this.setArmor(0.15);
        this.setEnergyCost(2);
        this.setResource(100);
        this.setDamage(125);
        this.setBehavior(ClericBehavior.getInstance());
    }

    public int setValue(){
        return 3;
    }
    
    public String getInfo(){
        return this.getName()+"   | "+super.getInfo()+" | Mana: "+this.getResource();
    }
    
}