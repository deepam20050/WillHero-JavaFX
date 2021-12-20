import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Game {
    private Player player;
    private Level level;
    private boolean isPaused;
    private boolean gameLost;
    private boolean resurrected;

    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Game()
    {
        player = new Player(this);
        level = new Level();
        isPaused = false;
        gameLost = false;
        resurrected = false;
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
    public void resurrect_hero() {
        player.getHero().getPosition().setY(50);
        player.getHero().setVelocity(0,0);
        gameLost = false;
        isPaused = false;
        resurrected = true;
    }
    public void save_file() {}
    public void load_file() {}

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
