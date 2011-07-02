package game.fantasyGame.creature.humanoid;

import game.fantasyGame.Dice;
import game.fantasyGame.creature.Entity;

/**
 * Created on 6/29/11 at 5:33 AM by Steve
 */
public class Hobbit extends Entity {
    public String getShortDescription() {
        return "Hobbit";
    }

    @Override
    protected int baseStrength() {
        return Dice.roll(1, 5);
    }

    @Override
    protected int baseMagic() {
        return Dice.roll(1, 0);
    }

    @Override
    protected int baseDexterity() {
        return Dice.roll(2, 0);
    }

    @Override
    protected int baseConstitution() {
        return Dice.roll(2, 6);
    }
}
