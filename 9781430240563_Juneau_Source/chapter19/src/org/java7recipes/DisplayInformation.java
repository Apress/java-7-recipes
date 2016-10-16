package org.java7recipes;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;

import android.widget.TextView;

public class DisplayInformation extends Activity {

	private TextView textView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	String displayText = null;
        super.onCreate(savedInstanceState);
		
		setContentView(R.layout.displayinformation);
		
		Intent startingIntent = getIntent();

		if (startingIntent != null) {
			Bundle b = startingIntent
					.getBundleExtra("android.intent.extra.INTENT");
			if (b == null) {
				displayText = "No data.";
			} else {
				displayText = b.getString("name") + ",\n\n";
				displayText += "You are a " + b.getString("level") + "\n\n";
				displayText += "Android developer.\n";

			}
		} else {
			displayText = "Information Not Found.";

		}
		
		textView = (TextView) this.findViewById(R.id.displaytext);
		textView.setText(displayText);
    }
    
   
}

