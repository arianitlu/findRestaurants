package com.example.pluscomputers.publictoilet2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ListLocationsDetails extends FragmentActivity implements OnMapReadyCallback{

    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_selected);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        Bundle extras = getIntent().getExtras();
        String userName;
        double lat;
        double lng;

        if (extras != null) {
            userName = extras.getString("name");
            lat = extras.getDouble("latitude");
            lng = extras.getDouble("longitude");

            Toast.makeText(getApplicationContext(),lat + " ," + lng,Toast.LENGTH_LONG).show();

            goToMapPoint(lat,lng,userName);
        }
    }

    public void callGPS() {
        ActivityCompat.requestPermissions(ListLocationsDetails.this,new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION},123);

        GPStracker g = new GPStracker(getApplication());
        Location l = g.getLocation();

        if (l != null) {
            double latGPS = l.getLatitude();
            double lonGPS = l.getLongitude();

            goToMapPoint(latGPS,lonGPS,"Your location");
        }

    }

    public void goToMapPoint(double latitude , double longitude , String title){

        LatLng location = new LatLng(latitude,longitude);

        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(latitude,longitude))
                .zoom(14)
                .build();
        mMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(position), 2000, null);
        mMap.addMarker(new MarkerOptions()
                .position(location)
                .title(title));
        mMap.addCircle(new CircleOptions()
                .center(location)
                .radius(100)
                .strokeColor(Color.GREEN)
                .fillColor(Color.argb(64,0,255,0)));
    }
}
