package com.janani123.firebaseregistrationlogin;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private String TAG = "so47492459";
    private GoogleMap myMap;
    static TextView latitude;
    static TextView longitude;
    static public double lat;
    static public double lang;

    Button bus;
    Firebase ref;
    String percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Firebase.setAndroidContext(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        ref = new Firebase("https://garbage-8e29d.firebaseio.com/distance");
        mapFragment.getMapAsync(this);
        CheckUserPermsions();

        //  latitude = (TextView) findViewById(R.id.lat);
        //  longitude = (TextView) findViewById(R.id.longi);
        bus = (Button) findViewById(R.id.button);


        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                fetchData process = new fetchData();
                process.execute();

                LatLng current = new LatLng(lat,lang);


                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Integer value = dataSnapshot.getValue(Integer.class);
                        System.out.println(value);
                        if(value<=12)
                        mMap.addMarker(new MarkerOptions().position(current).icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).title("Alerted Garbage Dumpster Location.Percent filled is 100%"));


                        else if(value>12&&value<=36)
                            mMap.addMarker(new MarkerOptions().position(current).icon(BitmapDescriptorFactory
                                    .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("Alerted Garbage Dumpster Location.Percent filled is 80%"));


                        else if(value>36&&value<=48)
                            mMap.addMarker(new MarkerOptions().position(current).icon(BitmapDescriptorFactory
                                    .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)).title("Alerted Garbage Dumpster Location.Percent filled is 60%"));


                        else if(value>48&&value<=60)
                            mMap.addMarker(new MarkerOptions().position(current).icon(BitmapDescriptorFactory
                                    .defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)).title("Alerted Garbage Dumpster Location.Percent filled is 40%"));


                        else
                            mMap.addMarker(new MarkerOptions().position(current).icon(BitmapDescriptorFactory
                                    .defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).title("Alerted Garbage Dumpster Location.Percent filled is 20%"));


                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

                // Add a marker in Sydney and move the camera



                // mMap.moveCamera(CameraUpdateFactory.newLatLng(current));


            }
        });








    }

    void CheckUserPermsions(){
        if ( Build.VERSION.SDK_INT >= 23){
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED  ){
                requestPermissions(new String[]{
                                android.Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return ;
            }
        }

        runlisner();
    }

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    runlisner();
                } else {

                    Toast.makeText( this,"cannot access " , Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    void runlisner(){
        locationlisner myloc=new locationlisner();
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,3, 1000, myloc);

        mythread myth = new mythread();
        myth.start();

    }

    class mythread extends  Thread{
        public void  run(){


            while(true){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(locationlisner.location != null) {
                            LatLng present = new LatLng(locationlisner.location.getLatitude(), locationlisner.location.getLongitude());

                            mMap.addMarker(new MarkerOptions().position(present).title("My Location"));
                            //   mMap.moveCamera(CameraUpdateFactory.newLatLng(present));
                        }
                    }
                });


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }






    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



    }
}