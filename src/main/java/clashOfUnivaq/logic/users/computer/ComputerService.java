package clashOfUnivaq.logic.users.computer;

import java.util.Random;

import clashOfUnivaq.datamodel.cards.spells.Spell;
import clashOfUnivaq.datamodel.cards.characters.Character;
import clashOfUnivaq.datamodel.map.Positions;
import clashOfUnivaq.datamodel.match.Game;
import clashOfUnivaq.datamodel.users.Computer;
import clashOfUnivaq.datamodel.users.User;
import clashOfUnivaq.logic.characters.ActionService;
import clashOfUnivaq.logic.users.IUserService;
import clashOfUnivaq.logic.users.GameServices;

public class ComputerService extends GameServices implements IUserService<Computer> {
    
    public void nextTurn(Computer pc, User g){

        Random random = new Random();

        switch (random.nextInt(2)){
            case 0:
            System.out.println("\nIl Computer ha scelto di schierare una carta.");
            this.deploy(pc, g);
            break;

            case 1:
            System.out.println("\nIl Computer ha scelto di passare il turno.");
            break;
        }
        GameServices.removeFreeze(pc);
    }

    public void deploy(Computer pc, User g){

        if(pc.getEnergy()<=3){
            System.out.println("Il Computer non ha abbastanza energia per schierare, passa all'attacco.");
            this.chooseAttack(pc, g);
            return;
        }

        for(int i=0, counter=0; i<5; i++){
            if(pc.getDeck().getCards().get(i) instanceof Spell) counter++;
            if(counter==5){
                System.out.println("Il Computer ha provato a schierare ma ha solo incantesimi in mano, passa all'attacco.");
                this.chooseAttack(pc, g);
                return;
            }
        }

        Random random = new Random();
        int index = random.nextInt(5);

        if(pc.getDeck().getCards().get(index) instanceof Character){

            Character c = ((Character)pc.getDeck().getCards().get(index));
            Positions pos = Positions.getPosition(random.nextInt(5));

            if(!(pc.getBg().getMap().containsKey(pos))){
                
                if(pos.ordinal()%2!=0) c.setArmor(c.getArmor()*2);

                pc.getBg().getMap().put(pos, c);

                System.out.println("Il Computer ha schierato un "+pc.getBg().getMap().get(pos).getName()+ " in "+pos);

                pc.getDeck().getCards().remove(index);
                pc.getDeck().addCard();
                pc.setEnergy(pc.getEnergy()-c.getEnergyCost());
                
            }else{
                System.out.println("Il Computer ha provato a schierare una carta, senza successo...");
            }
        }else{
            System.out.println("Il Computer ha provato a schierare una carta, senza successo...");
        }

        this.chooseNext(pc, g);
    }

    public void chooseNext(Computer pc, User g){
        Random random = new Random();
        switch(random.nextInt(2)){
            case 0:
            System.out.println("Il Computer ha poi scelto di attaccare.");
            this.chooseAttack(pc,g);
            break;

            case 1:
            System.out.println("Il Computer ha poi scelto di terminare il suo turno.");
        }
    }

    public void chooseAttack(Computer pc, User g){

        if(Game.getMoves()==0 || Game.getMoves()==1){
            System.out.println("Ma durante il suo primo turno il Computer non puo attaccare.");
            return;
        }

        Random random = new Random();
        
        switch(random.nextInt(3)){
            case 0:
            System.out.println("Il Computer ha scelto di attaccare con un unita' schierata in campo.");
            this.attack(pc, g);
            break;

            case 1:
            System.out.println("Il Computer ha scelto di lanciare un incantesimo.");
            this.throwSpell(pc, g);
            break;

            case 2:
            System.out.println("Ma poi ci ha ripensato ed ha scelto di terminare il suo turno.");
        }
    }

    public void attack(Computer pc, User g){

        Random random = new Random();
        Positions pos = Positions.getPosition(random.nextInt(6));

        if(pos.ordinal()%2==0){

            if(pc.getBg().getMap().get(pos)==null){

                System.out.println("Il Computer ha provato ad attaccarti, senza successo...");
                return;

            }else{

                Character c = pc.getBg().getMap().get(pos);
                    switch(random.nextInt(1)){
                    case 0:
                        ActionService.attack(c, pc, g);
                        return;

                    case 1:
                        c.specialAttack(pc, g);
                        return;
                }
            }
        }else{
            System.out.println("Il Computer ha provato ad attaccarti, senza successo...");
            return;
        }
    }

    public void throwSpell(Computer pc, User g){

        if (pc.getEnergy()<=3 ){
            System.out.println("Il Computer non ha abbastanza energia per lanciare incantesimi...");
            return;
        }

        Random random = new Random();
        int index = random.nextInt(5);

        if(pc.getDeck().getCards().get(index) instanceof Spell){

            Spell s = (Spell) pc.getDeck().getCards().get(index);

            if(pc.getEnergy()>= s.getEnergyCost()){

                s.cast(pc, g);
                pc.getDeck().getCards().remove(index);
                pc.setEnergy(pc.getEnergy()-s.getEnergyCost());
                pc.getDeck().addCard();
                return;

            }else{
                System.out.println("Il Computer ha provato a lanciarti un incantesimo, senza successo...");
                return;
            }

        }else{
            System.out.println("Il Computer ha provato a lanciarti un incantesimo, senza successo...");
            return;
        }
    }

    /** Method for getting a valid position so that the computer can attack/deploy the enemy/ally without issues, used after User.checkBG so that the battleground isn't empty 
     * @param x The user from which we get the battleground to check
     * @return A safe position for the computer to use in his methods
     * @author Lorenzo Di Giandomenico
    */
    public static Positions getTarget(User x){

        Random random = new Random();
        Positions pos = Positions.getPosition(random.nextInt(6));
        while(!(x.getBg().getMap().containsKey(pos))){
            pos = Positions.getPosition(random.nextInt(6));
        }
        return pos;
    }

}