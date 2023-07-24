package clashOfUnivaq.datamodel.cards.characters.magical;

import clashOfUnivaq.datamodel.cards.characters.Character;
import clashOfUnivaq.logic.characters.magical.BardBehavior;

public class Bard extends Character {

    public Bard(){
        super();
        this.setName("Bard");
        this.setHp(250);
        this.setArmor(0.20);
        this.setEnergyCost(3);
        this.setResource(100);
        this.setDamage(100);
        this.setBehavior(BardBehavior.getInstance());
    }

    public int setValue(){
        return 6;
    }

    public String getInfo(){
        return this.getName()+"     | "+super.getInfo()+" | Mana: "+this.getResource();
    }
    
}