package clashOfUnivaq.datamodel.cards.spells.damaging;

import clashOfUnivaq.datamodel.cards.spells.Spell;
import clashOfUnivaq.logic.spells.damaging.ThunderBehavior;

public class Thunder extends Spell {

    public Thunder(){
        this.setName("Thunder");
        this.setEnergyCost(3);
        this.setParam(125);
        this.setBehavior(ThunderBehavior.getInstance());
    }

    public String getInfo(){
        return this.getName()+"  | " + super.getInfo() + "Danno paralizzante: "+this.getParam();
    }
    
}