package com.example.pluscomputers.publictoilet2;

import android.Manifest;
import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<ListLocation> listLocations = new ArrayList<>();
    private List<ListLocation> listLocations2 = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListLocationAdapter listAdapter;
    private ImageView gpsImageView;
    private String distancaPikave;
    float colorGPS = BitmapDescriptorFactory.HUE_BLUE;
    float colorNormal = BitmapDescriptorFactory.HUE_RED;

    double latGPS;
    double lonGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        recyclerView = findViewById(R.id.recycler_view);
        listAdapter = new ListLocationAdapter(listLocations, this, this);

        gpsImageView = findViewById(R.id.gpsImageView);

        callGPS();
        listData();
        sortData();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listAdapter);

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        for (int i = 0 ; i < listLocations.size() ; i++){

            goToMapPoint(listLocations.get(i).getmLatitude(), listLocations.get(i).getmLongitude(), listLocations.get(i).getName(), colorNormal);

        }

        //GPS , merr koordinatat nga GPS dhe shfaq markerin dhe cameraposition ne lokacionin e marrur
        // llogarit distancen prej koordinatave te GPS deri te pika e caktuar
        gpsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callGpsTouch();

                //distancaPikave = distanceBetweenPoints(listLocations.get(1).getmLatitude(), listLocations.get(1).getmLongitude());

            }
        });


    }

    public String distanceBetweenPoints(double lat, double lng) {
        float results[] = new float[10];
        //for (int i=0; i<=listLocations.size(); i++){
        Location.distanceBetween(latGPS, lonGPS,
                lat, lng, results);
        //}

        //Toast.makeText(getApplicationContext(),"Distanca: " + results[0]/1000000 + " km" ,Toast.LENGTH_LONG).show();

        String distanca = String.valueOf(results[0] / 1000);

        return distanca;
    }

    private void listData() {
        ListLocation lista = new ListLocation("New Born", "Kafe",
                distanceBetweenPoints(42.660965, 21.158258), 42.660965, 21.158258);
        listLocations.add(lista);

        lista = new ListLocation("Ferizaj", "Kafe",
                distanceBetweenPoints(42.3703312, 21.1485373), 42.3703312, 21.1485373);
        listLocations.add(lista);

        lista = new ListLocation("SunnyHill", "Restaurant",
                distanceBetweenPoints(42.652385, 21.170248), 42.652385, 21.170248);
        listLocations.add(lista);

        lista = new ListLocation("Libraria Universitare", "Librari",
                distanceBetweenPoints(42.656521, 21.162000), 42.656521, 21.162000);
        listLocations.add(lista);

        lista = new ListLocation("Universiteti per biznes dhe teknologji", "Universitet",
                distanceBetweenPoints(42.558550, 21.134597), 42.558550, 21.134597);
        listLocations.add(lista);

        lista = new ListLocation("Qendra inovacionit te Kosoves", "Shkolle",
                distanceBetweenPoints(42.656521, 21.162000), 42.656521, 21.162000);
        listLocations.add(lista);

        lista = new ListLocation("Katedralja", "Katedrale",
                distanceBetweenPoints(42.656550, 21.159302), 42.656550, 21.159302);
        listLocations.add(lista);
    }

    public void callGPS() {
        ActivityCompat.requestPermissions(MapsActivity.this,new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION},123);

        GPStracker g = new GPStracker(getApplication());
        Location l = g.getLocation();

        if (l != null) {
            latGPS = l.getLatitude();
            lonGPS = l.getLongitude();
        }

    }

    public void callGpsTouch() {

        ActivityCompat.requestPermissions(MapsActivity.this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        GPStracker g = new GPStracker(getApplication());
        Location l = g.getLocation();

        if (l != null) {
            latGPS = l.getLatitude();
            lonGPS = l.getLongitude();

            goToMapPoint(latGPS, lonGPS, "Your location", colorGPS);
        }
    }

    public void goToMapPoint(double latitude, double longitude, String title, float color) {

        LatLng location = new LatLng(latitude,longitude);

        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(latitude,longitude))
                .zoom(14)
                .build();
        mMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(position), 4000, null);
        mMap.addMarker(new MarkerOptions()
                .position(location)
                .title(title)
                .icon(BitmapDescriptorFactory.defaultMarker(color)));
        mMap.addCircle(new CircleOptions()
                .center(location)
                .radius(100)
                .strokeColor(Color.GREEN)
                .fillColor(Color.argb(64,0,255,0)));
    }

    public void sortData() {

//        List<ListLocation> listPerSortim = new ArrayList<ListLocation>();
//        listPerSortim.add( new ListLocation("New Born", "Kafe", "8km",42.660965, 21.158258));
//        listPerSortim.add( new ListLocation("Ferizaj", "Kafe", "2.5km", 42.3703312, 21.1485373));
//        listPerSortim.add( new ListLocation("SunnyHill", "Restaurant", "4km",42.652385, 21.170248));

        Collections.sort(listLocations, new Comparator<ListLocation>() {
            public int compare(ListLocation obj1, ListLocation obj2) {
                // TODO Auto-generated method stub
                return obj1.getDistance().compareToIgnoreCase(obj2.getDistance());
            }
        });

        Log.v("FUN", listLocations.get(0).getDistance().toString());
        Log.v("\nFUN", listLocations.get(1).getDistance().toString());
        Log.v("\nFUN", listLocations.get(2).getDistance().toString());
    }

}
