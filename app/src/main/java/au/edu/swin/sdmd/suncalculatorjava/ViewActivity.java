package au.edu.swin.sdmd.suncalculatorjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import au.edu.swin.sdmd.suncalculatorjava.calc.AstronomicalCalendar;
import au.edu.swin.sdmd.suncalculatorjava.calc.GeoLocation;
import au.edu.swin.sdmd.suncalculatorjava.place.Place;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        initializeUI();
    }

    private void initializeUI() {
        DatePicker dp = findViewById(R.id.datePicker);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        dp.init(year,month,day,dateChangeHandler); // setup initial values and reg. handler
        updateTime(year, month, day);
    }

    private void updateTime(int year, int monthOfYear, int dayOfMonth) {
//        TimeZone tz = TimeZone.getDefault();
//        GeoLocation geolocation = new GeoLocation("Melbourne", -37.50, 145.01, tz);
        try {
            Place place = getIntent().getExtras().getParcelable("place");
            if (place == null){
                Log.e("metadata init","Couldn't find the Parcelled Image!");
                finish();
                return;
            }
            TimeZone tz = TimeZone.getTimeZone(place.getLocale());
            GeoLocation geoLocation = new GeoLocation(place.getName(), place.getLatitude(), place.getLongitude(), tz);
            AstronomicalCalendar ac = new AstronomicalCalendar(geoLocation);
            ac.getCalendar().set(year, monthOfYear, dayOfMonth);
            Date srise = ac.getSunrise();
            Date sset = ac.getSunset();

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

            TextView sunriseTV = findViewById(R.id.sunriseTimeTV);
            TextView sunsetTV = findViewById(R.id.sunsetTimeTV);
            TextView locationTV = findViewById(R.id.locationTV);
            Log.d("SUNRISE Unformatted", srise+"");

            sunriseTV.setText(sdf.format(srise));
            sunsetTV.setText(sdf.format(sset));
            locationTV.setText(place.getName());

        } catch (NullPointerException npe) {
            Log.e("Bundle Error (View)", npe.toString());
            finish();
        }
    }

    DatePicker.OnDateChangedListener dateChangeHandler = new DatePicker.OnDateChangedListener()
    {
        public void onDateChanged(DatePicker dp, int year, int monthOfYear, int dayOfMonth)
        {
            updateTime(year, monthOfYear, dayOfMonth);
        }
    };


}
