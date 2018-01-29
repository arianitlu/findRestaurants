package com.example.pluscomputers.publictoilet2;

import android.Manifest;
import android.graphics.Color;
import android.location.Location;
import android.support.design.widget.Snackbar;
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
    private Snackbar snackbar;
    private ImageView gpsImageView;
    private boolean gpsCalled = false;

    private String distancaPikave;

    float colorMarkerGPS = BitmapDescriptorFactory.HUE_BLUE;
    float colorMarkerNormal = BitmapDescriptorFactory.HUE_RED;

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
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setAdapter(listAdapter);

        callSnackBar();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        for (int i = 0 ; i < listLocations.size() ; i++){

            createPointMap(listLocations.get(i).getmLatitude(), listLocations.get(i).getmLongitude(), listLocations.get(i).getName(), colorMarkerNormal);

        }

        //GPS , merr koordinatat nga GPS dhe shfaq markerin dhe cameraposition ne lokacionin e marrur
        // llogarit distancen prej oordinatave te GPS deri te pika e caktuar
        gpsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callGpsOnTouch();

                //distancaPikave = distanceBetweenPoints(listLocations.get(1).getmLatitude(), listLocations.get(1).getmLongitude());

            }
        });

        createPointMap(listLocations.get(0).getmLatitude(), listLocations.get(0).getmLongitude(), listLocations.get(0).getName(), colorMarkerNormal);


    }

    public String distanceBetweenPoints(double lat, double lng) {
        float results[] = new float[10];

        Location.distanceBetween(latGPS, lonGPS,
                lat, lng, results);

        String distanca = String.valueOf(results[0] / 1000);

        return distanca;
    }

    private void listData() {
        ListLocation lista = new ListLocation(R.drawable.universitet, "Fakulteti Teknik", "Kafe",
                distanceBetweenPoints(42.648533, 21.167011), 42.648533, 21.167011);
        listLocations.add(lista);

        lista = new ListLocation(R.drawable.ferizaj, "Ferizaj", "Kafe",
                distanceBetweenPoints(42.3703312, 21.1485373), 42.3703312, 21.1485373);
        listLocations.add(lista);

        lista = new ListLocation(R.drawable.sunnyhill, "SunnyHill", "Restaurant",
                distanceBetweenPoints(42.652385, 21.170248), 42.652385, 21.170248);
        listLocations.add(lista);

        lista = new ListLocation(R.drawable.bibloteka, "Libraria Universitare", "Librari",
                distanceBetweenPoints(42.6557309, 21.1598383), 42.6557309, 21.1598383);
        listLocations.add(lista);

        lista = new ListLocation(R.drawable.ubt, "Universiteti per biznes dhe teknologji", "Universitet",
                distanceBetweenPoints(42.558550, 21.134597), 42.558550, 21.134597);
        listLocations.add(lista);

        lista = new ListLocation(R.drawable.inovacion, "Qendra inovacionit te Kosoves", "Shkolle",
                distanceBetweenPoints(42.6557098, 21.1597379), 42.6557098, 21.1597379);
        listLocations.add(lista);

        lista = new ListLocation(R.drawable.katedralja, "Katedralja", "Katedrale",
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

    public void callGpsOnTouch() {

        ActivityCompat.requestPermissions(MapsActivity.this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        if (gpsCalled == false) {

            GPStracker g = new GPStracker(getApplication());
            Location l = g.getLocation();

            if (l != null) {
                latGPS = l.getLatitude();
                lonGPS = l.getLongitude();

                createPointMap(latGPS, lonGPS, "Your location", colorMarkerGPS);
            }
        }

        goToPointMap(latGPS, lonGPS);

        gpsCalled = true;
    }

    public void createPointMap(double latitude, double longitude, String title, float color) {

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

    public void goToPointMap(double latitude, double longitude) {

        LatLng location = new LatLng(latitude, longitude);

        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude))
                .zoom(14)
                .build();
        mMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(position), 4000, null);
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

    public void callSnackBar() {
        View view = findViewById(R.id.activity_main);
        String snackBarDistanca = distanceBetweenPoints(listLocations.get(0).getmLatitude(),
                listLocations.get(0).getmLongitude());
        String snackBarDistancaShkurtuar = snackBarDistanca.substring(0, 4);
        snackbar = Snackbar.make(view, "Pika juaj më e afërt është: " + snackBarDistancaShkurtuar + " km",
                Snackbar.LENGTH_INDEFINITE)
                .setAction("Hide", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
        snackbar.show();

//        Snackbar.make(v, "Snackbar with Action", Snackbar.LENGTH_LONG)
//                .setAction("UNDO", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Snackbar.make(view, "Action!", Snackbar.LENGTH_SHORT).show();
//                    }
//                }).setActionTextColor(Color.RED).show();
//        View snackbarView = snackbar.getView();
//        snackbarView.setBackgroundColor(Color.parseColor("#000000"));
        snackbar.show();
    }

}
