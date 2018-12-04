package animation;

public class AnimatedSprite extends Sprite {

    public int frameNumber;
    public int frameWidth;
    public int frameHeight;
    public int frameX, frameY;
    public int totalWidth;
    public int totalHeight;
    public int totalFrames;
    //holds the animation strip
    private Image animimage;
    /*a delay which has to be overcome for the frame of this
    animated strip to be painted, used to untie animation rate from fps set in main loop */
    public int frameDelay;
    public int frameTicker;


    public AnimatedSprite2(int totalFrames, int frameWidth, int frameHeight, int totalWidth, int totalHeight, BufferedImage doubleBuffer) {
        super(totalWidth, totalHeight, doubleBuffer);
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.frameX = 0;
        this.frameY = 0;
        this.frameNumber = 0;
        this.totalFrames = totalFrames;
        this.totalWidth = totalWidth;
        this.totalHeight = totalHeight;
        /*If the delay in the Main class is 50ms, and frameDelay is 5, logically,
         a new frame will be shown every 50ms*5 = 250ms, every quarter of a second.
         See updateAnimation() method*/
        this.frameDelay = 5;
    }

    /** Loads the specified image from the Sprites folder of the project. The method
     *  uses Toolkit's createImage() method, and hence can only load .GIF .JPG or .PNG
     *  image types.
     *
     * @param name the full name of the image, including the extension.
     * @return
     */
    @Override
    public void loadSpriteImage(String name) {
        animimage = Toolkit.getDefaultToolkit().getImage("src/Sprites/" + name);


    }