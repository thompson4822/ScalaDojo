package game.fantasyGame;

import java.util.Random;

/**
 * Created on 7/1/11 at 8:08 AM by Steve
 */
public class Dice {
    private static Random rand = new Random();

    public static int roll(int diceCount, int modifier)
    {
        int result = 0;
        for(int index = 0; index < diceCount; index++)
        {
            result += rand.nextInt(6) + 1;
        }
        return result + modifier;
    }
}
