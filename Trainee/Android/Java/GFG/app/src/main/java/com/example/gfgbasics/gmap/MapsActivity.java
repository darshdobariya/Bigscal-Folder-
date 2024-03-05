package com.example.gfgbasics.gmap;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.gfgbasics.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.gfgbasics.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng sydney = new LatLng(-34, 151);
    LatLng TamWorth = new LatLng(-31.083332, 150.916672);
    LatLng NewCastle = new LatLng(-32.916668, 151.750000);
    LatLng Brisbane = new LatLng(-27.470125, 153.021072);
    private ArrayList<LatLng> locationArrayList;
    Double distance;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.gfgbasics.databinding.ActivityMapsBinding binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button hybridMapBtn = findViewById(R.id.idBtnHybridMap);
        Button terrainMapBtn = findViewById(R.id.idBtnTerrainMap);
        Button satelliteMapBtn = findViewById(R.id.idBtnSatelliteMap);

        searchView = findViewById(R.id.searchViewMap);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        // add some data in list
        locationArrayList = new ArrayList<>();
        locationArrayList.add(sydney);
        locationArrayList.add(TamWorth);
        locationArrayList.add(NewCastle);
        locationArrayList.add(Brisbane);

        // adding on click listener for our hybrid map button.
        hybridMapBtn.setOnClickListener(v-> mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID));

        // adding on click listener for our terrain map button.
        terrainMapBtn.setOnClickListener(v -> mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN));

        // adding on click listener for our satellite map button.
        satelliteMapBtn.setOnClickListener(v -> mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE));

        // adding on query listener for our search view.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // location name from search view.
                String location = searchView.getQuery().toString();

                // where we will store the list of all address.
                List<Address> addressList = null;
                Address address;

                // checking if the entered location is null or not.
                // on below line we are creating and initializing a geo coder.
                Geocoder geocoder = new Geocoder(MapsActivity.this);
                try {
                    // on below line we are getting location from the
                    // location name and adding that location to address list.
                    addressList = geocoder.getFromLocationName(location, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MapsActivity.this, "enter valid city name", Toast.LENGTH_SHORT).show();
                }
                // on below line we are getting the location
                // from our list a first position.
                try {
                    assert addressList != null;
                    if (addressList.size() > 0){
                        address = addressList.get(0);

                        // on below line we are creating a variable for our location
                        // where we will add our locations latitude and longitude.
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                        // on below line we are adding marker to that position.
                        mMap.addMarker(new MarkerOptions().position(latLng).title(location));

                        // below line is to animate camera to that position.
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                    }else Toast.makeText(MapsActivity.this, "Enter Valid city name", Toast.LENGTH_SHORT).show();
                }catch (ArrayIndexOutOfBoundsException e){
                    e.printStackTrace();
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        // at last we calling our map fragment to update.
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney")
                .icon(BitmapFromVector(getApplicationContext(), R.drawable.location)));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        for (int i = 0; i < locationArrayList.size(); i++) {
            mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
        }

        // adding on click listener to marker of google maps.
        mMap.setOnMarkerClickListener(marker -> {
            String markerName = marker.getTitle();
            Toast.makeText(MapsActivity.this, "Clicked location is " + markerName, Toast.LENGTH_SHORT).show();
            return false;
        });

        //draw line in range like, square and so on ...
        mMap.addPolyline((new PolylineOptions()).add(Brisbane, NewCastle, TamWorth, Brisbane).
                width(5)
                .color(Color.RED)
                .geodesic(true));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Brisbane, 13));

        //find distance between 2 location
        distance = SphericalUtil.computeDistanceBetween(sydney, Brisbane);
        Toast.makeText(this, distance / 1000 + "km", Toast.LENGTH_SHORT).show();

        mMap.setOnMapClickListener(point -> mMap.addMarker(new MarkerOptions()
                .position(point)
                .title("Marker in Sydney")
                .icon(BitmapFromVector(getApplicationContext(), R.drawable.location))));
    }

    private BitmapDescriptor
    BitmapFromVector(Context context, int vectorResId)
    {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);

        // below line is use to set bounds to our vector
        assert vectorDrawable != null;
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        Bitmap bitmap = Bitmap.createBitmap(
                vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}