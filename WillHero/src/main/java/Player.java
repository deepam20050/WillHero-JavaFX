import java.io.Serializable;

public class Player implements Serializable
{
    private int playerNo;
    private Hero hero;
    private int noOfCoins;
    private Game game;

    public Player(Game game)
    {
        this(game, 1);
    }
    public Player(Game game, int playerNo)
    {
        this.playerNo = playerNo;
        this.game = game;
        String controlKey = null;
        if(playerNo == 1)
            controlKey = "MOUSE";
        else if(playerNo == 2)
            controlKey = "Z";
        hero = new Hero(this, 100 + (playerNo-1)*75, 300, 50, playerNo, controlKey);
        noOfCoins = 0;
    }

    public Hero getHero()
    {
        return hero;
    }
    public void enter_screen() {}
    public void play() {}
    public void pause() {}
    public void restart() {}
    public void resume() {}
    public void resurrect_hero() {}
    public void save_file() {}
    public void load_file() {}

    public void lose_game()
    {
        game.lose_game();
        System.out.println("Player lost the game");
    }
    public void add_coins(int added_coins)
    {
        noOfCoins += added_coins;
    }

    public int getNoOfCoins()
    {
        return noOfCoins;
    }

    public Game getGame()
    {
        return game;
    }
}