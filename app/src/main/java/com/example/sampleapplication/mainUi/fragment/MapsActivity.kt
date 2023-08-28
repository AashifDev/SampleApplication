package com.example.sampleapplication.mainUi.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.sampleapplication.R
import com.example.sampleapplication.databinding.ActivityMapsBinding
import com.example.sampleapplication.mainUi.PlaceArrayAdapter
import com.example.sampleapplication.model.PlaceDataModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import java.util.Locale


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var currentLocation: Location? = null
    lateinit var fusedLocationClient: FusedLocationProviderClient

    private var placeAdapter: PlaceArrayAdapter? = null
    private lateinit var mPlacesClient: PlacesClient
    lateinit var placeDataModel: ArrayList<PlaceDataModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        placeDataModel = arrayListOf()

        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, getString(R.string.api_key), Locale.US);
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        mPlacesClient = Places.createClient(this)

        placeAdapter = PlaceArrayAdapter(this,R.layout.layout_item_places, mPlacesClient)

        binding.autoCompleteEditText.setAdapter(placeAdapter)

        binding.autoCompleteEditText.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val place = parent.getItemAtPosition(position) as PlaceDataModel
            binding.autoCompleteEditText.apply {
                setText(place.toString)
                setSelection(binding.autoCompleteEditText.length())
                var addressList: List<Address> = listOf()
                val geoCoder = Geocoder(this@MapsActivity)
                try {
                    addressList = geoCoder.getFromLocationName(place.toString(), 5) as List<Address>

                    if (addressList != null) {
                        val location: Address = addressList[0]

                        val lat = location.latitude
                        val lng = location.longitude
                        val country = location.countryCode

                        setUpMap(place,lat,lng, country)
                    }
                } catch (e: Exception) {
                    e.message
                }

            }
        }


    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) {location->
            if (location != null){
                currentLocation = location
                val currentLoc = LatLng(location.latitude,location.longitude)
               /* val boundsIndia = LatLngBounds(LatLng(23.63936, 68.14712), LatLng(28.20453, 97.34466))
                val padding = 0 // offset from edges of the map in pixels

                val cameraUpdate = CameraUpdateFactory.newLatLngBounds(boundsIndia, padding)
                mMap.animateCamera(cameraUpdate)*/
                placeMarkerOnMap(currentLoc)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLoc,12f))
            //mMap.addMarker(MarkerOptions().position(currentLoc).title("sdfsdf"))
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
            }
        }
    }

    private fun setUpMap(place: PlaceDataModel, lat: Double, lng: Double, country: String) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) {location->
            if (location != null){
                currentLocation = location
                val currentLoc = LatLng(lat,lng)
                placeMarkerOnMap(currentLoc)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLoc,12f))
                mMap.addMarker(MarkerOptions().position(currentLoc).title(place.toString))
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
            }
        }
    }

    private fun isLocationPermissionGranted(): Boolean {
        return if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                101
            )
            false
        } else {
            true
        }
    }

    private fun placeMarkerOnMap(currentLoc: LatLng) {
        val markerOptions = MarkerOptions().position(currentLoc)
        markerOptions.title("$currentLoc")
        mMap.addMarker(markerOptions)
    }

    override fun onStart() {
        super.onStart()
        isLocationPermissionGranted()
    }

    override fun onMarkerClick(p0: Marker) = false
}
