/* package com.sate.caspr;

* import java.io.IOException;

* import android.bluetooth.BluetoothAdapter;
* import android.bluetooth.BluetoothServerSocket;
* import android.bluetooth.BluetoothSocket;

* import java.util.*;

* import com.sate.caspr.Bluetooth;

* public class BLServer extends Thread {
* 	public final BluetoothServerSocket myServerSocket;
*	public BluetoothAdapter myBluetoothAdapter;
*	
*	public BLServer() {
*		BluetoothServerSocket tmp = null;
*		try {
*			tmp = myBluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
*		} catch (IOException e) {}
*		myServerSocket = tmp;
*	}
*	
*	public void run() {
*		BluetoothSocket socket = null;
*		while (true) {
*			try {
*				socket = myServerSocket.accept();
*			} catch (IOException e) {
*				break;
*			}
*			
*			if (socket != null) {
*				myConnectedSocket = new manageConnectedSocket(socket);
*				try {
*					myServerSocket.close();
*				} catch (IOException e) { }
*				break;
*			}
*		}
*	}
*	
*	public void cancel() {
*		try {
*			myServerSocket.close();
*		} catch (IOException e) {}
*	}
* }
*/