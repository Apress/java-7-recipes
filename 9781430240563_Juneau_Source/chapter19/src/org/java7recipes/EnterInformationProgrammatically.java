package org.java7recipes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class EnterInformationProgrammatically extends Activity {

	private LinearLayout layout;
	private Button button;
	private Spinner spinner;
	private EditText editText;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layout = new LinearLayout(this);
		layout.setOrientation(android.widget.LinearLayout.VERTICAL);
		button = new Button(this);
		button.setText("Press Me");
		button.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		       performAction();
		    }
		});
		editText = new EditText(this);
		editText.setText("Enter your name");

		spinner = new Spinner(this);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.levels_array,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		// Add views to the layout
		layout.addView(editText);
		layout.addView(spinner);
		layout.addView(button);
		setContentView(layout);
	}

	public void performAction() {
		// Perform button action here
		Intent i = new Intent(EnterInformationProgrammatically.this, DisplayInformation.class);
		Bundle b = new Bundle();
		b.putString("name", editText.getText().toString());
		b.putString("level", spinner.getSelectedItem().toString());
	
		i.putExtra("android.intent.extra.INTENT", b);
	    startActivity(i);
	}
}
