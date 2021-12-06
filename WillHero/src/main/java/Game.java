import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Game {
    private Player player;
    private Level level; // *** IMPLEMENT LEVEL CLASS ***

    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Game()
    {
        player = new Player();
        level = new Level();
    }

    // *** BELOW METHODS TO BE IMPLEMENTED ***

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
    public void pause() {}
    public void restart() {}
    public void resume() {}
    public void resurrect_hero() {}
    public void save_file() {}
    public void load_file() {}
}
