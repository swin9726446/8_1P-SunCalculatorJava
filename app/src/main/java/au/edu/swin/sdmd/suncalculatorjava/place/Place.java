package au.edu.swin.sdmd.suncalculatorjava.place;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by 9726446 on 31/10/18 @ LB1-MAC-017
 * Place object to help build list
 * Made parcelable so things may be sent to the viewer
 */

public class Place implements Parcelable {
    //Private Variables:
    private final String name;
    private final String latitude;
    private final String longitude;
    private final String locale;

    //Getters:
    public String getName() {
        return name;
    }
    public double getLatitude(){return Convert(latitude);}
    public double getLongitude(){return Convert(longitude);}
    public String getLocale() {
        return locale;
    }

    //Converter:
    private double Convert(String string){
        try {
            return Double.parseDouble(string);
        }
        catch (NumberFormatException nfe){
            Log.e("conversion", nfe.toString());
            return 0;
        }
    }

    //Constructor:
    public Place(String aName, String aLat, String aLong, String aLocale) {
        name = aName;
        latitude = aLat;
        longitude = aLong;
        locale = aLocale;
    }

    //Parcel stuff
    public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>(){
        //Construct object using parcel
        @Override
        public Place createFromParcel (Parcel p){
            return new Place(p);
        }
        //make a whole bunch of 'em
        @Override
        public Place[] newArray(int size){
            return new Place[size];
        }
    };
    /**
     * Parcel Constructor (& packer!)
     * @param p Parcel built by another Image.
     */
    public Place (Parcel p){
        name = p.readString();
        latitude = p.readString();
        longitude = p.readString();
        locale = p.readString();
    }
    public void writeToParcel (Parcel parcel, int i){
        parcel.writeString(name);
        parcel.writeString(latitude);
        parcel.writeString(longitude);
        parcel.writeString(locale);
    }

    /**
     * I'm pretty sure this is just for compliance.
     * @return
     */
    @Override
    public int describeContents(){
        return 0;
    }
}
