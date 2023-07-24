package clashOfUnivaq.datamodel.map;

public enum Positions {

    LA, LD, CA, CD, RA, RD;
    /*Left Attack, Left Defense
    Central Attack, Central Defense
    Right Attack, Right Defense*/

    /** Given an integer, checks if it corresponds to the ordinal of one of the positions 
     *  @param ordinal The number to check if it corresponds to one of the position's ordinal
     *  @return The related position if it's ordinal corresponds to the parameter given
     *  @author Lorenzo Di Giandomenico
    */
    public static Positions getPosition(int ordinal){
        for(Positions position : Positions.values()) {
            if(position.ordinal()==ordinal) return position;
        }
        return null;
    }
    
}