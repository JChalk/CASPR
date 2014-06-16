package com.sate.caspr;

import java.io.IOException;

import com.sate.caspr.MainActivity;
import com.sate.caspr.R;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener{

	//**************************************************************************
	//*							DECLARATIONS								   *
	//* The Majority of these Scripts were taken from javacodegeeks.com        *
	//**************************************************************************
	/**12
	 * Thread that houses the sound-based navigation
	 */
	Thread thread;
	
	/**
	 * MediaPlayer instance that plays the sound for forward
	 */
	MediaPlayer forward;
	
	/**
	 * MediaPlayer instance that plays the sound for reverse
	 */
	MediaPlayer reverse;
	
	/**
	 * mediaPlayer instance that plays the sound to turn
	 */
	MediaPlayer turn;
	
	/**
	 * STOP is only true when a.) Mission Objective Met (Not implemented)
	 * 		  b.) Application goes into pause
	 */
	boolean STOP = false;
	
	/**
	 * OBSTRUCTION is true when the sensor detects an obstruction
	 */
	boolean OBSTRUCTION = false;
	
	SensorManager sensorManager;
	Sensor proximitySensor;		
	
	//**************************************************************************
	//**************************************************************************
	    
    /**
     * Standard onCreate method that details the GUI. Also instantiates earlier
     * declarations. Sets up sensors, and the sound playing thread.
     */
    @Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);       
        
        forward = MediaPlayer.create(this, R.raw.movefoward);
        turn = MediaPlayer.create(this, R.raw.turningright);
        reverse = MediaPlayer.create(this, R.raw.movebackward);
        
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        
        //Thread handles playing the sounds
		thread = new Thread(){
				
			@Override
			public void run(){
				
				while(!STOP){
					if(OBSTRUCTION == false){
						moveForward();
					}
							
					else if(OBSTRUCTION == true){
						turn();
					}
				}
			}
		};
				thread.start();
	}
        
    /**
     * Backs up the robot and turns
     */
    public void turn(){
    	
    	forward.stop();
		reverse.start();
		try{
			Thread.sleep(500);
			reverse.stop();
			reverse.prepare();
		} catch (Exception e) {
			e.printStackTrace();
		}
		turn.start();
		try{
			Thread.sleep(1400);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
									
		try {
			forward.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    /**
     * Moves robot forward.
     */
    public void moveForward(){
    	
    	forward.start();
		try {
			Thread.sleep(250);
			forward.stop();
			forward.prepare();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	/**
	 * UNIMPLEMENTED
	 */
    @Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {/*UNIMPLEMENTED*/}
	
	/**
	 * Sensor value reads high or low. High value changes per device,
	 */
	@Override
	public void onSensorChanged(SensorEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.values[0] == 0){
			
			OBSTRUCTION = true;
			Toast.makeText(MainActivity.this,"Stopping",Toast.LENGTH_SHORT).show();
			
		}
		else{
			
			OBSTRUCTION = false;
			Toast.makeText(MainActivity.this, "Starting", Toast.LENGTH_SHORT).show();
		}	
	}
	
	/**
	 * Unregisters sensor listener and stops the movement loop
	 */
	@Override
	protected void onPause(){
		super.onPause();
		sensorManager.unregisterListener(this);
		STOP = true;
	}
	
	/**
	 * Registers sensor listener and restarts the movement loop
	 */	
	@Override
	protected void onResume(){
		super.onResume();
		sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
		STOP = false;
	}
}