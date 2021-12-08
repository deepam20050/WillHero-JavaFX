public class RedOrc extends Orc{
  public RedOrc (double x, double y, double _jump_speed, double _size, int _hits_required) {
    /* For now jump_speed, size, hits_required initialized to 1
     * Can change if required
     */
    super(x, y,  1, 1, 1);
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
