package Game;

import Game.Objects.Boat;
import Game.Objects.GameObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private final String name;
    private final List<Boat> boats;

    public String getName() {
        return name;
    }

    public List<GameObject> getBoats() {
        return Collections.unmodifiableList(boats);
    }

    public void addBoat(Boat newBoat) {
        boats.add(newBoat);
    }

    public boolean enemyShot(int x, int y) {
        for (Boat boat : boats)
            if (boat.contains(x, y))
                return true;

        return false;
    }

    public void makeShot() {
        //
    }

    public Player(String name) {
        this.name = name;
        boats = new ArrayList<>(10);
    }
}
