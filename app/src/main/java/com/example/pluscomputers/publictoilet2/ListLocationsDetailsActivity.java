package com.example.pluscomputers.publictoilet2;

import android.Manifest;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
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

import java.text.DecimalFormat;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class ListLocationsDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mMap;

    TextView txtName1, txtDescribtion;
    ImageView gpsFixed2;
    Double latGPS, lonGPS;
    Boolean gpsCalled = false;
    private boolean fullScreen = false;
    float colorMarkerGPS = BitmapDescriptorFactory.HUE_BLUE;
    private LinearLayout linearLayoutRoot;
    private FrameLayout frameMap1;


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

        linearLayoutRoot = findViewById(R.id.linearLayoutRoot);
        frameMap1 = findViewById(R.id.frameLayout);

        txtName1 = findViewById(R.id.name1);
        txtDescribtion = findViewById(R.id.describtion);

        ratingBar1 = findViewById(R.id.ratingBar1);
        ratingBar2 = findViewById(R.id.ratingBar2);
        ratingBar3 = findViewById(R.id.ratingBar3);
        ratingBar4 = findViewById(R.id.ratingBar4);
        ratingBar5 = findViewById(R.id.ratingBar5);
        ratingBar6 = findViewById(R.id.ratingBar6);
        ratingBar7 = findViewById(R.id.ratingBar7);

        gpsFixed2 = findViewById(R.id.gpsFixed2);

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

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        Bundle extras = getIntent().getExtras();
        String name;
        double lat;
        double lng;
        String describtion;

        gpsFixed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callGpsOnTouch();
            }
        });

        if (extras != null) {
            name = extras.getString("name");
            lat = extras.getDouble("latitude");
            lng = extras.getDouble("longitude");
            describtion = extras.getString("describtion");

            //type = extras.getString("type");

            //Toast.makeText(getApplicationContext(), lat + " ," + lng, Toast.LENGTH_LONG).show();
            txtName1.setText(name);
            txtDescribtion.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s");
            //txtDescribtion.setText(describtion);

            goToMapPoint(lat, lng, name);
        }

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng clickCoords) {

                boolean isFullScreen = false;

                ChangeBounds changeBounds = new ChangeBounds();
                changeBounds.setDuration(2000);

                if (fullScreen == false) {

                    isFullScreen = true;

                    Animation bottomDown = AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.bottom_down);

                    linearLayoutRoot.startAnimation(bottomDown);
                    linearLayoutRoot.setVisibility(View.INVISIBLE);

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ChangeBounds changeBounds = new ChangeBounds();
                            changeBounds.setDuration(2500);
                            TransitionManager.beginDelayedTransition(frameMap1, changeBounds);
                            changeHeightFull(frameMap1);
                        }
                    }, 2000);
                } else {
                    changeHeightNormal(frameMap1);

                    isFullScreen = false;

                    TransitionManager.beginDelayedTransition(frameMap1, changeBounds);

                    Animation bottomUp = AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.bottom_up);

                    linearLayoutRoot.startAnimation(bottomUp);
                    linearLayoutRoot.setVisibility(View.VISIBLE);
                }
                fullScreen = isFullScreen;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int resId = item.getItemId();

        if (resId == R.id.action_language) {
            Toast.makeText(getApplicationContext(), "You selected language settings", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);


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

    public void callGpsOnTouch() {

        ActivityCompat.requestPermissions(ListLocationsDetailsActivity.this, new String[]{
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

        goToMapPoint(latGPS, lonGPS, "Your Location");

        gpsCalled = true;
    }

    public void callGPS() {
        ActivityCompat.requestPermissions(ListLocationsDetailsActivity.this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        GPStracker g = new GPStracker(getApplication());
        Location l = g.getLocation();

        if (l != null) {
            latGPS = l.getLatitude();
            lonGPS = l.getLongitude();

            goToMapPoint(latGPS, lonGPS, "Your location");
        }

    }

    public void createPointMap(double latitude, double longitude, String title, float color) {

        LatLng location = new LatLng(latitude, longitude);

        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude))
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
                .fillColor(Color.argb(64, 0, 255, 0)));
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

    public void changeHeightFull(View... views) {
        for (View current : views) {
            ViewGroup.LayoutParams params = current.getLayoutParams();
            params.height = MATCH_PARENT;
            params.width = MATCH_PARENT;
            current.setLayoutParams(params);
        }
    }

    public void changeHeightNormal(View... views) {
        for (View current : views) {
            ViewGroup.LayoutParams params = current.getLayoutParams();
            params.height = 800;
            params.width = MATCH_PARENT;
            current.setLayoutParams(params);
        }
    }
}
