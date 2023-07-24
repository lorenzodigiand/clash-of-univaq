package clashOfUnivaq.datamodel.cards.spells.damaging;

import clashOfUnivaq.datamodel.cards.spells.Spell;
import clashOfUnivaq.logic.spells.damaging.FireballBehavior;

public class Fireball extends Spell {

    public Fireball(){
        this.setName("Fireball");
        this.setEnergyCost(2);
        this.setParam(75);
        this.setBehavior(FireballBehavior.getInstance());
    }

    public String getInfo(){
        return this.getName()+" | " + super.getInfo()+ "Danno incendiario: "+this.getParam();
    }

}