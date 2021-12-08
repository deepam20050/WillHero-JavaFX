public class BossOrc extends Orc {
  public BossOrc (double x, double y, double _jump_speed, double _size, int _hits_required) {
    /* For now jump_speed, size, hits_required initialized to 2, 5, 5 respectively
     * Can change if required
     */
    super(x, y, 2, 5, 5);
  }
  @Override
  public void if_collides (Hero hero) {

  }
  @Override
  public void if_falls () {

  }
  @Override 
  public void is_attacked () {
    
  }
}
