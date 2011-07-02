package game.fantasyGame.creature;

import game.fantasyGame.Dice;

/**
 * Created on 7/2/11 at 6:08 AM by Steve
 */
public class Player extends Entity {
    private String name;

    @Override
    public String getShortDescription() {
        return "Bold Ser " + name;
    }

    @Override
    protected int baseStrength() {
        return Dice.roll(3, 4);
    }

    @Override
    protected int baseMagic() {
        return Dice.roll(3, 0);
    }

    @Override
    protected int baseDexterity() {
        return Dice.roll(2, 5);
    }

    @Override
    protected int baseConstitution() {
        return Dice.roll(4, 4);
    }

    public Player(String name){
        this.name = name;
    }
}
