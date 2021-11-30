public class InputTracker
{
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;

    public InputTracker()
    {
        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
    }

    public void setLeftPressed(boolean isPressed) {
        leftPressed = isPressed;
    }
    public void setRightPressed(boolean isPressed) {
        rightPressed = isPressed;
    }
    public void setUpPressed(boolean isPressed) {
        upPressed = isPressed;
    }
    public void setDownPressed(boolean isPressed) {
        downPressed = isPressed;
    }

    public boolean getLeftPressed() {
        return leftPressed;
    }
    public boolean getRightPressed() {
        return rightPressed;
    }
    public boolean getUpPressed() {
        return upPressed;
    }
    public boolean getDownPressed() {
        return downPressed;
    }
}