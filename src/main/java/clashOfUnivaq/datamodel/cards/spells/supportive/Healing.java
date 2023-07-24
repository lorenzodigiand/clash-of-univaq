package clashOfUnivaq.datamodel.cards.spells.supportive;

import clashOfUnivaq.datamodel.cards.spells.Spell;
import clashOfUnivaq.logic.spells.supportive.HealingBehavior;

public class Healing extends Spell {

    public Healing(){
        this.setName("Heal");
        this.setEnergyCost(3);
        this.setParam(100);
        this.setBehavior(HealingBehavior.getInstance());
    }

    public String getInfo(){
        return this.getName()+"     | " + super.getInfo()+"Punti vita curati: "+this.getParam();
    }
    
}