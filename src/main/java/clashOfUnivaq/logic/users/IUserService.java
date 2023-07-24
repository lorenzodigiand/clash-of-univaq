package clashOfUnivaq.logic.users;

import clashOfUnivaq.datamodel.users.User;

public interface IUserService <T extends User> {

    /** Starts the next turn for the related user
     * @param g1 The user who's starting his turn
     * @param g2 The user who's waiting for his next turn, passed for attack methods
     * @author Lorenzo Di Giandomenico
    */
    public void nextTurn(T g1, User g2);

    /** Method for correctly placing a character on the battleground of his owner
     * @param g1 The user who's deploying his character
     * @param g2 The user who's waiting for his next turn, passed for attack methods
     * @author Lorenzo Di Giandomenico
    */
    public void deploy(T g1, User g2);

    /** Method for choosing what to do after deploying, either attacking, deploying again or finishing the turn
     * @param g1 The user who's choosing what to do next
     * @param g2 The user who's waiting for his next turn, passed for attack methods
     * @author Lorenzo DI Giandomenico
    */
    public void chooseNext(T g1, User g2);

    /** Method for choosing which kind of attack to execute
     * @param g1 The user who's gonna attack
     * @param g2 The user who's gonna get attacked
     * @author Lorenzo Di Giandomenico
    */
    public void chooseAttack(T g1, User g2);

    /** Method for throwing a spell to the enemy
     * @param g1 The user who's gonna throw the selected spell
     * @param g2 The user who's gonna get attacked
     * @author Lorenzo Di Giandomenico
    */
    public void throwSpell(T g1, User g2);

    /** Method for attacking the enemy with a character on the battleground
     * @param g1 The user who's gonna attack with the selected character
     * @param g2 The user who's gonna get attacked
     * @author Lorenzo Di Giandomenico
    */
    public void attack(T g1, User g2);
    
}