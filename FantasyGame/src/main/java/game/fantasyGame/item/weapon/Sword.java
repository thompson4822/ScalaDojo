package game.fantasyGame.item.weapon;

import java.util.ArrayList;

/**
 * Created on 6/29/11 at 5:38 AM by Steve
 */

public class Sword {

    static ArrayList<String> types() {
        ArrayList<String> result = new ArrayList<String>();
        result.add("Sword");
        result.add("Machete");
        result.add("Rapier");
        result.add("Cutlass");
        return result;
    }
}
