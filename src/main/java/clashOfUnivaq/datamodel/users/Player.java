package clashOfUnivaq.datamodel.users;

import lombok.Getter;
import lombok.Setter;

public class Player extends User {

    private @Getter @Setter String name;

    public Player(String name){
        super();
        this.name = name; 
    }
    
}