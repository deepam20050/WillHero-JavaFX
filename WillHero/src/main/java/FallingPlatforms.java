import java.lang.reflect.Array;
import java.util.ArrayList;

public class FallingPlatforms extends GameObject {
    private ArrayList < Plank > planks;
    private int totalNoOfPlanks = 2;
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
    @Override
    public void if_collides(Hero hero) {
        if (isLanded == false) {
            boolean touched = false;
            for (Plank x : planks) {
                touched |= x.if_collides_hero(hero);
            }
            if (touched == false) return;
            isLanded = true;
        }
        if (planks.isEmpty()) return;
        planks.get(0).fall();
        planks.remove(0);
    }
    @Override
    public void updateFrame(double cameraPosition) {
        getImageView().setX(getPosition().getX() - cameraPosition);
        for (Plank x : planks) {
            x.updateFrame(cameraPosition);
        }
    }
}
