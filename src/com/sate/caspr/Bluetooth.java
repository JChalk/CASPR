package com.sate.caspr;

import java.util.Set;

import com.sate.caspr.R;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
// import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Bluetooth extends Activity{

	public static final int REQUEST_ENABLE_BT = 1;
	public BluetoothAdapter myBluetoothAdapter;
	public Button onBtn;
	public Button offBtn;
	public Button listBtn;
	public Button findBtn;
	public Button discover;
	public TextView text;
	public Set<BluetoothDevice> pairedDevices;
	// private ListView myListView;
	public ArrayAdapter<String> BTArrayAdapter;
	public IntentFilter Filter;
	public BroadcastReceiver Receiver;
	public Intent discoverableIntent;
		
		@Override
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);  
	        
	        // Settings that check for Bluetooth.
	           myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	           if(myBluetoothAdapter == null) {
	         	  	onBtn.setEnabled(false);
	         	  	offBtn.setEnabled(false);
	         	  	listBtn.setEnabled(false);
	         	  	findBtn.setEnabled(false);
	         	  	text.setText("Status: not supported");

	         	  	Toast.makeText(getApplicationContext(),"Your device does not support Bluetooth",Toast.LENGTH_LONG).show();
	           }
	         	  	
	         	else { 
	         		text = (TextView) findViewById(R.id.text);
		    	    onBtn = (Button)findViewById(R.id.turnOn);
		    	    onBtn.setOnClickListener(new OnClickListener() {
		    	    	// Turn On Bluetooth Adapter.
		    	    	public void onClick(View v){
			         		myBluetoothAdapter.enable();
			         		text.setText("Status: Connected");
			         		Toast.makeText(getApplicationContext(),"Turning on Bluetooth...",Toast.LENGTH_LONG).show();
		    	    	}
		    	    });
	         		
		    	    text = (TextView) findViewById(R.id.text);
		    	    offBtn = (Button)findViewById(R.id.turnOff);
		    	    offBtn.setOnClickListener(new OnClickListener() {
		    	  		// Turn Off Bluetooth Adapter.
		    	    	public void onClick(View v) {
		    	  			if (myBluetoothAdapter.isEnabled()){
		    	  				myBluetoothAdapter.disable();
		    	  				text.setText("Status: Disconnected");
		    	  				Toast.makeText(getApplicationContext(),"Turning off Bluetooth...",Toast.LENGTH_LONG).show();
		    	  			}
		    	  		}
	         	});
		    	    discover = (Button)findViewById(R.id.search);
		    	    discover.setOnClickListener(new OnClickListener() {
		    	    	// Enable the Discovery Mode that allows others to find your device.
		    	    	public void onClick(View v) {
		    	    		discoverableIntent = new		
		    	    		Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		    	    		discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
		    	    		startActivity(discoverableIntent);
		    	    	}
		    	    });
	       	}
	    }
		
		
		public void list(View view){
			     	  // get paired devices
			           pairedDevices = myBluetoothAdapter.getBondedDevices();
			           if (myBluetoothAdapter.isEnabled()){
			        	   if(pairedDevices.size()>0){
			           // put it's one to the adapter
			        		   for(BluetoothDevice device : pairedDevices){
			        			   BTArrayAdapter.add(device.getName()+ "\n" + device.getAddress());
			        			   Toast.makeText(getApplicationContext(),"Show Paired Devices", 
			        			   Toast.LENGTH_SHORT).show();
			           	
			        		   }
			        	   }
			           }
			     
		        
			        Receiver = new BroadcastReceiver() {
		        	public void onReceive(Context context, Intent intent) {
		        		String action = intent.getAction();
		        		// When Device finds New Device
		        		if (BluetoothDevice.ACTION_FOUND.equals(action)) {
		        			// Connect to New Device
		        			BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		        			// Add name to List Array
		        			BTArrayAdapter.add(device.getName() + "\n" + device.getAddress());
		        		}
		        	}
		        };
		        
		        // Register the Receiver Using an Intent Filter
		        Filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		        registerReceiver(Receiver, Filter);
		   }
		public void find(View view) {
			if (myBluetoothAdapter.isDiscovering()) {
     		   // the button is pressed when it discovers, so cancel the discovery
	     		   myBluetoothAdapter.cancelDiscovery();
     	   		}
     	   		else {
     	   			BTArrayAdapter.clear();
     	   			myBluetoothAdapter.startDiscovery();
	     			
     	   			registerReceiver(Receiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));	
     	   			}    
        		}
			
			
			public void onDestroy(){
				super.onDestroy();
				myBluetoothAdapter.disable();
				
				// context.unregisterReceiver(Receiver, Filter);
			}
			
			
			
/** 
 * All of the following Code was originally used for the Bluetooth Application, but has now been updated for other uses. 
 * 
 * Spoken by Jack Chalker		
 */
			
//	          } else {
//	    	      text = (TextView) findViewById(R.id.text);
//	    	      onBtn = (Button)findViewById(R.id.turnOn);
//	    	      onBtn.setOnClickListener(new OnClickListener() {
//	    			@Override
//	    			public void onClick(View v) {
//	    				// TODO Auto-generated method stub
//	    				Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//	    				startActivityForResult(intent, 1);
//	    				myBluetoothAdapter.enable();
//	    			}
//	    	      });
//	    	      
//	    	      offBtn = (Button)findViewById(R.id.turnOff);
//	    	      offBtn.setOnClickListener(new OnClickListener() {
//	    	  		
//	    	  		@Override
//	    	  		public void onClick(View v) {
//	    	  			// TODO Auto-generated method stub
//	    	  			Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//	    	  			startActivityForResult(intent, 1);
//	    	  			myBluetoothAdapter.disable();
//	    	  		}
//	    	      });
//	    	      
//	    	      listBtn = (Button)findViewById(R.id.paired);
//	    	      listBtn.setOnClickListener(new OnClickListener() {
//	    	  		
//	    	  		@Override
//	    	  		public void onClick(View v) {
//	    	  			// TODO Auto-generated method stub
//	    	  			list(v);
//	    	  		}
//	    	      });
//	    	      
//	    	      findBtn = (Button)findViewById(R.id.search);
//	    	      findBtn.setOnClickListener(new OnClickListener() {
//	    	  		
//	    	  		@Override
//	    	  		public void onClick(View v) {
//	    	  			// TODO Auto-generated method stub
//	    	  			find(v);
//	    	  		}
//	    	      });
//	    	    
//	    	      myListView = (ListView)findViewById(R.id.listView1);
//	    
//	    	     }
//	    	
//	    	      // create the arrayAdapter that contains the BTDevices, and set it to the ListView
//	    	      BTArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
//	    	      myListView.setAdapter(BTArrayAdapter);
//	    	      filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
//	    	      receiver = new BroadcastReceiver() {
//	    	   
//	    	    	  public void onReceive(Context context, Intent intent) {
//	   				// TODO Auto-generated method stub
//	   				String Action = intent.getAction();
//	   				if(BluetoothDevice.ACTION_FOUND.equals(Action)){
//	   					BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//	   					BTArrayAdapter.add(device.getName()+ ("+s+")+"\n"+device.getAddress());
//	   				}	
//	   				
//	    	    }
//	   				
//	    	       public void registerReceiver(receiver, filter);{
//	    	       
//	    	       filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
//	    	       }
//	    	      };
//	       
//	       public void on(View view){
//	           if (!myBluetoothAdapter.isEnabled()) {
//	              Intent turnOnIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//	              startActivityForResult(turnOnIntent, REQUEST_ENABLE_BT);
//
//	              Toast.makeText(getApplicationContext(),"Bluetooth turned on" ,
//	             		 Toast.LENGTH_LONG).show();
//	           }
//	           else{
//	              Toast.makeText(getApplicationContext(),"Bluetooth is already on",
//	             		 Toast.LENGTH_LONG).show();
//	           }
//	        }
//	        
//	        @Override
//	        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//	     	   // TODO Auto-generated method stub
//	     	   if(requestCode == REQUEST_ENABLE_BT){
//	     		   if(myBluetoothAdapter.isEnabled()) {
//	     			   text.setText("Status: Enabled");
//	     		   } else {   
//	     			   text.setText("Status: Disabled");
//	     		   }
//	     	   }
//	        }
//	        

//	        
//	        final BroadcastReceiver bReceiver = new BroadcastReceiver() {
//	     	    @Override
//	   		public void onReceive(Context context, Intent intent) {
//	     	        String action = intent.getAction();
//	     	        // When discovery finds a device
//	     	        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
//	     	             // Get the BluetoothDevice object from the Intent
//	     	        	 BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//	     	        	 // add the name and the MAC address of the object to the arrayAdapter
//	     	             BTArrayAdapter.add(device.getName() + "\n" + device.getAddress());
//	     	             BTArrayAdapter.notifyDataSetChanged();
//	     	        }
//	     	    }
//	     	};
//	     	
//	        public void find(View view) {
//	     	   if (myBluetoothAdapter.isDiscovering()) {
//	     		   // the button is pressed when it discovers, so cancel the discovery
//	     		   myBluetoothAdapter.cancelDiscovery();
//	     	   }
//	     	   else {
//	     			BTArrayAdapter.clear();
//	     			myBluetoothAdapter.startDiscovery();
//	     			
//	     			registerReceiver(bReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));	
//	     		}    
//	        }

	
	
}
