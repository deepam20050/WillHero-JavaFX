import java.lang.reflect.Array;
import java.util.ArrayList;

public class Level
{
    private ArrayList<Island> islands;
    private ArrayList<Orc> orcs;
    private ArrayList<Coin> coins;
    private ArrayList<Chest> chests;
//    private ArrayList<Obstacle> obstacles;

    private GhostHero ghostHero;

    // Default Constructor: Constructs default (first) level layout
    public Level()
    {
        // Initialising Arraylists for all GameObjects
        islands = new ArrayList<Island>();
        orcs = new ArrayList<Orc>();
        coins = new ArrayList<Coin>();
        chests = new ArrayList<Chest>();
//        obstacles = new ArrayList<Obstacle>();

        setLevelDemo();
//        setLevel1();
    }

    private void setLevel1()
    {
        islands.add(new Island(50, 400, 500));
        islands.add(new Island(650, 350, 250));
        islands.add(new Island(1100, 350, 300));
        islands.add(new Island(1500, 275, 350));
        islands.add(new Island(1900, 375, 300));
        islands.add(new Island(2700, 375, 300));

        coins.add(new Coin(350, 275));
        coins.add(new Coin(350, 215));
        coins.add(new Coin(940, 250));
        coins.add(new Coin(1025, 250));
        coins.add(new Coin(2200, 200));
        coins.add(new Coin(2275, 200));
        coins.add(new Coin(2350, 200));
        coins.add(new Coin(2425, 200));
        coins.add(new Coin(2500, 200));
        coins.add(new Coin(2575, 200));
        coins.add(new Coin(2650, 200));
        coins.add(new Coin(2200, 275));
        coins.add(new Coin(2275, 275));
        coins.add(new Coin(2350, 275));
        coins.add(new Coin(2425, 275));
        coins.add(new Coin(2500, 275));
        coins.add(new Coin(2575, 275));
        coins.add(new Coin(2650, 275));

        chests.add(new WeaponChest(2800, 315, new Sword(2800, 315, null)));

        islands.get(0).addBackgroundObject("file:assets/BackgroundObj1.png", 50, 140);
        islands.get(0).addBackgroundObject("file:assets/BackgroundObj7.png", 400, 160);
    }

    // Layout of level shown for game demo (Deadline 2)
    private void setLevelDemo()
    {
        islands.add(new Island(50, 400, 250));
        islands.add(new Island(400, 300, 200));
        islands.add(new Island(675, 350, 275));
        islands.add(new Island(1100, 275, 300));

        islands.get(0).addBackgroundObject("file:assets/BackgroundObj1.png", 0, 95);
        islands.get(0).addBackgroundObject("file:assets/BackgroundObj2.png", 65, 60);
        islands.get(0).addBackgroundObject("file:assets/BackgroundObj3.png", 180, 120);

        islands.get(1).addBackgroundObject("file:assets/BackgroundObj4.png", 5, 110);
        islands.get(1).addBackgroundObject("file:assets/BackgroundObj5.png", 90, 55);

        islands.get(2).addBackgroundObject("file:assets/BackgroundObj3.png", 25, 80);
        islands.get(2).addBackgroundObject("file:assets/BackgroundObj1.png", 200, 100);

        islands.get(3).addBackgroundObject("file:assets/BackgroundObj4.png", 200, 120);

        orcs.add(new GreenOrc(470,150,50));
        orcs.add(new GreenOrc(800, 200, 50));
        orcs.add(new GreenOrc(875, 100, 50));
//        orcs.add(new BossOrc(1075,75,50));

//        chests.add(new CoinChest(450, 240));
//        chests.add(new CoinChest(710, 290));
        chests.add(new CoinChest(1200, 215, 5));
        chests.add(new WeaponChest(710, 290, new ThrowingKnives(710, 290, null)));

        coins.add(new Coin(625, 290));
        coins.add(new Coin(625, 230));
    }

    public void createGhostHero(Hero heroToFollow)
    {
        ghostHero = new GhostHero(heroToFollow);
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
    public GhostHero getGhostHero() {
        return ghostHero;
    }
}