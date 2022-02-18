package com.example.work06

import android.Manifest
import android.app.Activity
import androidx.fragment.app.Fragment

import android.os.Bundle
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

class MapsActivity(val activity:Activity) : Fragment(), OnMapReadyCallback {

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

        return inflater.inflate(R.layout.activity_maps,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
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
        }
    }



}