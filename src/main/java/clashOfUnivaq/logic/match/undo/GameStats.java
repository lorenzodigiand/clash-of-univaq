package clashOfUnivaq.logic.match.undo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import clashOfUnivaq.datamodel.map.Positions;
import lombok.Getter;
import lombok.Setter;

public class GameStats {

    private static @Getter @Setter List<GameState> stats = new ArrayList<>();

    /** Prints on screen the list of games ordered by number of moves 
     *  @author Lorenzo Di Giandomenico
    */
    public static void getMoves(){
        
        stats.stream().sorted((gs1, gs2) -> Integer.compare(gs1.getMoves(), gs2.getMoves()))
        .forEach((gs) -> System.out.println("Numero di mosse: "+gs.getMoves()));

    }

    /** Prints on screen the list of games ordered by number of pieces 
     *  @author Lorenzo Di Giandomenico
    */
    public static void getPieces(){

        stats.stream().sorted((gs1, gs2) -> Integer.compare((gs1.getP1bg().size()+gs1.getP2bg().size()), (gs2.getP1bg().size()+gs1.getP2bg().size())))
        .forEach((gs) -> System.out.println("Numero di unita': "+(gs.getP1bg().size()+gs.getP2bg().size())));
    
    }

    /** Prints on screen the list of games ordered by the values of pieces 
     *  @author Lorenzo Di Giandomenico
    */
    public static void getValues(){

        Collections.sort(stats, new Comparator<GameState>() {
            @Override
            public int compare(GameState gs1, GameState gs2){
                int valueG1=0, valueG2=0;
                for (Positions pos : Positions.values()) {
                    if(gs1.getP1bg().get(pos) != null) valueG1+=gs1.getP1bg().get(pos).getValue();
                    if(gs1.getP2bg().get(pos) != null) valueG1+=gs1.getP2bg().get(pos).getValue();

                    if(gs2.getP1bg().get(pos) != null) valueG2+=gs2.getP1bg().get(pos).getValue();
                    if(gs2.getP2bg().get(pos) != null) valueG2+=gs2.getP2bg().get(pos).getValue();
                }
                return Integer.compare(valueG1, valueG2);
            }
        });
        
        // Can't use lambdas because value is not final or effectively final
        for (GameState gameState : stats) {
            int value=0;
            for (Positions pos : Positions.values()) {
                if(gameState.getP1bg().get(pos) != null) value+=gameState.getP1bg().get(pos).getValue();
                if(gameState.getP2bg().get(pos) != null) value+=gameState.getP2bg().get(pos).getValue();
            }
            System.out.println("Valore complessivo delle unita': "+value);
        }
    }
    
}