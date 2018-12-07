package application;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	  protected static ArrayList<AudioInputStream> aisList = new ArrayList<AudioInputStream>();
	  protected static ArrayList<String> aisFile = new ArrayList<String>();

	  //protected Clip sound;
	  protected Clip sound;
	  protected String filename;


	  /**
	   * Creates a new sound out of the given file. Must be a .wav file.
	   * 
	   * While this constructor is available for usage, it is highly recommended that you do not use this.
	   * Instead call EZ.addSound() method which will perform additional background actions to bind the sound to the window.
	   * 
	   * @param file of the sound to load.
	   * */
	  public Sound(String file) {
	    /*
	    filename = file;
	    sound = tryLoadSound(file);
	    if (sound == null) {
	      System.out.println("Error loading sound file");
	      System.exit(1);
	    }
	    if (sound == null) {
	      reloadClip();
	    }
	    */
	    try {
	      AudioInputStream ais = AudioSystem.getAudioInputStream(new File(file).getAbsoluteFile());
	      sound = AudioSystem.getClip();
	      sound.open(ais);
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      System.out.println("Error loading sound file, it may not exist or another program has a lock on it.");
	      System.exit(1);
	    }
	  }// end constructor


	  /**
	   * This will play the sound file from wherever the current position is.
	   * 
	   */
	  public void play() {
	    if( sound.getFramePosition() == sound.getFrameLength()  || 
	        (sound.getFramePosition() != 0 && sound.isRunning()) ) {
	      sound.setFramePosition(0);
	    }
	    sound.start();
	  }

	  /**
	   * This will stop the sound and reset back to the start.
	   */
	  public void stop() {
	    sound.stop();
	    sound.setFramePosition(0);
	  } // end stop()
	  
	  /**
	   *  Will pause the sound at it's current position. Using play() will resume from this point.
	   */
	  public void pause() {
	    sound.stop();
	  }

	  /**
	   * Will play from the start and loop the sound... again... and again... and again...
	   * 
	   */
	  public void loop() {
	    sound.setFramePosition(0);
	    sound.loop(Clip.LOOP_CONTINUOUSLY);
	  }
	  
	  /** 
	   * Returns how many frames are held within this sound file.
	   * @return Positive int value including zero indicating number of frames.
	   * Otherwise -1 to indicate that the file's length cannot be determined.
	   */
	  public int getFrameLength() {
	    return sound.getFrameLength();
	  }
	  
	  /**
	   * Returns the current frame of the sound file.
	   * @return Positive int value including zero indicating the current frame.
	   */
	  public int getFramePosistion() {
	    return sound.getFramePosition();
	  }
	  
	  /**
	   * Returns the total length of the sound file in microseconds.
	   * @return Positive long value including zero indicating the length of the sound file.
	   * Otherwise -1 to indicate the file's length cannot be determined.
	   */
	  public long getMicroSecondLength() {
	    return sound.getMicrosecondLength();
	  }
	  
	  /**
	   * Returns the current position in microseconds.
	   * @return Positive long value including zero indicating the position in the sound file.
	   * Otherwise -1 to indicate the file's position cannot be determined.
	   */
	  public long getMicroSecondPosition() {
	    return sound.getMicrosecondPosition();
	  }
	  
	  /**
	   * Sets the position in frames from which to continue playing.
	   * This will be overridden if stop() or loop() is called after this(they reset back to start).
	   * @param pos frame of the file to start from.
	   */
	  public void setFramePosition(int pos) {
	    sound.setFramePosition(pos);
	  }

	  
	  /**
	   * Sets the position in microseconds from which to continue playing.
	   * This will be overridden if stop() or loop() is called after this(they reset back to start).
	   * Note: the level of precision is based upon ms per frame.
	   * @param pos milliseconds of the file to start from.
	   */
	  public void setMicrosecondPosition(int pos) {
	    sound.setMicrosecondPosition(pos);
	  }
	  
	  
	} // end class

