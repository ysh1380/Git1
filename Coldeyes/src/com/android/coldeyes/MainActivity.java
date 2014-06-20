package com.android.coldeyes;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {

	private EditText editTextLatitude = null; // 위도

	private EditText editTextLongitude = null; // 경도

	private Spinner spinnerLocationProvider = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editTextLatitude = (EditText) findViewById(R.id.editTextLatitude);

		editTextLongitude = (EditText) findViewById(R.id.editTextLongitude);

		spinnerLocationProvider = (Spinner) findViewById(R.id.spinnerLocationProvider);

		Button buttonGetLocation = (Button) findViewById(R.id.buttonGetLocation);

		buttonGetLocation.setOnClickListener(buttonRefreshClickListener);

		// ------------------------------------------------------------------

		// Set views.

		String[] locationProviders = { "GPS", "Network" };

		ArrayAdapter<CharSequence> adapterOfSpinnerLocationProvider = new ArrayAdapter<CharSequence>(

		this,

		android.R.layout.simple_spinner_item,

		locationProviders

		);

		adapterOfSpinnerLocationProvider.setDropDownViewResource(

		android.R.layout.simple_spinner_dropdown_item);

		spinnerLocationProvider.setAdapter(adapterOfSpinnerLocationProvider);

	}

	OnClickListener buttonRefreshClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			String locationProvider = (String) spinnerLocationProvider
					.getSelectedItem();

			LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

			Location lastKnownLocation = null;

			if (locationProvider.equalsIgnoreCase(LocationManager.GPS_PROVIDER))

				lastKnownLocation = lm
						.getLastKnownLocation(LocationManager.GPS_PROVIDER);

			else if (locationProvider
					.equalsIgnoreCase(LocationManager.NETWORK_PROVIDER))

				lastKnownLocation = lm
						.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

			else

				lastKnownLocation = null;

			if (null != lastKnownLocation) {

				editTextLatitude.setText("" + lastKnownLocation.getLatitude());

				editTextLongitude
						.setText("" + lastKnownLocation.getLongitude());

			}

			else {

				editTextLatitude.setText("위도 식별 불가");

				editTextLongitude.setText("경도 식별 불가");

			}

		}

	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
