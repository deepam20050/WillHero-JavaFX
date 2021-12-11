public class Player
{
    private Hero hero; // *** IMPLEMENT HERO CLASS ***
    private int noOfCoins;
    private Game game;

    // *** OTHER METHODS TO BE ADDED & IMPLEMENTED ***

    public Player()
    {
        hero = new Hero(this, 100, 300, 50);
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
        // *** ADD IMPLEMENTATION FOR LOSING GAME ***
        System.out.println("Player lost the game");
    }
    public void add_coins(int added_coins)
    {
        noOfCoins += added_coins;
    }
}
