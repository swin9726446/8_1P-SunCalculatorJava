package au.edu.swin.sdmd.suncalculatorjava.place;
import au.edu.swin.sdmd.suncalculatorjava.R;


import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.support.v7.widget.RecyclerView;
import java.util.List;


/**
 * Created by 9726446 on 31/10/18 @ LB1-MAC-017
 * Place adapter for building recycler view list.
 * Adapted from code for A5_1P, in turn from tutorial provided on:
 * https://www.androidhive.info/2016/01/android-working-with-recycler-view/ (last accessed 4/10/18)
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>{

    //List of Places to view. DataSet for technical purposes.
    private final List<Place> placeList;

    //View to contain the relevant information
    static class PlaceViewHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final TextView lat_long;
        final TextView locale;
        //test
        Place p;

        private PlaceViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tvTitle);
            lat_long = view.findViewById(R.id.tvLat_Long);
            locale = view.findViewById(R.id.tvLocale);
        }
    }

    public PlaceAdapter(List<Place> placeList){
        this.placeList = placeList;
    }

    //Creates a view holder while more are needed.
    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        //New item
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_list_row, parent, false);
        return new PlaceViewHolder(itemView);
    }

    //Replaces view holders that are off screen to give the illusion of scrolling.
    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position){
        //get element
        //replace contents
        Place place = placeList.get(position);
        holder.title.setText(place.getName());
        holder.lat_long.setText(String.format("%s / %s", place.getLatitude(), place.getLongitude()));
        holder.locale.setText(place.getLocale());
        holder.p = place;
        holder.itemView.setTag(position);
    }

    //return size
    @Override
    public int getItemCount(){
        return placeList.size();
    }
}