package com.example.googlemapfragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.location.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment(val activity:Activity) : Fragment(), OnMapReadyCallback {

    /*private val callback = OnMapReadyCallback { googleMap ->

        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }*/
    var a = 0
    //맵
    private lateinit var mMap: GoogleMap
    //권한
    lateinit var locationPermission: ActivityResultLauncher<Array<String>>
    //위치 서비스가 GPS를 사용해서 위치를 확인 (현재위치)
    lateinit var fusedLocationClient: FusedLocationProviderClient
    //위치 값 요청에 대한 갱신 정보를 받는 변수 (이동하면서 바뀌는 위치)
    lateinit var locationCallback: LocationCallback

    override fun onCreateView( //맨처음에 들어오는것
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        locationPermission = registerForActivityResult( ActivityResultContracts.RequestMultiplePermissions() ){ results->
            if (!results.all { it.value }){
                Toast.makeText(activity,"권한 승인이 필요합니다",Toast.LENGTH_LONG).show()
            }
        }

        //권한 요청
        locationPermission.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        updateLocation()
    }

    @SuppressLint("MissingPermission")
    fun updateLocation(){
        val locationRequest = LocationRequest.create()
        locationRequest.run {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 1000
        }

        locationCallback = object :LocationCallback(){
            //1초에 한번씩 변경된 위치 정보가 onLocationResult로 전달된다
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult?.let {
                    for (location in it.locations){
                        Log.d("위치정보"," - 위도:${location.latitude} 경도:${location.longitude}")
                        setLastLocation(location)
                    }
                }
            }
        }
        //권한 처리
        fusedLocationClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper())


    }

    fun setLastLocation(lastLocation: Location){
        val LATLNG = LatLng(lastLocation.latitude,lastLocation.longitude)


        val makerOptions = MarkerOptions().position(LATLNG).title("i am her")
        val cameraPosition = CameraPosition.Builder().target(LATLNG).zoom(15.0f).build()

        mMap.clear()
        mMap.addMarker(makerOptions)
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }


    fun setLocation(latitude:Double,longitude:Double){
        if (latitude != 0.0 || longitude != 0.0) {
            println("---------------" + latitude)
            println("---------------" + longitude)
            val LATLNG = LatLng(latitude, longitude)

            val makerOptions = MarkerOptions().position(LATLNG).title("i am her")
            val cameraPosition = CameraPosition.Builder().target(LATLNG).zoom(15.0f).build()
            mMap.clear()
            mMap.addMarker(makerOptions)
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            a = 1
        }
    }



}