package clashOfUnivaq.logic.characters;

import clashOfUnivaq.datamodel.cards.characters.Character;
import clashOfUnivaq.datamodel.users.Computer;
import clashOfUnivaq.datamodel.users.Player;
import clashOfUnivaq.datamodel.users.User;

public interface CharacterBehavior {

    /** Attacks the enemy in a unique way depending on the character that attacks 
     * @param c The character selected by the player
     * @param x The player who's gonna attack the enemy
     * @param y The user who's gonna get attacked by the special attack if it's meant to damage his units or his tower
     * @author Lorenzo Di Giandomenico
    */
    public void specialAttack(Character c, Player x, User y);

    /** Attacks the enemy in a unique way depending on the character that attacks 
     * @param c The character selected by the computer
     * @param x The computer who's gonna attack the enemy
     * @param y The user who's gonna get attacked by the special attack if it's meant to damage his units or his tower
     * @author Lorenzo Di Giandomenico
    */
    public void specialAttack(Character c, Computer x, User y);
    
}