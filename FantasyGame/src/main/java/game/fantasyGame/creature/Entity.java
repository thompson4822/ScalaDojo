package game.fantasyGame.creature;

/**
 * Created on 7/1/11 at 7:42 AM by Steve
 */
public abstract class Entity {
    private int strength;
    private int magic;
    private int dexterity;
    private int constitution;

    public abstract String getShortDescription();  //

    // How hard are hits?
    public int getStrength() {
        if(strength == 0)
            strength = baseStrength();
        return strength;
    }

    protected abstract int baseStrength();

    // How good with spells?
    public int getMagic() {
        if(magic == 0)
            magic = baseMagic();
        return magic;
    }

    protected abstract int baseMagic();

    // How nimble?
    int getDexterity() {
        if(dexterity == 0)
            dexterity = baseDexterity();
        return dexterity;
    }

    protected abstract int baseDexterity();

    // How hearty?
    int getConstitution() {
        if(constitution == 0)
            constitution = baseConstitution();
        return constitution;
    }

    protected abstract int baseConstitution();
}
