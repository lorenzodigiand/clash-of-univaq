package clashOfUnivaq.datamodel.cards.spells.supportive;

import clashOfUnivaq.datamodel.cards.spells.Spell;
import clashOfUnivaq.logic.spells.supportive.RageBehavior;

public class Rage extends Spell {

    public Rage(){
        this.setName("Rage");
        this.setEnergyCost(3);
        this.setParam(75);
        this.setBehavior(RageBehavior.getInstance());
    }

    public String getInfo(){
        return this.getName()+"     | " + super.getInfo()+"Incremento del danno: "+this.getParam();
    }
    
}