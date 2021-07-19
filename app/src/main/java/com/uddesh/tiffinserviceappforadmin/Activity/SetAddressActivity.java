package com.uddesh.tiffinserviceappforadmin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
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
import com.uddesh.tiffinserviceappforadmin.BuildConfig;
import com.uddesh.tiffinserviceappforadmin.DataModels.AdminLocationModel;
import com.uddesh.tiffinserviceappforadmin.Helpers.SharedPreferencesHelper;
import com.uddesh.tiffinserviceappforadmin.Helpers.ToastHelper;
import com.uddesh.tiffinserviceappforadmin.R;
import com.uddesh.tiffinserviceappforadmin.Repository.RetrofitViewModel;
import java.util.List;


public class SetAddressActivity extends AppCompatActivity implements OnMapReadyCallback, PermissionsListener {
    private MapView mapView;
    private PermissionsManager permissionsManager;
    private MapboxMap mapboxMap;
    private EditText address_edittext;
    private SharedPreferencesHelper sharedPreferences;
    private RetrofitViewModel viewModel;
    private Symbol symbol;
    private String location;
    private double latitude , longitude;
    private ToastHelper toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, BuildConfig.MapBoxApiKey);
        setContentView(R.layout.activity_set_address);
        sharedPreferences = new SharedPreferencesHelper(this);
        viewModel = new RetrofitViewModel(getApplication());
        toast = new ToastHelper(this);
        mapView =  findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        address_edittext = findViewById(R.id.address_edittext);
        Button set_location_button = findViewById(R.id.set_location_button);
        set_location_button.setOnClickListener(view -> {
            location = address_edittext.getText().toString();
            String username = sharedPreferences.getSharedPreferences("username");
            if(!location.equals("")) {
                viewModel.updateAdminLocation(new AdminLocationModel(location, latitude, longitude, username)).observe(this, result -> {
                    if (result) {
                        if(getIntent().getBooleanExtra("fromHomePage" , false)){
                            toast.makeToast("Location updated successfully" , Toast.LENGTH_LONG);
                            Intent intent = new Intent(this, HomePageActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                        else{
                            Intent intent = new Intent(this, PersonalDetailsActivity.class);
                            startActivity(intent);
                        }
                        finish();
                    } else {
                        toast.makeToast("Something went wrong", Toast.LENGTH_LONG);
                    }
                });
            }
            else{
                toast.makeToast("Enter the address" , Toast.LENGTH_LONG);
            }

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
        mapboxMap.setStyle(Style.SATELLITE_STREETS, style -> {
            enableLocationComponent(style);
            Location location = mapboxMap.getLocationComponent().getLastKnownLocation();
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            style.addImage("LOCATION_MARKER" , BitmapUtils.getBitmapFromDrawable(getResources().getDrawable(R.drawable.ic_location_marker)));
            SymbolManager symbolManager = new SymbolManager(mapView, mapboxMap, style);
            symbolManager.setIconAllowOverlap(true);
            symbolManager.setTextAllowOverlap(true);
            SymbolOptions symbolOptions = new SymbolOptions()
                    .withLatLng(new LatLng(latitude , longitude))
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
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }
            });
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