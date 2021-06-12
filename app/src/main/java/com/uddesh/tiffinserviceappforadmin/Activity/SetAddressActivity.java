package com.uddesh.tiffinserviceappforadmin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.OnSymbolDragListener;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;
import com.mapbox.mapboxsdk.utils.BitmapUtils;
import com.uddesh.tiffinserviceappforadmin.R;
import java.util.List;


public class SetAddressActivity extends AppCompatActivity implements OnMapReadyCallback, PermissionsListener {
    private MapView mapView;
    private PermissionsManager permissionsManager;
    private MapboxMap mapboxMap;
    private Symbol symbol;
    private EditText address_edittext;
    private Button set_location_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
        setContentView(R.layout.activity_set_address);
        mapView =  findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        address_edittext = findViewById(R.id.address_edittext);
        set_location_button = findViewById(R.id.set_location_button);
        set_location_button.setOnClickListener(view -> {
            Intent intent = new Intent(this , HomePageActivity.class);
            startActivity(intent);
            finish();

        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {

    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            mapboxMap.getStyle(this::enableLocationComponent);
        } else {
            Toast.makeText(this, "Please grant permission to continue" , Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        SetAddressActivity.this.mapboxMap = mapboxMap;
        mapboxMap.setStyle(Style.SATELLITE_STREETS, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                enableLocationComponent(style);
                Location location = mapboxMap.getLocationComponent().getLastKnownLocation();
                style.addImage("LOCATION_MARKER" , BitmapUtils.getBitmapFromDrawable(getResources().getDrawable(R.drawable.ic_location_marker)));
                SymbolManager symbolManager = new SymbolManager(mapView, mapboxMap, style);
                symbolManager.setIconAllowOverlap(true);
                symbolManager.setTextAllowOverlap(true);
                SymbolOptions symbolOptions = new SymbolOptions()
                        .withLatLng(new LatLng(location.getLatitude() , location.getLongitude()))
                        .withIconImage("LOCATION_MARKER")
                        .withIconSize(1.3f)
                        .withDraggable(true);
                symbol = symbolManager.create(symbolOptions);
                symbolManager.addDragListener(new OnSymbolDragListener() {
                    @Override
                    public void onAnnotationDragStarted(Symbol annotation) {

                    }

                    @Override
                    public void onAnnotationDrag(Symbol annotation) {

                    }

                    @Override
                    public void onAnnotationDragFinished(Symbol annotation) {
                        Log.i("tag", symbol.getLatLng()+"");
                    }
                });
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    // private functions
    @SuppressWarnings( {"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            LocationComponent locationComponent = mapboxMap.getLocationComponent();
            locationComponent.activateLocationComponent(LocationComponentActivationOptions.builder(this, loadedMapStyle).build());
            locationComponent.setLocationComponentEnabled(true);
            locationComponent.setCameraMode(CameraMode.TRACKING);
            locationComponent.setRenderMode(RenderMode.COMPASS);
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }
}