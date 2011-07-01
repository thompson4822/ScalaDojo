package game.creature.humanoid;

import game.Dice;
import game.creature.Entity;

/**
 * Created on 6/29/11 at 5:33 AM by Steve
 */
public class Pixie extends Entity {
    public String getShortDescription() {
        return "Pixie";
    }

    @Override
    protected int baseStrength() {
        return Dice.roll(1, 0);
    }

    @Override
    protected int baseMagic() {
        return Dice.roll(3, 6);
    }

    @Override
    protected int baseDexterity() {
        return Dice.roll(2, 6);
    }

    @Override
    protected int baseConstitution() {
        return Dice.roll(1, 0);
    }
}
