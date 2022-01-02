import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable
{
//    private Player player;
    private ArrayList<Player> listOfPlayers;
    private Level level;
    private String gameMode;
    private int coinsForResurrection;
    private boolean isPaused;
    private boolean gameLost;
    private boolean gameWon;
    private boolean resurrected;

    // Camera Properties
    private double cameraPosition;
    private double cameraVelocity;

    public Game()
    {
        this("Regular");
    }
    public Game(String gameMode)
    {
        this.gameMode = gameMode;

//        player = new Player(this);
        listOfPlayers = new ArrayList<Player>();
        listOfPlayers.add(new Player(this, 1));
        if(gameMode.equals("Multiplayer"))
            listOfPlayers.add(new Player(this, 2));

        if(gameMode.equals("Flappy Hero"))
            level = new Level("Flappy Hero");
        else
            level = new Level();

        System.out.println(gameMode);
        coinsForResurrection = 20;
        isPaused = false;
        gameLost = false;
        gameWon = false;
        resurrected = false;

        cameraPosition = 0;
        cameraVelocity = 1;

        if(gameMode.equals("TimeChallenge"))
        {
            level.createGhostHero(getPlayer().getHero());
        }
    }

    public void save_file(int fileNo) {
        System.out.println("Saving File " + fileNo);
    }
    public void load_file(int fileNo) {
        System.out.println("Loading File " + fileNo);
    }
    public void serialise() {}
    public void deserialise() {}

    public Player getPlayer()
    {
        return listOfPlayers.get(0);
    }
    public ArrayList<Player> getListOfPlayers() {
        return listOfPlayers;
    }
    public Level get_current_level()
    {
        return level;
    }
    public void play() {}
    public void pause() {
        System.out.println("Game Paused");
        isPaused = true;
    }
    public void restart() {}
    public void resume() {
        System.out.println("Game Resumed");
        isPaused = false;
    }
    public void lose_game()
    {
        gameLost = true;
    }
    public void win_game ()
    {
        this.gameWon = true;
    }
    public void resurrect_hero()
    {
        for(Player player: listOfPlayers)
        {
            player.add_coins(-coinsForResurrection);
            player.getHero().getPosition().setY(50);
            player.getHero().setVelocity(0, 0);
        }
        gameLost = false;
        isPaused = false;
        resurrected = true;
    }

    public void updateCamera()
    {
        double minPos = getPlayer().getHero().getPosition().getX();
        for(Player player: listOfPlayers)
        {
            if(player.getHero().getPosition().getX() < minPos)
            {
                minPos = player.getHero().getPosition().getX();
            }
        }

        if(this.getPlayer().getHero().getCurrentPowerUp() instanceof Feather)
        {
            cameraVelocity = Feather.flySpeed;
        }
        else if(minPos - cameraPosition <= 100)
            cameraVelocity = 0;
        else if(minPos - cameraPosition <= 250)
            cameraVelocity = 2;
        else if(minPos - cameraPosition >= WillHero.sceneWidth - 100)
            cameraPosition = this.getPlayer().getHero().getPosition().getX() - WillHero.sceneWidth + 100;
        else
            cameraVelocity = 7;
        cameraPosition += cameraVelocity;
    }

    public int getCoinsForResurrection() {
        return coinsForResurrection;
    }
    public String getGameMode() {
        return gameMode;
    }
    public boolean isPaused() {
        return isPaused;
    }
    public boolean isGameLost() {
        return gameLost;
    }
    public boolean isGameWon() {
        return gameWon;
    }
    public boolean isResurrected() {
        return resurrected;
    }
    public double getCameraPosition() {
        return cameraPosition;
    }
}
