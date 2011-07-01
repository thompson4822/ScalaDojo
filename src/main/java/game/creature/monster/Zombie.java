package game.creature.monster;

import game.creature.Entity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created on 6/29/11 at 5:33 AM by Steve
 */
public class Zombie extends Entity {
    public String getShortDescription() {
        return "Zombie";
    }

    @Override
    protected int baseStrength() {
        throw new NotImplementedException();
    }

    @Override
    protected int baseMagic() {
        throw new NotImplementedException();
    }

    @Override
    protected int baseDexterity() {
        throw new NotImplementedException();
    }

    @Override
    protected int baseConstitution() {
        throw new NotImplementedException();
    }
}
