package clashOfUnivaq.logic.match;

import java.util.Random;

import clashOfUnivaq.datamodel.cards.Card;
import clashOfUnivaq.datamodel.cards.characters.magical.Bard;
import clashOfUnivaq.datamodel.cards.characters.magical.Cleric;
import clashOfUnivaq.datamodel.cards.characters.magical.Mage;
import clashOfUnivaq.datamodel.cards.characters.magical.Paladin;
import clashOfUnivaq.datamodel.cards.characters.physical.Giant;
import clashOfUnivaq.datamodel.cards.characters.physical.Knight;
import clashOfUnivaq.datamodel.cards.characters.physical.Ranger;
import clashOfUnivaq.datamodel.cards.characters.physical.Rogue;
import clashOfUnivaq.datamodel.cards.spells.damaging.Fireball;
import clashOfUnivaq.datamodel.cards.spells.damaging.Poison;
import clashOfUnivaq.datamodel.cards.spells.damaging.Thunder;
import clashOfUnivaq.datamodel.cards.spells.supportive.Freeze;
import clashOfUnivaq.datamodel.cards.spells.supportive.Healing;
import clashOfUnivaq.datamodel.cards.spells.supportive.Rage;

public class Casual {

    /** Creates a new random card 
     * @return A new random card, which can be a spell or a character
     * @author Lorenzo Di Giandomenico
    */
    public static Card randomizeCard(){

        Random random = new Random();
        Card card = null;

        switch(random.nextInt(14)){
            case 0:
                card = new Knight();
                break;
            case 1:
                card = new Giant();
                break;
            case 2:
                card = new Rogue();
                break;
            case 3:
                card = new Ranger();
                break;
            case 4:
                card = new Mage();
                break;
            case 5:
                card = new Cleric();
                break;
            case 6:
                card = new Bard();
                break;
            case 7:
                card = new Paladin();
                break;
            case 8:
                card = new Fireball();
                break;
            case 9:
                card = new Poison();
                break;
            case 10:
                card = new Thunder();
                break;
            case 11:
                card = new Healing();
                break;
            case 12:
                card = new Freeze();
                break;
            case 13:
                card = new Rage();
                break;
        }
        return card;
    }
    
}