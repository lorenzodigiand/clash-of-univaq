package clashOfUnivaq.datamodel.cards;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public abstract class Card implements Serializable, Cloneable {

    private @Getter @Setter int energyCost;
    private @Getter @Setter String name;

    public String getInfo(){
        return "Energy cost: " + this.energyCost + " | ";
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    @Override
    public boolean equals(Object o){
        if(this.name.equalsIgnoreCase(((Card)o).name)) return true;
        else return false;
    }
    
}