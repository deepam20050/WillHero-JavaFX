public class InputTracker
{
    private boolean spacePressed;
    private boolean zPressed;
    private boolean leftMousePressed;

    public InputTracker()
    {
        spacePressed = false;
        leftMousePressed = false;
    }

    public void setSpacePressed(boolean isPressed)
    {
        spacePressed = isPressed;
    }
    public void setZPressed(boolean isPressed) {
        zPressed = isPressed;
    }
    public void setLeftMousePressed(boolean isPressed)
    {
        leftMousePressed = isPressed;
    }

    public boolean isSpacePressed()
    {
        return spacePressed;
    }
    public boolean isZPressed() {
        return zPressed;
    }
    public boolean isLeftMousePressed()
    {
        return leftMousePressed;
    }
}
