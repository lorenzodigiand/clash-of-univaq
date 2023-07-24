package clashOfUnivaq.datamodel.cards.spells.supportive;

import clashOfUnivaq.datamodel.cards.spells.Spell;
import clashOfUnivaq.logic.spells.supportive.FreezeBehavior;

public class Freeze extends Spell {

    public Freeze(){
        this.setName("Freeze");
        this.setEnergyCost(4);
        this.setParam(1);
        this.setBehavior(FreezeBehavior.getInstance());
    }

    public String getInfo(){
        return this.getName()+"   | " + super.getInfo()+ "Durata Congelamento: "+this.getParam()+" turno.";
    }
    
}