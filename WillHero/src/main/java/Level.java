import java.lang.reflect.Array;
import java.util.ArrayList;

public class Level
{
    private ArrayList<Island> islands;
    private ArrayList<Orc> orcs;
    private ArrayList<Chest> chests;
    private ArrayList<Coin> coins;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Projectile> current_projectiles;

    // *** CORRESPONDING CLASSES TO BE IMPLEMENTED SEPARATELY ***

    public Level()
    {
        islands = new ArrayList<Island>();
        orcs = new ArrayList<Orc>();
        chests = new ArrayList<Chest>();
        coins = new ArrayList<Coin>();
        obstacles = new ArrayList<Obstacle>();
        current_projectiles = new ArrayList<Projectile>();

        // *** INITIALISE ARRAY VALUES (According to level layout) ***
    }

    public ArrayList<Island> getIslands() {
        return islands;
    }
    public ArrayList<Orc> getOrcs() {
        return orcs;
    }
    public ArrayList<Chest> getChests() {
        return chests;
    }
    public ArrayList<Coin> getCoins() {
        return coins;
    }
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
    public ArrayList<Projectile> getCurrentProjectiles() {
        return current_projectiles;
    }
}
