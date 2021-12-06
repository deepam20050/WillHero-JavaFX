public class Orc extends GameObject {
  private double jump_speed;
  private double size;
  private int hits_required;

  public Orc (double _jump_speed, double _size, double _hits_required) {
    jump_speed = _jump_speed;
    size = _size;
    hits_required = _hits_required;
  }

  public void is_attacked () {

  }
  public void if_falls () {

  }
  public void jump_up () {

  }
  public void if_lands (island Island) {

  }

  public double get_size () {
    return size;
  }
  
  @Override
  public void if_collides (hero Hero) {

  }
}