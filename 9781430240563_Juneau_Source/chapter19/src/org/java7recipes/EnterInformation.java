package org.java7recipes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class EnterInformation extends Activity {

	private Spinner spinner;
	private EditText editText;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enterinformation);

		spinner = (Spinner) this.findViewById(R.id.spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.levels_array,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
	}

	public void performAction(View view) {
		// Perform button action here
		editText = (EditText) this.findViewById(R.id.edittext);
		Intent i = new Intent(EnterInformation.this, DisplayInformation.class);
		Bundle b = new Bundle();
		b.putString("name", editText.getText().toString());
		b.putString("level", spinner.getSelectedItem().toString());
	
		i.putExtra("android.intent.extra.INTENT", b);
	    startActivity(i);
	}
}
