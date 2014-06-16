package com.sate.caspr;

/* import java.io.IOException;
*
* import android.bluetooth.BluetoothAdapter;
* import android.bluetooth.BluetoothDevice;
* import android.bluetooth.BluetoothSocket;
*
* import java.util.UUID;
*
* public class BLClient extends Thread{
* 	private final BluetoothSocket mySocket;
* 	private final BluetoothDevice myDevice;
* 	private BluetoothAdapter myBluetoothAdapter;
* 	public static UUID MY_UUID = UUID.fromString(uuid)
*	
*	
*	public BLClient(BluetoothDevice device) {
*		BluetoothSocket tmp = null;
*		myDevice = device;
*		
*		try {
*			tmp = device.createRfcommSocketToServiceRecord(MY_UUID)
*		}
*		catch (IOException e) {}
*		mySocket = tmp;
*	}
*	
*	public void run() {
*		myBluetoothAdapter.cancelDiscovery();
*		
*		try {
*			mySocket.connect();
*		} catch (IOException connectException) {
*			try {
*				mySocket.close();
*			} catch (IOException closeException) {}
*			return;
*		}	
*	
*		manageConnectedSocket (mySocket);
*	}
*	
*	public void cancel() {
*		try {
*			mySocket.close();
*		} catch (IOException e) {}
*	}
*
* }
*/