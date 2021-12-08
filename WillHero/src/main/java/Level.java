import java.lang.reflect.Array;
import java.util.ArrayList;

public class Level
{
    private ArrayList<Island> islands;
    private ArrayList<Orc> orcs;
    private ArrayList<Chest> chests;
    private ArrayList<Coin> coins;
//    private ArrayList<Obstacle> obstacles;
//    private ArrayList<Projectile> current_projectiles;

    // Default Constructor: Constructs default (first) level layout
    public Level()
    {
        // Initialising Arraylists for all GameObjects
        islands = new ArrayList<Island>();
//        orcs = new ArrayList<Orc>();
//        chests = new ArrayList<Chest>();
//        coins = new ArrayList<Coin>();
//        obstacles = new ArrayList<Obstacle>();
//        current_projectiles = new ArrayList<Projectile>();

        // Adding all instances of Island
        islands.add(new Island(50, 400, 250));
        islands.add(new Island(400, 300, 200));
        islands.add(new Island(675, 350, 275));
        islands.add(new Island(800, 300, 390));
        orcs.add(new GreenOrc(675, 350, 275));
        chests.add(new CoinChest(850, 300));
        // *** INITIALISE OTHER ARRAY VALUES (According to level layout) ***
    }

    public ArrayList<Island> getIslands() {
        return islands;
    }
//    public ArrayList<Orc> getOrcs() {
//        return orcs;
//    }
//    public ArrayList<Chest> getChests() {
//        return chests;
//    }
//    public ArrayList<Coin> getCoins() {
//        return coins;
//    }
//    public ArrayList<Obstacle> getObstacles() {
//        return obstacles;
//    }
//    public ArrayList<Projectile> getCurrentProjectiles() {
//        return current_projectiles;
//    }
}
