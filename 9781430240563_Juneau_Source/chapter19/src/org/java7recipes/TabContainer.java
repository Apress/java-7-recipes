package org.java7recipes;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class TabContainer extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupTabs();
    }
    
    private void setupTabs(){
    	setContentView(R.layout.tabs);

        Resources res = getResources(); // Resource object for obtaining drawables
        TabHost tabHost = getTabHost(); 
        TabHost.TabSpec spec;  
        Intent intent;  

        // Create an Intent to launch an Activity for the tab 
        intent = new Intent().setClass(this, EnterInformation.class);

        // Initialize a TabSpec for each tab, and then add to tabhost
        spec = tabHost.newTabSpec("enterInformation").setIndicator("Information",
        				res.getDrawable(R.drawable.enterinformation))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, todaysDate.class);
        spec = tabHost.newTabSpec("todaysDate").setIndicator("Todays Date",
                          res.getDrawable(R.drawable.main))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, VisitSite.class);
        spec = tabHost.newTabSpec("visitSite").setIndicator("Visit java.net",
                          res.getDrawable(R.drawable.visitsite))
                      .setContent(intent);
        tabHost.addTab(spec);
        
        tabHost.setCurrentTab(0);
    }
    
    
}