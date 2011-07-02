package game.fantasyGame.creature.monster;

import game.fantasyGame.Dice;
import game.fantasyGame.creature.Entity;

/**
 * Created on 6/29/11 at 5:34 AM by Steve
 */
public class Goblin extends Entity {
    public String getShortDescription() {
        return "Goblin";
    }

    @Override
    protected int baseStrength() {
        return Dice.roll(2, 0);
    }

    @Override
    protected int baseMagic() {
        return Dice.roll(0, 1);
    }

    @Override
    protected int baseDexterity() {
        return Dice.roll(1, 0);
    }

    @Override
    protected int baseConstitution() {
        return Dice.roll(1, 4);
    }
}
