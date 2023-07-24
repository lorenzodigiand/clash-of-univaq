package clashOfUnivaq.datamodel.map;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class Tower implements Serializable {

    private @Getter @Setter int hp;

    public Tower(){
        this.hp=3000;
    }

    public String getInfo(){
        if(this.hp<0) this.hp=0;
        return "Torre | Punti vita rimanenti: "+this.hp;
    }
    
}