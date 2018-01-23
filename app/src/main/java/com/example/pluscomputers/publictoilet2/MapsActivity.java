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
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<ListLocation> listLocations = new ArrayList<>();
    private List<ListLocation> listLocations2 = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListLocationAdapter listAdapter;
    //private ListLocationAdapter listAdapter2;
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

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listAdapter);

        listData();

    }

    private void listData() {
        ListLocation lista = new ListLocation("New Born", "Kafe", "2km",42.660965, 21.158258);
        listLocations.add(lista);

        lista = new ListLocation("Ferizaj", "Kafe", "2.5km", 42.3703312, 21.1485373);
        listLocations.add(lista);

        lista = new ListLocation("SunnyHill", "Restaurant", "2.5km",42.652385, 21.170248);
        listLocations.add(lista);

        lista = new ListLocation("Libraria Universitare", "Librari", "3.5km",42.656521, 21.162000);
        listLocations.add(lista);

        lista = new ListLocation("Universiteti per biznes dhe teknologji", "Universitet", "4.5km",42.558550, 21.134597);
        listLocations.add(lista);

        lista = new ListLocation("Qendra inovacionit te Kosoves", "Shkolle", "5km",42.656521, 21.162000);
        listLocations.add(lista);

        lista = new ListLocation("Katedralja", "Katedrale", "6.5km",42.656550, 21.159302);
        listLocations.add(lista);

        lista = new ListLocation("Telekomi i Kosoves", "Posta", "7.4km",42.651735, 21.157197);
        listLocations.add(lista);

        lista = new ListLocation("Dardha", "Qender Tregtare", "8.5km",42.648956, 21.151975);
        listLocations.add(lista);
    }

    private void listData2() {
        ListLocation lista = new ListLocation("New Born", "Kafe", distancaPikave,42.660965, 21.158258);
        listLocations2.add(lista);

        lista = new ListLocation("SunnyHill", "Restaurant", distancaPikave,42.652385, 21.170248);
        listLocations2.add(lista);

        lista = new ListLocation("Libraria Universitare", "Librari", distancaPikave,42.656521, 21.162000);
        listLocations2.add(lista);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        for (int i = 0 ; i < listLocations.size() ; i++){

            goToMapPoint(listLocations.get(i).getmLatitude(), listLocations.get(i).getmLongitude(), listLocations.get(i).getName(), colorNormal);

        }

        // Pika qe preket ne ekran dhe kthen koordinatat(latitude,longitude)
        // Ide e mire mund te jete nese shfrytezuesi e prek Map-in dhe ajo bohet full screen ose match_parent
//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng) {
//                Toast.makeText(getApplicationContext(), "Pika: " + latLng, Toast.LENGTH_LONG).show();
//
//            }
//        });

        //GPS , merr koordinatat nga GPS dhe shfaq markerin dhe cameraposition ne lokacionin e marrur
        // llogarit distancen prej koordinatave te GPS deri te pika e caktuar
        gpsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callGPS();

                distancaPikave = distanceBetweenPoints(listLocations.get(1).getmLatitude(), listLocations.get(1).getmLongitude());

//                Toast.makeText(getApplicationContext(),
//                        "\nDistanca nga lokacioni  "
//                                + "2 eshte :"
//                                + distancaPikave + " km",
//
//                        Toast.LENGTH_LONG).show();

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

    public void callGPS() {
        ActivityCompat.requestPermissions(MapsActivity.this,new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION},123);

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


}
