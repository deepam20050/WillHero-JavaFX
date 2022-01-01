import java.lang.reflect.Array;
import java.util.ArrayList;

public class FallingPlatforms extends GameObject {
    private ArrayList < Plank > planks;
    private int totalNoOfPlanks = 6;
    private final double lengthOfPlank = 40;
    private final double fallVelocity = 20;
    private boolean isLanded;
    private double length;
    FallingPlatforms (double x, double y) {
        super(new Vector2D(x, y), new Vector2D(0, 0));
        this.isLanded = false;
        planks = new ArrayList<Plank>();
        double xStart = x;
        for (int i = 0; i < totalNoOfPlanks; ++i) {
            planks.add(new Plank(xStart, y, lengthOfPlank, fallVelocity));
            xStart += lengthOfPlank;
        }
        this.length = this.lengthOfPlank * this.totalNoOfPlanks;
    }
    public ArrayList < Plank > getPlanks () {
        return planks;
    }
    public void if_collides_hero (Hero hero) throws InterruptedException {
        if (planks.isEmpty()) return;
        boolean landed = false;
        for (Plank x : planks) {
            landed |= x.if_collides_hero(hero);
        }
        if (landed == false && this.isLanded == false) return;
        this.isLanded = true;
        if (landed) {
            hero.setVelocity(0, -10);
        }
        if (planks.isEmpty()) return;
        System.out.println("Touched a plank");
        planks.get(0).fall();
        planks.get(0).setActive(false);
        planks.remove(0);
    }
    @Override
    public void if_collides(Hero hero) {}
    @Override
    public void updateFrame(double cameraPosition) {
        getImageView().setX(getPosition().getX() - cameraPosition);
        for (Plank x : planks) {
            x.updateFrame(cameraPosition);
        }
    }
}
