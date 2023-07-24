package clashOfUnivaq.datamodel.cards.spells.damaging;

import clashOfUnivaq.datamodel.cards.spells.Spell;
import clashOfUnivaq.logic.spells.damaging.PoisonBehavior;

public class Poison extends Spell {

    public Poison(){
        this.setName("Poison");
        this.setEnergyCost(3);
        this.setParam(100);
        this.setBehavior(PoisonBehavior.getInstance());
    }

    public String getInfo(){
        return this.getName()+"   | " + super.getInfo()+ "Danno corrosivo: "+this.getParam();
    }
    
}