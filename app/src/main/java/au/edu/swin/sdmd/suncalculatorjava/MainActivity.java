package au.edu.swin.sdmd.suncalculatorjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import au.edu.swin.sdmd.suncalculatorjava.place.*;

/**
 * Created by 9726446 on 31/10/18 @ LB1-MAC-017.
 * Basic idea: Import code from the recycler-view exercise and then fiddle with as needed
 */

public class MainActivity extends AppCompatActivity {
    private static final int NUM_BOOKS = 20;
    private final List<Place> placeList = new ArrayList<>();
    private PlaceAdapter placeAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initaliseUI();
        populatePlaceData();

    }

    private void initaliseUI() {
        RecyclerView recyclerView = findViewById(R.id.rvPlaceList);

        //Documentation says this boosts performance if each item is the same size so...
        recyclerView.setHasFixedSize(true);

        //Set adapter
        placeAdapter = new PlaceAdapter(placeList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
        recyclerView.setAdapter(placeAdapter);
    }

    /**
     * Read cities from file.
     */
    private void populatePlaceData() {
        try {
            String strAry[];
            String string;
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(getResources().openRawResource(R.raw.au_locations))
            );
            //for each line in the reader {
            while ((string = bufferedReader.readLine()) != null){
                strAry = string.split(",");
                placeList.add(new Place(
                        strAry[0],
                        strAry[1],
                        strAry[2],
                        strAry[3]
                ));
            }
            bufferedReader.close();
        }
        catch (Exception e) {
            Log.e("Read Error", String.format("Couldn't read location data.\n%s", e.toString()));
        }

        placeAdapter.notifyDataSetChanged();
    }

    /**
     * Send the chosen data to the view app and launch it.
     * Code recycled from 5_2P
     * @param view the view sending the request
     */
    public void viewTimes(View view){
        Intent i = new Intent();
        i.setClass(getApplicationContext(), ViewActivity.class);
        int object = (int)view.getTag();
        try{
            i.putExtra("place", placeList.get(object));
            startActivity(i);
        }
        catch (Exception e){
            Log.e("viewTimes", e.toString());
        }
    }
}