package game.creature.monster;

import game.creature.Entity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created on 6/29/11 at 5:32 AM by Steve
 */
public class Skeleton extends Entity {
    public String getShortDescription() {
        return "Skeleton";
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
