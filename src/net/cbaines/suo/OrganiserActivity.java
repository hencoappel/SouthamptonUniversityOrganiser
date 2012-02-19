package net.cbaines.suo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class OrganiserActivity extends Activity {

	private static final String TAG = "OrganiserActivity";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.organiser_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menu_preferences:
			Intent i = new Intent(OrganiserActivity.this, PreferencesActivity.class);
			startActivityForResult(i, 0);
			return true;
		default:
			Log.e(TAG, "No known menu option selected");
			return super.onOptionsItemSelected(item);
		}
	}
}