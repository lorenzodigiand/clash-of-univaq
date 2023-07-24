package clashOfUnivaq.logic.users.player;

import java.util.Scanner;

import clashOfUnivaq.datamodel.users.Player;
import clashOfUnivaq.datamodel.users.User;
import clashOfUnivaq.datamodel.cards.Card;
import clashOfUnivaq.datamodel.cards.characters.Character;
import clashOfUnivaq.datamodel.cards.spells.Spell;
import clashOfUnivaq.datamodel.map.Positions;
import clashOfUnivaq.datamodel.match.Game;
import clashOfUnivaq.logic.characters.ActionService;
import clashOfUnivaq.logic.match.exceptions.InvalidPositionException;
import clashOfUnivaq.logic.match.exceptions.NotACharacterException;
import clashOfUnivaq.logic.match.exceptions.NotASpellException;
import clashOfUnivaq.logic.match.exceptions.NotEnoughEnergyException;
import clashOfUnivaq.logic.match.undo.GameState;
import clashOfUnivaq.logic.match.undo.Undo;
import clashOfUnivaq.logic.users.IUserService;
import clashOfUnivaq.logic.users.GameServices;

public class PlayerService extends GameServices implements IUserService<Player> {

    transient Scanner scanner = new Scanner(System.in);
    private boolean endTurn;

    public void nextTurn(Player g1, User g2){

        System.out.println("\n"+g1.getName()+", e' il tuo turno, cosa vuoi fare?");
        System.out.println("1: Schiera e/o attacca.");
        System.out.println("2: Passa il turno.");
        System.out.println("3: Indietro di una mossa.");
        System.out.println("4: Salva partita ed esci.");
        System.out.println("5: Esci senza salvare.");

        switch(scanner.next()){
            case "1":
            Undo.save(new GameState(g1, g2));
            this.deploy(g1, g2);
            break;

            case "2":
            Undo.save(new GameState(g1, g2));
            break;

            case "3":
            if(Game.getMoves()==0 || Game.getMoves()==1){
                System.out.println("\nNon puoi annullare la tua prima mossa, riprova al prossimo turno.");
                this.nextTurn(g1, g2);
            }else{
                System.out.println("\nStai per ripetere il tuo turno precedente.");
                Undo.restore(g1, g2);
            }
            break;

            case "4":
            Game.quit(g1, g2);
            break;

            case "5":
            Game.setEnd(true);
            break;

            default:
            System.out.println("\nAzione non valida, riprova.");
            this.nextTurn(g1, g2);
        }
        GameServices.removeFreeze(g1);
        endTurn=false;
    }

    public void deploy(Player g1, User g2){

        int counter=0;
        for (Card c : g1.getDeck().getCards()) {
            if(c instanceof Spell) counter++;
            if(counter==5){
                System.out.println("\nNon possiedi carte personaggio da schierare, passerai all'attacco.");
                this.chooseAttack(g1, g2);
                return;
            }
        }

        System.out.println("\nQuale carta desideri posizionare?    Energia: "+g1.getEnergy()+" / 10");
        System.out.println("Inserire 0 per passare direttamente alla fase d'attacco.");
        System.out.println("Inserisci 6 per tornare indietro.\n");
        g1.getDeck().printDeck();

        String choose = scanner.next().toUpperCase();  

        switch(choose){
            case "1": case "2": case "3": case "4": case "5":

            int index = Integer.parseInt(choose)-1;

            try {
                g1.getDeck().verifyIsChar(index);
                g1.checkEnergy(g1.getDeck().getCards().get(index));
            } catch (NotACharacterException | NotEnoughEnergyException e) {
                this.deploy(g1, g2);
                break;
            }

            Character c= (Character) g1.getDeck().getCards().get(index);

            System.out.println("\nIn che posizione vuoi schierarla?");
            System.out.println("Inserisci 0 per tornare indietro.");
            System.out.println("Stato attuale del campo di battaglia:\n");

            System.out.println(g2.getTower().getInfo());
            g2.getBg().bgInfoEnemy();
            System.out.println();
            g1.getBg().bgInfoAlly();
            System.out.println(g1.getTower().getInfo());

            Positions pos = null;

            do {
                String position=scanner.next().toUpperCase();
                try {
                    if(position.equals("0")){
                        this.deploy(g1, g2);
                        return;
                    }
                    pos = Positions.valueOf(position);
                } catch (IllegalArgumentException e) {
                    System.out.println("\nPosizione non valida, riprova.");
                    System.out.println("Inserisci 0 per tornare indietro.\n");

                    System.out.println(g2.getTower().getInfo());
                    g2.getBg().bgInfoEnemy();
                    System.out.println();
                    g1.getBg().bgInfoAlly();
                    System.out.println(g1.getTower().getInfo());
                }
            } while (pos == null);            
            
            try {
                g1.getBg().setCharacter(c, pos);
            } catch (InvalidPositionException e) {
                this.deploy(g1, g2);
                break;
            }

            g1.setEnergy(g1.getEnergy()-c.getEnergyCost());
            g1.getDeck().getCards().remove(index);
            g1.getDeck().addCard();

            System.out.println("\n"+c.getName()+" schierato in "+pos);
            this.chooseNext(g1, g2);
            break;

            case "6":
            this.nextTurn(g1, g2);
            break;

            case "0":
            this.chooseAttack(g1, g2);
            break;

            default:
            System.out.println("\nCarta selezionata non valida, riprova.");
            this.deploy(g1, g2);
        }
    }

    public void chooseNext(Player g1, User g2){

        System.out.println("\nOra vuoi attaccare, passare il turno o continuare a schierare?");
        System.out.println("1: Attaccare.");
        System.out.println("2: Passare il turno.");
        System.out.println("3: Continuare a schierare.");

        switch(scanner.next()){
            case "1":
            this.chooseAttack(g1, g2);
            break;

            case "2":
            break;

            case "3":
            this.deploy(g1, g2);
            break;

            default:
            System.out.println("\nAzione inserita non valida, riprova.");
            this.chooseNext(g1, g2);
        }
    }

    public void chooseAttack(Player g1, User g2) {

        if(Game.getMoves()==0 || Game.getMoves()==1){
            System.out.println("\nDurante il primo turno non si puo attaccare, riprova il prossimo turno.");
            return;
        }

        System.out.println("\nDesideri lanciare un incantesimo, attaccare con un'unita' schierata o passare il turno?");
        System.out.println("1: Lanciare un'incantesimo.");
        System.out.println("2: Attaccare con un'unita' schierata.");
        System.out.println("3: Passare il turno.");

        switch(scanner.next()){

            case "1":
            this.throwSpell(g1, g2);
            break;

            case "2":
            this.attack(g1, g2);
            break;

            case "3":
            break;

            default:
            System.out.println("\nMetodo di attacco inserito non valido, riprova.");
            this.chooseAttack(g1, g2);
        }
    }

    public void attack(Player g1, User g2){

        if(GameServices.checkBG(g1)){
            System.out.println("\nNon hai nessuna unita' schierata in campo, scegli un altro tipo di attacco.");
            this.chooseAttack(g1, g2);
            return;
        }

        System.out.println("\nCon quale unita' schierata desideri attaccare?");
        System.out.println("Inserisci 0 per tornare indietro.\n");
        g1.getBg().bgInfo();

        Positions pos=null;

        do {
            String position=scanner.next().toUpperCase();

            try {
                if(position.equals("0")){
                    if(endTurn) System.out.println("\nHai gia lanciato un incantesimo in questo turno!");
                    else this.chooseAttack(g1, g2);
                    return;
                }
                pos =Positions.valueOf(position);
            } catch (IllegalArgumentException e) {
                System.out.println("\nPosizione non valida, riprova.");
                System.out.println("Inserisci 0 per tornare indietro.\n");
                g1.getBg().bgInfo();
            }
        } while (pos == null);

        if(g1.getBg().getMap().get(pos)==null){
            System.out.println("\nPosizione selezionata vuota, riprova.");
            this.attack(g1, g2);
            return;
        }

        if(pos.ordinal()%2!=0){
            System.out.println("\nNon puoi attaccare con un'unita' schierata in difesa, riprova.");
            this.attack(g1, g2);
            return;
        }

        Character ally = g1.getBg().getMap().get(pos);

        if(ally.isFreeze()){
            System.out.println("\nL'unita' con la quale vuoi attaccare e' congelata, scegline un altra.");
            this.attack(g1, g2);
            return;
        }

        System.out.println("\nQuale tipo di attacco vuoi eseguire?");
        System.out.println("1: Attacco Standard.");
        System.out.println("2: Attacco Speciale.");

        String choice;
        switch(scanner.next()){
            case "1":
            ActionService.attack(ally, g1, g2);

            if(endTurn) return;

            System.out.println("\nOra vuoi lanciare un incantesimo o terminare il turno?");
            System.out.println("1: Lanciare un incantesimo.");
            System.out.println("2: Terminare il turno.");

            choice = scanner.next();
            
            while(!(choice.equals("1")) && !(choice.equals("2"))){
                System.out.println("Scelta sbagliata, riprova.");
                choice = scanner.next();
            }

            endTurn=true;
            if(choice.equals("1")) this.throwSpell(g1, g2);
            break;

            case "2":
            ally.specialAttack(g1, g2);
            
            if(endTurn) return;

            System.out.println("\nOra vuoi lanciare un incantesimo o terminare il turno?");
            System.out.println("1: Lanciare un incantesimo.");
            System.out.println("2: Terminare il turno.");

            choice = scanner.next();

            while(!(choice.equals("1")) && !(choice.equals("2"))){
                System.out.println("Scelta sbagliata, riprova.");
                choice = scanner.next();
            }

            endTurn=true;
            if(choice.equals("1")) this.throwSpell(g1, g2);
            break;

            default:
            System.out.println("\nAttacco inserito non valido, riprova.");
            this.attack(g1, g2);
        }  
    }

    public void throwSpell(Player g1, User g2){
        int counter=0;
        for (Card c : g1.getDeck().getCards()) {
            if(c instanceof Character) counter++;
            if(counter==5){
                System.out.println("\nNon possiedi incantesimi da lanciare, scegli un altro tipo di attacco.");
                this.chooseAttack(g1, g2);
                return;
            }
        }
                
        System.out.println("\nQuale incantesimo desideri lanciare?    Energia: "+g1.getEnergy()+" / 10");
        System.out.println("Inserire 0 per tornare indietro.\n");
        g1.getDeck().printDeck();

        String choose = scanner.next();

        switch(choose){
            case "1": case "2": case "3": case "4": case "5":

            int index = Integer.parseInt(choose)-1;

            try {
                g1.getDeck().verifyIsSpell(index);
                g1.checkEnergy(g1.getDeck().getCards().get(index));
            } catch (NotASpellException | NotEnoughEnergyException e) {
                this.throwSpell(g1, g2);
                break;
            }

            Spell s = (Spell) g1.getDeck().getCards().get(index);
            s.cast(g1, g2);
            g1.setEnergy(g1.getEnergy()-s.getEnergyCost());
            g1.getDeck().getCards().remove(index);
            g1.getDeck().addCard();

            if(endTurn) return;

            System.out.println("\nOra vuoi attaccare o terminare il turno?");
            System.out.println("1: Attaccare.");
            System.out.println("2: Terminare il turno.");

            String choice = scanner.next();

            while(!(choice.equals("1")) && !(choice.equals("2"))){
                System.out.println("Scelta sbagliata, riprova.");
                choice = scanner.next();
            }

            endTurn=true;
            if(choice.equals("1")) this.attack(g1, g2);
            break;

            case "0":
            if(endTurn) System.out.println("\nHai gia attaccato in questo turno!");
            else this.chooseAttack(g1, g2);
            break;

            default:
            System.out.println("\nIncantesimo selezionato non valido, riprova.");
            this.throwSpell(g1, g2);
        }
    }

}