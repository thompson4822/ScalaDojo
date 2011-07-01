package game.creature.monster;

import game.Dice;
import game.creature.Entity;

/**
 * Created on 6/29/11 at 5:34 AM by Steve
 */
public class DireWolf extends Entity {
    public String getShortDescription() {
        return "Dire Wolf";
    }

    @Override
    protected int baseStrength() {
        return Dice.roll(3, 0);
    }

    @Override
    protected int baseMagic() {
        return Dice.roll(0, 1);
    }

    @Override
    protected int baseDexterity() {
        return Dice.roll(2, 5);
    }

    @Override
    protected int baseConstitution() {
        return Dice.roll(3, 0);
    }
}
