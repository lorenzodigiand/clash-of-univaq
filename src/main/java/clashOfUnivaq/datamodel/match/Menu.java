package clashOfUnivaq.datamodel.match;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import clashOfUnivaq.datamodel.users.Computer;
import clashOfUnivaq.datamodel.users.Player;
import clashOfUnivaq.datamodel.users.User;
import clashOfUnivaq.logic.match.undo.GameStats;

public class Menu {

    /** Prints on screen the main menu and starts a new game 
     *  @author Lorenzo Di Giandomenico
    */
    public static void start(){

        Game game = new Game();
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\tClash of Univaq");
        System.out.println("\nSeleziona la modalita' di gioco:");
        System.out.println("\n1: Player vs Player.");
        System.out.println("2: Player vs Computer.");
        System.out.println("3: Caricare una partita salvata.");
        System.out.println("4: Guarda le statistiche delle partite passate.");
        System.out.println("5: Leggi le regole del gioco.");
        System.out.println("0: Esci dal gioco.");

        switch(scanner.next()){
            case "1":
                System.out.printf("\nNome Giocatore 1: ");
                Player g1 = new Player(scanner.next());
                System.out.printf("\nNome Giocatore 2: ");
                Player g2 = new Player(scanner.next());
                System.out.println("\nLa partita sta per cominciare!");

                game.start(g1, g2);
                break;

            case "2":
                System.out.printf("\nNome giocatore 1: ");
                Player g = new Player(scanner.next());
                System.out.println("\nStai per giocare contro il computer.");
                Computer pc = new Computer();
                System.out.println("\nLa partita sta per cominciare!");

                game.start(g, pc);
                break;

            case "3":
                try {

                FileInputStream fis= new FileInputStream("save.ser");
                ObjectInputStream ois= new ObjectInputStream(fis);

                Player p1 = (Player)ois.readObject();
                User p2 = (User)ois.readObject();
                Game.setMoves((Integer)ois.readObject());

                fis.close();
                ois.close();

                System.out.println("\nStai per caricare una partita salvata.");

                if(p2 instanceof Player) game.start(p1, (Player)p2);
                else game.start(p1, (Computer)p2);
                
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;

            case "4":
                System.out.println("\nEcco l'elenco delle partite ordinato per numero di mosse.");
                GameStats.getMoves();

                System.out.println("\nEcco l'elenco delle partite ordinato per numero di unita' in campo.");
                GameStats.getPieces();

                System.out.println("\nEcco l'elenco delle partite ordinato per valore delle unita' presenti in campo.");
                System.out.println("Bard: 6 - Cleric: 3 - Mage: 7 - Paladin: 10");
                System.out.println("Giant: 5 - Knight: 3 - Ranger: 7 - Rogue: 8\n");
                GameStats.getValues();

                System.out.println("\nPremere 0 per tornare al Menu' Principale.");

                while(!scanner.next().equals("0")){
                    System.out.println("\nComando inserito non valido, riprova.");
                    System.out.println("Premere 0 per tornare al Menu' Principale.");
                }

                Menu.start();
                break;

            case "5":
                System.out.println("\n\tClash of Univaq: Regolamento");
                System.out.println("\nIn Clash of Univaq l'obiettivo del giocatore e' quello di distruggere la torre avversaria sfondando le difese nemiche.");
                System.out.println("Ogni giocatore possiede un mazzo di carte, una riserva d'energia che aumenta ogni turno ed un campo di battaglia formato da 3 corsie.");
                System.out.println("All'inizio del proprio turno si possono schierare le proprie unita' con le quali poi attaccare quelle nemiche.");
                System.out.println("Esistono potenti incantesimi dagli effetti devastanti ed ogni personaggio ha un attacco speciale che richiede risorse per essere eseguito.");
                System.out.println("A voi scoprire quali effetti imprevedibili possano provocare...");
                System.out.println("Uno strumento essenziale e' la possibilitaì di ripetere il turno precedente.");
                System.out.println("Sfruttatelo per sfuggire da situazioni critiche in cui vi siete imbattuti.");
                System.out.println("Alla fine di ogni partita e' possibile vedere le vostre statistiche da quando avete avviato il gioco.");
                System.out.println("\nN.B. Ogni qual volta si interagisce col campo di battaglia bisogna usare delle parole chiave per selezionare la casella opportuna.");
                System.out.println("Left Attack: LA - Central Attack: CA - Right Attack: RA.");
                System.out.println("Left Defense: LD - Central Defense: CD - Right Defense: RD.");
                System.out.println("\nPremere 0 per tornare al Menu' Principale.");

                while(!scanner.next().equals("0")){
                    System.out.println("\nComando inserito non valido, riprova.");
                    System.out.println("Premere 0 per tornare al Menu' Principale.");
                }

                Menu.start();
                break;

            case "0":
                System.exit(0);
                break;

            default:
                System.out.println("\nModalita' inserita non valida, riprova.");
                Menu.start();
        }
        scanner.close();
    }
    
}