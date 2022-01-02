import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Level implements Serializable
{
    private ArrayList<Island> islands;
    private ArrayList<Orc> orcs;
    private ArrayList<Coin> coins;
    private ArrayList<Chest> chests;
//    private ArrayList<Obstacle> obstacles;
    private ArrayList<FallingBridge> obstacles;
    private ArrayList<PowerUp> powerUps;
    private ArrayList<ShootingStar> shootingStars;

    private ArrayList<ArrayList<? extends GameObject>> allObjectsInLevel;
    private GhostHero ghostHero;

    public Level()
    {
        this("1");
    }
    // Default Constructor: Constructs default (first) level layout
    public Level(String levelName)
    {
        // Initialising Arraylists for all GameObjects
        islands = new ArrayList<Island>();
        orcs = new ArrayList<Orc>();
        coins = new ArrayList<Coin>();
        chests = new ArrayList<Chest>();
//        obstacles = new ArrayList<Obstacle>();
        obstacles = new ArrayList<FallingBridge>();
        powerUps = new ArrayList<PowerUp>();
        shootingStars = new ArrayList<ShootingStar>();

        allObjectsInLevel = new ArrayList<ArrayList<? extends GameObject>>();
        allObjectsInLevel.add(islands);
        allObjectsInLevel.add(orcs);
        allObjectsInLevel.add(coins);
        allObjectsInLevel.add(chests);
//        allObjectsInLevel.add(obstacles);
        allObjectsInLevel.add(powerUps);
        allObjectsInLevel.add(shootingStars);

        if(levelName.equals("1"))
        {
//            setLevelDemo();
            setLevel1();
        }
        else if(levelName.equals("Flappy Hero"))
        {
            setFlappyBirdLevel();
        }
    }

    private void setLevel1()
    {
        // Placing all landable objects in the stage (Islands and Falling Bridges)
        islands.add(new Island(50, 400, 500));
        islands.add(new Island(650, 350, 250));
        islands.add(new Island(1100, 350, 300));
        islands.add(new Island(1500, 275, 350));
        islands.add(new Island(1900, 375, 300));
        islands.add(new Island(2700, 375, 300));
        obstacles.add(new FallingBridge(3000, 375, 10));
        islands.add(new Island(3700, 375, 300));
        islands.add(new Island(4150, 300, 175));
        islands.add(new Island(4500, 225, 225));
        islands.add(new Island(4950, 225, 350));
        islands.add(new Island(5500, 275, 400));
        islands.add(new Island(6000, 300, 350));
        obstacles.add(new FallingBridge(6600, 300, 6));
        islands.add(new Island(6840, 300, 350));
        islands.add(new Island(7400, 300, 300));
        obstacles.add(new FallingBridge(7700, 300, 8));
        islands.add(new Island(8200, 350, 200));
        islands.add(new Island(8500, 425, 250));
        islands.add(new Island(8600, 225, 175));
        islands.add(new Island(9000, 125, 250));
        islands.add(new Island(8900, 400, 275));
        islands.add(new Island(9500, 325, 400));
        islands.add(new Island(10000, 325, 450));
        islands.add(new Island(10750, 325, 400));
        obstacles.add(new FallingBridge(11150, 325, 10));
        islands.add(new Island(12400, 325, 375));
        obstacles.add(new FallingBridge(12775, 325, 25));
        islands.add((new Island(13775, 325, 200)));

        orcs.add(new GreenOrc(1200, 100, 50));
        orcs.add(new GreenOrc(1300, 100, 50));
        orcs.add(new GreenOrc(1550, 250, 50));
        orcs.add(new GreenOrc(1950, 250, 50));
        orcs.add(new GreenOrc(2025, 100, 50));
        orcs.add(new GreenOrc(3200, 100, 50));
        orcs.add(new GreenOrc(3275, 150, 50));
        orcs.add(new GreenOrc(3350, 200, 50));
        orcs.add(new GreenOrc(4200, 100, 50));
        orcs.add(new GreenOrc(4500, 50, 50));
        orcs.add(new GreenOrc(4575, 75, 50));
        orcs.add(new RedOrc(5600, 100, 50));
        orcs.add(new RedOrc(6050, 100, 50));
        orcs.add(new RedOrc(6200, 125, 50));
        orcs.add(new RedOrc(6350, 150, 50));
        orcs.add(new RedOrc(6700, 150, 50));
        orcs.add(new GreenOrc(8600, 50, 50));
        orcs.add(new GreenOrc(9500, 175, 50));
        orcs.add(new GreenOrc(9560, 150, 50));
        orcs.add(new GreenOrc(9620, 100, 50));
        orcs.add(new GreenOrc(9680, 125, 50));
        orcs.add(new GreenOrc(9740, 160, 50));
        orcs.add(new GreenOrc(9800, 100, 50));
        orcs.add(new RedOrc(10000, 100, 50));
        orcs.add(new RedOrc(10060, 150, 50));
        orcs.add(new RedOrc(10120, 125, 50));
        orcs.add(new RedOrc(10180, 100, 50));
        orcs.add(new RedOrc(10240, 175, 50));
        orcs.add(new RedOrc(10300, 160, 50));
        orcs.add(new RedOrc(10360, 115, 50));
        orcs.add(new GreenOrc(11300, 100, 50));
        orcs.add(new GreenOrc(11400, 100, 50));
        orcs.add(new BossOrc(13175, 300, 150));

        powerUps.add(new Feather(9100, 25));

        chests.add(new WeaponChest(2800, 315, new Sword(2800, 315, null)));
        chests.add(new WeaponChest(5700, 215, new Sword(5700, 215, null)));
        chests.add(new WeaponChest(1650, 215, new ThrowingKnives(1650, 215, null)));
        chests.add(new WeaponChest(3800, 315, new ThrowingKnives(3800, 315, null)));
        chests.add(new WeaponChest(8250, 290, new ThrowingKnives(8250,  290, null)));
        chests.add(new CoinChest(750, 290, 5));
        chests.add(new CoinChest(5000, 165, 5));
        chests.add(new CoinChest(6900, 240, 5));

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

        coins.add(new Coin(3425, 300));
        coins.add(new Coin(3500, 300));
        coins.add(new Coin(3575, 300));

        coins.add(new Coin(4050, 180));

        coins.add(new Coin(4265, 165));
        coins.add(new Coin(4340, 165));
        coins.add(new Coin(4415, 165));

        coins.add(new Coin(6375, 105));
        coins.add(new Coin(6450, 105));
        coins.add(new Coin(6525, 105));
        coins.add(new Coin(6375, 180));
        coins.add(new Coin(6450, 180));
        coins.add(new Coin(6525, 180));

        coins.add(new Coin(11700, 160));
        coins.add(new Coin(11775, 160));
        coins.add(new Coin(11850, 160));
        coins.add(new Coin(11925, 160));
        coins.add(new Coin(12000, 160));
        coins.add(new Coin(12075, 160));
        coins.add(new Coin(12150, 160));
        coins.add(new Coin(12225, 160));
        coins.add(new Coin(12300, 160));
        coins.add(new Coin(11700, 235));
        coins.add(new Coin(11775, 235));
        coins.add(new Coin(11850, 235));
        coins.add(new Coin(11925, 235));
        coins.add(new Coin(12000, 235));
        coins.add(new Coin(12075, 235));
        coins.add(new Coin(12150, 235));
        coins.add(new Coin(12225, 235));
        coins.add(new Coin(12300, 235));

        islands.get(0).addBackgroundObject("file:assets/BackgroundObj1.png", 50, 140);
        islands.get(0).addBackgroundObject("file:assets/BackgroundObj7.png", 400, 160);

        islands.get(1).addBackgroundObject("file:assets/BackgroundObj4.png", 20, 125);
        islands.get(2).addBackgroundObject("file:assets/BackgroundObj5.png", 20, 75);
        islands.get(3).addBackgroundObject("file:assets/BackgroundObj5.png", 250, 100);
        islands.get(3).addBackgroundObject("file:assets/BackgroundObj2.png", 220, 60);
        islands.get(4).addBackgroundObject("file:assets/BackgroundObj2.png", 220, 100);
        islands.get(5).addBackgroundObject("file:assets/BackgroundObj2.png", 20, 120);
        islands.get(5).addBackgroundObject("file:assets/BackgroundObj5.png", 220, 60);

        islands.get(6).addBackgroundObject("file:assets/BackgroundObj3.png", 30, 120);
        islands.get(6).addBackgroundObject("file:assets/BackgroundObj4.png", 200, 100);
        islands.get(7).addBackgroundObject("file:assets/BackgroundObj6.png", 25, 50);
        islands.get(8).addBackgroundObject("file:assets/BackgroundObj1.png", 100, 120);
        islands.get(8).addBackgroundObject("file:assets/BackgroundOb6.png", 50, 50);
        islands.get(9).addBackgroundObject("file:assets/BackgroundObj7.png", 175, 120);
        islands.get(9).addBackgroundObject("file:assets/BackgroundObj5.png", 150, 60);
        islands.get(10).addBackgroundObject("file:assets/BackgroundObj3.png", 25, 100);
        islands.get(10).addBackgroundObject("file:assets/BackgroundObj4.png", 250, 120);
        islands.get(11).addBackgroundObject("file:assets/BackgroundObj1.png", 100, 120);
        islands.get(12).addBackgroundObject("file:assets/BackgroundObj2.png", 25, 50);
        islands.get(12).addBackgroundObject("file:assets/BackgroundObj4.png", 200, 120);
        islands.get(13).addBackgroundObject("file:assets/BackgroundObj1.png", 50, 130);
        islands.get(21).addBackgroundObject("file:assets/BackgroundObj1.png", 30, 120);
        islands.get(21).addBackgroundObject("file:assets/BackgroundObj5.png", 100, 50);
        islands.get(21).addBackgroundObject("file:assets/BackgroundObj7.png", 200, 130);
        islands.get(22).addBackgroundObject("file:assets/BackgroundObj4.png", 30, 120);
        islands.get(22).addBackgroundObject("file:assets/BackgroundObj7.png", 200, 150);
    }

    // Layout of level shown for game demo (Deadline 2)
    private void setLevelDemo()
    {
        islands.add(new Island(50, 400, 250));
        islands.add(new Island(400, 300, 200));
        islands.add(new Island(675, 350, 275));
//        islands.add(new Island(1100, 275, 300));

        islands.get(0).addBackgroundObject("file:assets/BackgroundObj1.png", 0, 95);
        islands.get(0).addBackgroundObject("file:assets/BackgroundObj2.png", 65, 60);
        islands.get(0).addBackgroundObject("file:assets/BackgroundObj3.png", 180, 120);

        islands.get(1).addBackgroundObject("file:assets/BackgroundObj4.png", 5, 110);
        islands.get(1).addBackgroundObject("file:assets/BackgroundObj5.png", 90, 55);

        islands.get(2).addBackgroundObject("file:assets/BackgroundObj3.png", 25, 80);
        islands.get(2).addBackgroundObject("file:assets/BackgroundObj1.png", 200, 100);

//        islands.get(3).addBackgroundObject("file:assets/BackgroundObj4.png", 200, 120);

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

        obstacles.add(new FallingBridge(1100, 275, 10));

//        powerUps.add(new Feather(850, 100));
    }

    private void setFlappyBirdLevel()
    {
        double gapBetweenPipes = 225;
        double gapBetweenLocations = 450;
        int numberOfLocations = 100;
        double startingLocation = 1000;

        islands.add(new Island(50, 400, 250));
        powerUps.add(new Feather(250, 250));

        for(int i = 0; i < 50; i++)
        {
            double xPositionOfPipes = startingLocation + i*gapBetweenLocations;
            double upperPipeHeight = Math.random() * (WillHero.sceneHeight - gapBetweenPipes);

            islands.add(new Pipe(xPositionOfPipes, upperPipeHeight, true));
            islands.add(new Pipe(xPositionOfPipes, upperPipeHeight + gapBetweenPipes, false));

            powerUps.add(new Feather(xPositionOfPipes + 25, upperPipeHeight + 60));
        }
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
    public ArrayList<FallingBridge> getObstacles() {
        return obstacles;
    }
    public ArrayList<PowerUp> getPowerUps() {
        return powerUps;
    }
    public ArrayList<ShootingStar> getShootingStars() {
        return shootingStars;
    }
    public ArrayList<ArrayList<? extends GameObject>>  getAllObjectsInLevel() {
        return allObjectsInLevel;
    }
    public GhostHero getGhostHero() {
        return ghostHero;
    }
}