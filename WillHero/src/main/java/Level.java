import java.lang.reflect.Array;
import java.util.ArrayList;

public class Level
{
    private ArrayList<Island> islands;
    private ArrayList<Orc> orcs;
    private ArrayList<Coin> coins;
    private ArrayList<Chest> chests;
//    private ArrayList<Obstacle> obstacles;
//    private ArrayList<Projectile> current_projectiles;

    // Default Constructor: Constructs default (first) level layout
    public Level()
    {
        // Initialising Arraylists for all GameObjects
        islands = new ArrayList<Island>();
        orcs = new ArrayList<Orc>();
        coins = new ArrayList<Coin>();
        chests = new ArrayList<Chest>();
//        obstacles = new ArrayList<Obstacle>();
//        current_projectiles = new ArrayList<Projectile>();

        // Adding all instances of Island
        islands.add(new Island(50, 400, 250));
        islands.add(new Island(400, 300, 200));
        islands.add(new Island(675, 350, 275));
        islands.add(new Island(1100, 275, 300));
        islands.add(new Island(1500, 275, 600));

        islands.get(0).addBackgroundObject("file:assets/BackgroundObj1.png", 0, 95);
        islands.get(0).addBackgroundObject("file:assets/BackgroundObj2.png", 65, 60);
        islands.get(0).addBackgroundObject("file:assets/BackgroundObj3.png", 180, 120);

        islands.get(1).addBackgroundObject("file:assets/BackgroundObj4.png", 5, 110);
        islands.get(1).addBackgroundObject("file:assets/BackgroundObj5.png", 90, 55);

        islands.get(2).addBackgroundObject("file:assets/BackgroundObj3.png", 25, 80);
        islands.get(2).addBackgroundObject("file:assets/BackgroundObj1.png", 200, 100);

        islands.get(3).addBackgroundObject("file:assets/BackgroundObj4.png", 200, 120);
        islands.get(4).addBackgroundObject("file:assets/BackgroundObj4.png", 200, 120);

        orcs.add(new GreenOrc(470,150,50));
        orcs.add(new GreenOrc(800, 200, 50));
        orcs.add(new GreenOrc(875, 100, 50));
        orcs.add(new RedOrc(490, 150, 50));
        orcs.add(new RedOrc(1550, 250, 50));
        orcs.add(new BossOrc(1550,150,200));

//        chests.add(new CoinChest(450, 240));
//        chests.add(new CoinChest(710, 290));
        chests.add(new CoinChest(1200, 215, 5));
        chests.add(new WeaponChest(710, 290, new ThrowingKnives(710, 290, null)));

        coins.add(new Coin(625, 290));
        coins.add(new Coin(625, 230));

        // *** INITIALISE OTHER ARRAY VALUES (According to level layout) ***
    }

    public ArrayList<Island> getIslands() {
        return islands;
    }
    public ArrayList<Orc> getOrcs() {
        return orcs;
    }
    public ArrayList<Coin> getCoins() {
        return coins;
    }
    public ArrayList<Chest> getChests() {
        return chests;
    }
//    public ArrayList<Obstacle> getObstacles() {
//        return obstacles;
//    }
//    public ArrayList<Projectile> getCurrentProjectiles() {
//        return current_projectiles;
//    }
}