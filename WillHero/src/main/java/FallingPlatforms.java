import java.util.ArrayList;

public class FallingPlatforms extends GameObject implements Obstacle {
    private ArrayList < Plank > planks;
    private int totalNoOfPlanks = 6;
    private final double lengthOfPlank = 40;
    private boolean droppedOff;
    private boolean isLanded;
    private double frames;
    FallingPlatforms (double x, double y) {
        super(new Vector2D(x, y), new Vector2D(0, 0));
        this.isLanded = false;
        planks = new ArrayList<>();
        double xStart = x;
        for (int i = 0; i < totalNoOfPlanks; ++i) {
            planks.add(new Plank(xStart, y, lengthOfPlank));
            xStart += lengthOfPlank;
        }
        this.droppedOff = false;
    }
    public ArrayList < Plank > getPlanks () {
        return planks;
    }
    @Override
    public void if_collides (Hero hero) {
        if (planks.isEmpty()) return;
        boolean landed = false;
        for (Plank x : planks) {
            landed |= x.if_collides_hero(hero);
        }
        if (landed == false && this.isLanded == false) return;
        if (landed) {
            hero.setVelocity(0, -10);
        }
        this.isLanded = true;
        if (this.droppedOff == false) {
            planks.get(0).fall();
            planks.remove(0);
            this.droppedOff = true;
            this.frames = 0;
        } else {
            if (this.frames < 0.5) return;
            planks.get(0).fall();
            planks.remove(0);
            this.frames = 0;
        }
    }
    @Override
    public void update_obs_state () {
        this.frames += 1 / WillHero.frameRate;
    }
    @Override
    public void updateFrame(double cameraPosition) {
        update_obs_state();
        getImageView().setX(getPosition().getX() - cameraPosition);
        for (Plank x : planks) {
            x.updateFrame(cameraPosition);
        }
    }
}
