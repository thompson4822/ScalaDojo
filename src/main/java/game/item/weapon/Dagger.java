package game.item.weapon;

import java.util.ArrayList;

/**
 * Created on 6/29/11 at 5:39 AM by Steve
 */
public class Dagger {
    static ArrayList<String> types() {
        ArrayList<String> result = new ArrayList<String>();
        result.add("Dagger");
        result.add("Dirk");
        result.add("Poniard");
        result.add("Stiletto");
        result.add("Butter Knife");
        return result;
    }

}
