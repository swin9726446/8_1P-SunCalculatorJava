package au.edu.swin.sdmd.suncalculatorjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
        populateBookData();

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
    private void populateBookData() {
        
//        //I wanted to give something interesting to look at.
//        for (int i = 0; i < NUM_BOOKS; i += 1) {
//            placeList.add(new Place(
//                    "City No." + (i+1),
//                    i + ".00",
//                    "1" + i + "0.00",
//                    "trial/error"
//            ));
//        }

        placeAdapter.notifyDataSetChanged();
    }
}