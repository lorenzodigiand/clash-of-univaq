package clashOfUnivaq.datamodel.cards.characters.physical;

import clashOfUnivaq.datamodel.cards.characters.Character;
import clashOfUnivaq.logic.characters.physical.GiantBehavior;

public class Giant extends Character {

    public Giant(){
        super();
        this.setName("Giant");
        this.setHp(450);
        this.setArmor(0.40);
        this.setEnergyCost(6);
        this.setResource(50);
        this.setDamage(125);
        this.setBehavior(GiantBehavior.getInstance());
    }

    public int setValue(){
        return 5;
    }

    public String getInfo(){
        return this.getName()+"    | "+super.getInfo()+" | Stamina: "+this.getResource();
    }
    
}