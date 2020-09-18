package theGame;
import java.io.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import sun.audio.*;


public class BGM 
{
	String filename;
	
	public BGM(String filename)
	{
		//in order for this code to work you must change your forbiden references: 
		//Window -> Preferences -> Java -> Compiler -> Errors/Warnings -> Deprecated and restricted API -> Forbidden reference (access rules): -> change to warning
		this.filename = filename;
	
	}
	public void Play(){	
		AudioPlayer musicPlayer = AudioPlayer.player;
		AudioStream bgm;
		AudioData m;
		ContinuousAudioDataStream loops = null; 
		
		try{
			InputStream getFile = new FileInputStream(filename +".wav"); //gets the audio file
			bgm = new AudioStream(getFile);
			AudioPlayer.player.start(bgm);
		}
		catch(FileNotFoundException e){e.getStackTrace();}
		catch(IOException e){e.getStackTrace();}
		musicPlayer.start(loops); 
		
		//starts music player
		
	}


}
