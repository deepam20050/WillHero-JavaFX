import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

public class Blade extends GameObject {
    private Rectangle rect;
    private final double width = 300;
    private final double height = 100;
    private Rotate rect_rotate, image_rotate;
    private final String imagePath;
    private static double angle = 0;
    Blade (double x, double y, double pivot_x, double pivot_y) {
        super(new Vector2D(x, y), new Vector2D(0, 0));

        rect = new Rectangle();
        rect.setFill(Color.BLUE);
//        rect.setFill(null);
        rect.setX(x);
        rect.setY(y);
        rect.setWidth(width);
        rect.setHeight(height);

        imagePath = "file:assets/WindmillFanSprite.png";
        setImage(new Image(imagePath));
        getImageView().setX(x);
        getImageView().setY(y);
        getImageView().setFitHeight(width);
        getImageView().setFitHeight(height);

        rect_rotate = new Rotate();
        rect_rotate.setPivotX(pivot_x);
        rect_rotate.setPivotY(pivot_y);
        rect.getTransforms().add(rect_rotate);

        image_rotate = new Rotate();
        image_rotate.setPivotX(pivot_x);
        image_rotate.setPivotY(pivot_y);
        getImageView().getTransforms().add(image_rotate);
    }
    public void rotateBlade () {
        rect.setRotate(rect.getRotate() + 1);
        getImageView().setRotate(getImageView().getRotate() + 1);
//        rect_rotate.setAngle(angle);
//        image_rotate.setAngle(angle);
//        angle = (angle + 1) % 360;
    }
    @Override
    public void if_collides (Hero hero) {
        Rectangle hero_rect = new Rectangle();
        hero_rect.setX(hero.getPosition().getX());
        hero_rect.setY(hero.getPosition().getY());
        hero_rect.setWidth(hero.getSize());
        hero_rect.setHeight(hero.getSize());
        Shape collide = Shape.intersect(hero_rect, rect);

        if (collide.getBoundsInLocal().getWidth() != -1) {
            System.out.println("Collision with windmill");
            hero.lose_game();
        }
    }
    public void addInChildren (Group root) {
        root.getChildren().addAll(rect);
        root.getChildren().addAll(getImageView());
    }
    @Override
    public void updatePosition (double cameraPosition) {
//        this.setPosition(getPosition().getX() + getVelocity().getX(), getPosition().getY() + getVelocity().getY());
        rect.setX(this.getPosition().getX() - cameraPosition);
        getImageView().setX(this.getPosition().getX() - cameraPosition);
    }
//    private Rotate rotate;
//    private String imagePath;
//    private double height;
//    private double angle;
//
//    Blade (double x, double y, double px, double py) {
//        super(new Vector2D(x, y), new Vector2D(0, 0));
//        angle = 90.0;
//        height = 30;
//        imagePath = "file:assets/WindmillFanSprite.png";
//        setImage(new Image(imagePath));
//        getImageView().setX(x);
//        getImageView().setY(y);
//        getImageView().setPreserveRatio(true);
//    }
//    @Override
//    public void if_collides(Hero hero) {
////        if (this.getBoundary().intersects(getBoundary(hero))) {
////            hero.lose_game();
////        }
//        if (hero.getImageView().getBoundsInParent().intersects(getImageView().getBoundsInParent())) {
//            System.out.println("hero touched windmill :/");
//            hero.lose_game();
//        }
//    }
//    public void rotateBlade () {
//        getImageView().setRotate(getImageView().getRotate() + 1);
//    }
}
