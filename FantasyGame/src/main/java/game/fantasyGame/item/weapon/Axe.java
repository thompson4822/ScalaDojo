package game.fantasyGame.item.weapon;

import java.util.ArrayList;

/**
 * Created on 6/29/11 at 5:38 AM by Steve
 */
public class Axe {
    static ArrayList<String> types() {
        ArrayList<String> result = new ArrayList<String>();
        result.add("Axe");
        result.add("Hatchet");
        result.add("Cleaver");
        result.add("Tomahawk");
        result.add("Adz");
        return result;
    }
}
