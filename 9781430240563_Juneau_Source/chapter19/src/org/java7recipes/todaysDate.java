package org.java7recipes;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class todaysDate extends Activity {
	private TextView tv;
	private LinearLayout layout;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doWork();
		layout = new LinearLayout(this);
		layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(tv);
		setContentView(layout);
    }
    
    public void doWork(){
		Date todaysDate = new Date();
		tv = new TextView(this);
		tv.setText("The current date is: " + todaysDate);
    }
}