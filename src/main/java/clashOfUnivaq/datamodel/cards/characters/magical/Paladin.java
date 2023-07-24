package clashOfUnivaq.datamodel.cards.characters.magical;

import clashOfUnivaq.datamodel.cards.characters.Character;
import clashOfUnivaq.logic.characters.magical.PaladinBehavior;

public class Paladin extends Character {

    public Paladin(){
        super();
        this.setName("Paladin");
        this.setHp(350);
        this.setArmor(0.30);
        this.setEnergyCost(7);
        this.setResource(100);
        this.setDamage(375);
        this.setBehavior(PaladinBehavior.getInstance());
    }

    public int setValue(){
        return 10;
    }

    public String getInfo(){
        return this.getName()+"  | "+super.getInfo()+" | Mana: "+this.getResource();
    }
    
}