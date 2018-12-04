package animation;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import sun.applet.Main;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/** A class to represent sprites in game (non-animated).
 *  What should a sprite be able to do?
 * 
 *
 *
 *
 *
 *
 */
public class Sprite {

    /*Properties of each sprite */
    /**The image for this sprite */
    private Image spriteImage;
    /** The Graphics object for this sprite's image */
    private Graphics2D spriteImageG2D;
    
    /**The position of this Sprite, as it should be drawn on the backbuffer.
     The position is of type double, as AffineTransform operates on double primitives,
     with double precision. Of course, the screen's pixel coordinates are ints, but the
     default draw methods handle that. */
    private double spriteXPosition;
    private double spriteYPosition;
    private int spriteHeight;
    private int spriteWidth;

    /** The matrix transform responsible for geometric transformations to the sprite */
    private AffineTransform spriteTransform;
    
    /**The double buffer onto which this Sprite object should draw itself */
    BufferedImage spriteDoubleBuffer;
    /**The double buffer's graphics context */
    Graphics2D spriteDoubleBufferG2D;


    /**Construct a sprite template, and initialize its instance variables later. */



    /**Construct a complete sprite */
    public Sprite(int width, int height, BufferedImage doubleBuffer){
        /*Position is set with setters */
        this.spriteXPosition = 0;
        this.spriteYPosition = 0;
        this.spriteWidth = width;
        this.spriteHeight = height;
        this.spriteDoubleBufferG2D = (Graphics2D) doubleBuffer.getGraphics();
        this.spriteTransform = new AffineTransform();
        //try this
        //this.spriteImageG2D = (Graphics2D)this.spriteImage.getGraphics();

    }





    

 /*Getters and setters */
    public Graphics2D getSpriteDoubleBufferG2D() {
        return spriteDoubleBufferG2D;
    }

//    public void setSpriteDoubleBufferG2D(Graphics2D spriteDoubleBufferG2D) {
//        this.spriteDoubleBufferG2D = spriteDoubleBufferG2D;
//    }

    /** Translate the underlying matrix for this sprite, based on the sprite's
     * set x and y coordinates.
     * 
     */
    public void transform(){
               
        this.spriteTransform.setToIdentity(); //resets this transform to the identity transform (I guess 3z3 0's matrix)
        //translate based on the location of the ImageEntity (its X and Y) and the dimensions of the
        //image itself
        this.spriteTransform.translate((int)this.getSpriteXPosition(), (int)this.getSpriteYPosition());

        

    }

    /** Draw this Sprite, based on its image object and affinetrasnform for
     *  location/transformation operations that have been done to it on the
     *  backbuffer.
     *
     */
    public void draw(){
        this.transform();
        //what should be the ImageObserver argument? The backbuffer or the JFrame??
        //this.spriteDoubleBufferG2D.drawImage(this.getSpriteImage(), spriteTransform, null);
        this.spriteDoubleBufferG2D.drawImage(this.getSpriteImage(), spriteTransform,  Main.getInstance());
        //this.spriteDoubleBufferG2D.drawImage(spriteImage, 0, 0, null);
    }


    
    /** Loads the specified image from the Sprites folder of the project. The method
     *  uses Toolkit's createImage() method, and hence can only load .GIF .JPG or .PNG
     *  image types.
     *
     * @param name the full name of the image, including the extension.
     * @return
     */
    public void loadSpriteImage(String name) {
        this.setSpriteImage(Toolkit.getDefaultToolkit().getImage("src/Sprites/"+name));
        
    }

    public Image getSpriteImage() {
        return spriteImage;
    }

    public void setSpriteImage(Image spriteImage) {
        this.spriteImage = spriteImage;
    }

    public Graphics2D getSpriteImageG2D() {
        return spriteImageG2D;
    }

    public void setSpriteImageG2D(Graphics2D spriteImageG2D) {
        this.spriteImageG2D = spriteImageG2D;
    }

    
    //==============================================================

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public void setSpriteHeight(int spriteHeight) {
        this.spriteHeight = spriteHeight;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public void setSpriteWidth(int spriteWidth) {
        this.spriteWidth = spriteWidth;
    }

    public double getSpriteXPosition() {
        return spriteXPosition;
    }

    public void setSpriteXPosition(double spriteXPosition) {
        this.spriteXPosition = spriteXPosition;
    }

    public double getSpriteYPosition() {
        return spriteYPosition;
    }

    public void setSpriteYPosition(double spriteYPosition) {
        this.spriteYPosition = spriteYPosition;
    }


}