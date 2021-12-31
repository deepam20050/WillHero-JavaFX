import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Game {
    private Player player;
    private Level level;
    private String gameMode;
    private int coinsForResurrection;
    private boolean isPaused;
    private boolean gameLost;
    private boolean resurrected;

    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Game()
    {
        this("Regular");
    }
    public Game(String gameMode)
    {
        player = new Player(this);
        if(gameMode.equals("Flappy Hero"))
            level = new Level("Flappy Hero");
        else
            level = new Level();
        this.gameMode = gameMode;
        System.out.println(gameMode);
        coinsForResurrection = 20;
        isPaused = false;
        gameLost = false;
        resurrected = false;

        if(gameMode.equals("TimeChallenge"))
        {
            level.createGhostHero(player.getHero());
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
        return player;
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
    public void resurrect_hero()
    {
        player.add_coins(-coinsForResurrection);
        player.getHero().getPosition().setY(50);
        player.getHero().setVelocity(0,0);
        gameLost = false;
        isPaused = false;
        resurrected = true;
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
    public boolean isResurrected() {
        return resurrected;
    }
}
