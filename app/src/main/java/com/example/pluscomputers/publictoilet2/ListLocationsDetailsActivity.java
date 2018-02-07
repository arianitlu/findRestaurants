package com.example.pluscomputers.publictoilet2;

import android.Manifest;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;
import android.widget.RatingBar;
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

import java.text.DecimalFormat;

public class ListLocationsDetailsActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mMap;

    TextView txtName1, txtType1;

    RatingBar ratingBar1, ratingBar2, ratingBar3, ratingBar4, ratingBar5, ratingBar6, ratingBar7;
    TextView avgRatings, txtRatPastertia, txtRatNdricimi, txtRatMadhesia, txtRatVentilimi, txtRatKomoditeti,
            txtRatTextView1, txtRatTextView2;
    float numRating1, numRating2, numRating3, numRating4, numRating5, numRating6, numRating7;
    int numriPjestues = 0;
    boolean iscalledListener1 = false;
    boolean iscalledListener2 = false;
    boolean iscalledListener3 = false;
    boolean iscalledListener4 = false;
    boolean iscalledListener5 = false;
    boolean iscalledListener6 = false;
    boolean iscalledListener7 = false;

    float vleraMesatare = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_selected1);

        noStatusBar();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        txtName1 = findViewById(R.id.name1);
        txtType1 = findViewById(R.id.type1);

        ratingBar1 = findViewById(R.id.ratingBar1);
        ratingBar2 = findViewById(R.id.ratingBar2);
        ratingBar3 = findViewById(R.id.ratingBar3);
        ratingBar4 = findViewById(R.id.ratingBar4);
        ratingBar5 = findViewById(R.id.ratingBar5);
        ratingBar6 = findViewById(R.id.ratingBar6);
        ratingBar7 = findViewById(R.id.ratingBar7);

        avgRatings = findViewById(R.id.avgRatings);

        txtRatKomoditeti = findViewById(R.id.txtRatKomoditeti);
        txtRatMadhesia = findViewById(R.id.txtRatMadhesia);
        txtRatNdricimi = findViewById(R.id.txtRatNdricimi);
        txtRatPastertia = findViewById(R.id.txtRatPastertia);
        txtRatVentilimi = findViewById(R.id.txtRatVentilimi);
        txtRatTextView1 = findViewById(R.id.txtRatTextView1);
        txtRatTextView2 = findViewById(R.id.txtRatTextView2);

        numRating1 = ratingBar1.getRating();
        numRating2 = ratingBar2.getRating();
        numRating3 = ratingBar3.getRating();
        numRating4 = ratingBar4.getRating();
        numRating5 = ratingBar5.getRating();
        numRating6 = ratingBar6.getRating();
        numRating7 = ratingBar7.getRating();

        addListenerOnRatingBar();

        avgRatings.setText(String.valueOf(mesatarjaRatings(1)));

    }

    public void addListenerOnRatingBar() {
        ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                numRating1 = rating;
                if (iscalledListener1 == false) {
                    numriPjestues += 1;
                }
                iscalledListener1 = true;

                avgRatings.setText(String.valueOf(mesatarjaRatings(numriPjestues)));
                txtRatPastertia.setText(String.valueOf(numRating1));
            }
        });
        ratingBar2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numRating2 = rating;
                if (iscalledListener2 == false) {
                    numriPjestues += 1;
                }
                iscalledListener2 = true;
                avgRatings.setText(String.valueOf(mesatarjaRatings(numriPjestues)));
                txtRatNdricimi.setText(String.valueOf(numRating2));
            }
        });
        ratingBar3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numRating3 = rating;
                if (iscalledListener3 == false) {
                    numriPjestues += 1;
                }
                iscalledListener3 = true;
                avgRatings.setText(String.valueOf(mesatarjaRatings(numriPjestues)));
                txtRatMadhesia.setText(String.valueOf(numRating3));
            }
        });
        ratingBar4.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numRating4 = rating;
                if (iscalledListener4 == false) {
                    numriPjestues += 1;
                }
                iscalledListener4 = true;
                avgRatings.setText(String.valueOf(mesatarjaRatings(numriPjestues)));
                txtRatVentilimi.setText(String.valueOf(numRating4));
            }
        });
        ratingBar5.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numRating5 = rating;
                if (iscalledListener5 == false) {
                    numriPjestues += 1;
                }
                iscalledListener5 = true;
                avgRatings.setText(String.valueOf(mesatarjaRatings(numriPjestues)));
                txtRatKomoditeti.setText(String.valueOf(numRating5));
            }
        });
        ratingBar6.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numRating6 = rating;
                if (iscalledListener6 == false) {
                    numriPjestues += 1;
                }
                iscalledListener6 = true;
                avgRatings.setText(String.valueOf(mesatarjaRatings(numriPjestues)));
                txtRatTextView1.setText(String.valueOf(numRating6));
            }
        });
        ratingBar7.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                numRating7 = rating;
                if (iscalledListener7 == false) {
                    numriPjestues += 1;
                }
                iscalledListener7 = true;
                avgRatings.setText(String.valueOf(mesatarjaRatings(numriPjestues)));
                txtRatTextView2.setText(String.valueOf(numRating7));
            }
        });

    }

    public double mesatarjaRatings(int n) {
        float shuma = (numRating1 + numRating2 + numRating3 + numRating4 + numRating5 + numRating6 + numRating7) / n;
        double shumaShkurtuar;
        shumaShkurtuar = Double.parseDouble(new DecimalFormat("##.#").format(shuma));
        return shumaShkurtuar;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        Bundle extras = getIntent().getExtras();
        String name;
        double lat;
        double lng;
        String type;

        if (extras != null) {
            name = extras.getString("name");
            lat = extras.getDouble("latitude");
            lng = extras.getDouble("longitude");
            type = extras.getString("type");

            //Toast.makeText(getApplicationContext(), lat + " ," + lng, Toast.LENGTH_LONG).show();
            txtName1.setText(name);
            txtType1.setText(type);

            goToMapPoint(lat, lng, name);
        }
    }

    public void callGPS() {
        ActivityCompat.requestPermissions(ListLocationsDetailsActivity.this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        GPStracker g = new GPStracker(getApplication());
        Location l = g.getLocation();

        if (l != null) {
            double latGPS = l.getLatitude();
            double lonGPS = l.getLongitude();

            goToMapPoint(latGPS, lonGPS, "Your location");
        }

    }

    public void goToMapPoint(double latitude, double longitude, String title) {

        LatLng location = new LatLng(latitude, longitude);

        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude))
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
                .fillColor(Color.argb(64, 0, 255, 0)));
    }

    public void noStatusBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
