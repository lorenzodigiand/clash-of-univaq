package clashOfUnivaq.logic.spells;

import clashOfUnivaq.datamodel.cards.spells.Spell;
import clashOfUnivaq.datamodel.users.Computer;
import clashOfUnivaq.datamodel.users.Player;
import clashOfUnivaq.datamodel.users.User;

public interface SpellBehavior {

    /** Throws a spell onto an enemy, an ally or a tower depending on the spell 
     * @param s The spell thrown by the first user
     * @param x The player who's gonna throw the spell
     * @param y The user who's gonna get attacked if the selected spell is meant to damage the tower or an enemy's unit
     * @author Lorenzo Di Giandomenico
    */
    public void cast(Spell s, Player x, User y);

    /** Throws a spell onto an enemy, an ally or a tower depending on the spell 
     * @param s The spell thrown by the first user
     * @param x The computer who's gonna throw the spell
     * @param y The user who's gonna get attacked if the selected spell is meant to damage the tower or an enemy's unit
     * @author Lorenzo Di Giandomenico
    */
    public void cast(Spell s, Computer x, User y);
    
}