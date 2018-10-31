package au.edu.swin.sdmd.suncalculatorjava.place;

/**
 * Created by 9726446 on 31/10/18 @ LB1-MAC-017
 * Place object to help build list
 */

public class Place {
    //Private Variables:
    private final String name;
    private final String latitude;
    private final String longitude;
    private final String locale;

    //Getters:
    public String getName() {
        return name;
    }
    String getLatitude(){return latitude;}
    String getLongitude(){return longitude;}
    String getLocale() {
        return locale;
    }

    //Constructor:
    public Place(String aName, String aLat, String aLong, String aLocale) {
        name = aName;
        latitude = aLat;
        longitude = aLong;
        locale = aLocale;
    }
}
