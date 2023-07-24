package clashOfUnivaq.datamodel.cards.characters.physical;

import clashOfUnivaq.datamodel.cards.characters.Character;
import clashOfUnivaq.logic.characters.physical.RogueBehavior;

public class Rogue extends Character {

    public Rogue(){
        super();
        this.setName("Rogue");
        this.setHp(150);
        this.setArmor(0.10);
        this.setEnergyCost(5);
        this.setResource(50);
        this.setDamage(325);
        this.setBehavior(RogueBehavior.getInstance());
    }

    public int setValue(){
        return 8;
    }

    public String getInfo(){
        return this.getName()+"    | "+super.getInfo()+" | Stamina: "+this.getResource();
    }
    
}