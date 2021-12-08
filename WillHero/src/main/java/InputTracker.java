public class InputTracker
{
    private boolean spacePressed;

    public InputTracker()
    {
        spacePressed = false;
    }

    public void setSpacePressed(boolean isPressed)
    {
        spacePressed = isPressed;
    }

    public boolean isSpacePressed()
    {
        return spacePressed;
    }
}
