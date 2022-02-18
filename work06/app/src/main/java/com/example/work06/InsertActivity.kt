package com.example.work06

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat

class InsertActivity : AppCompatActivity() {

    val CAMERA = arrayOf(Manifest.permission.CAMERA)
    val STORAGE = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)
    val CAMERA_CODE = 98
    val STORAGE_CODE = 99

    lateinit var locationPermission: ActivityResultLauncher<Array<String>>
    // 위치 서비스가 GPS를 사용해서 위치를 확인
    lateinit var fusedLocationClient: FusedLocationProviderClient
    // 위치 값 요청에 대한 갱신 정보를 받는 변수
    lateinit var locationCallback: LocationCallback
    var imageAddress = ""
    var lat:Double = 0.0
    var lon:Double = 0.0
    var addr = ""
    var texttext = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)


        val edittxt = findViewById<EditText>(R.id.editText1)
        val textView = findViewById<TextView>(R.id.textView)
        val camera = findViewById<Button>(R.id.cameraBtn)
        val insert = findViewById<Button>(R.id.insertBtn)

        locationPermission = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()){ result ->
                if(!result.all { it.value }){
                    Toast.makeText(this,"권한 승인이 필요합니다",Toast.LENGTH_SHORT).show()
                }

            }


        locationPermission.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        updateLocation()
        camera.setOnClickListener {
            CallCamera()
            textView.text = texttext
        }
        insert.setOnClickListener {
            val title = edittxt.text.toString()
            textView.text = texttext
            val test:VO = VO(0,imageAddress,title,"",addr)
            DBHelper.getInstance(this,"DATA.db").insert(test)
            println("==============="+test.imgaddr)
            println("==============="+test.title)
            println("==============="+test.address)
        }
    }

        // 카메라 권한, 저장소 권한
        // 요청 권한
        override fun onRequestPermissionsResult(requestCode: Int,
                                                permissions: Array<out String>, grantResults: IntArray) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)

            when(requestCode){
                CAMERA_CODE -> {
                    for (grant in grantResults){
                        if(grant != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this, "카메라 권한을 승인해 주세요", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                STORAGE_CODE -> {
                    for(grant in grantResults){
                        if(grant != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this, "저장소 권한을 승인해 주세요", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }

        // 다른 권한등도 확인이 가능하도록
        fun checkPermission(permissions: Array<out String>, type:Int):Boolean{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                for (permission in permissions){
                    if(ContextCompat.checkSelfPermission(this, permission)
                        != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(this, permissions, type)
                        return false
                    }
                }
            }
            return true
        }

        // 카메라 촬영 - 권한 처리
        fun CallCamera(){
            if(checkPermission(CAMERA, CAMERA_CODE) && checkPermission(STORAGE, STORAGE_CODE)){
                val itt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(itt, CAMERA_CODE)
            }
        }

        // 사진 저장
        fun saveFile(fileName:String, mimeType:String, bitmap: Bitmap): Uri?{

            var CV = ContentValues()

            // MediaStore 에 파일명, mimeType 을 지정
            CV.put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            CV.put(MediaStore.Images.Media.MIME_TYPE, mimeType)

            // 안정성 검사
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                CV.put(MediaStore.Images.Media.IS_PENDING, 1)
            }

            // MediaStore 에 파일을 저장
            val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, CV)
            if(uri != null){
                var scriptor = contentResolver.openFileDescriptor(uri, "w")

                val fos = FileOutputStream(scriptor?.fileDescriptor)

                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                fos.close()

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                    CV.clear()
                    // IS_PENDING 을 초기화
                    CV.put(MediaStore.Images.Media.IS_PENDING, 0)
                    contentResolver.update(uri, CV, null, null)
                }
            }
            return uri
        }

        fun getPath(uri: Uri?): String {
            val projection = arrayOf<String>(MediaStore.Images.Media.DATA)
            val cursor: Cursor = managedQuery(uri, projection, null, null, null)
            startManagingCursor(cursor)
            val columnIndex: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            return cursor.getString(columnIndex)
        }


        // 결과
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            val imageView = findViewById<ImageView>(R.id.imageView)

            if(resultCode == Activity.RESULT_OK){
                when(requestCode){
                    CAMERA_CODE -> {
                        if(data?.extras?.get("data") != null){
                            val img = data?.extras?.get("data") as Bitmap
                            val uri = saveFile(RandomFileName(), "image/jpeg", img)
                            imageView.setImageURI(uri)
                            println("이미지 경로 : $uri")
                            println("실제 이미지 경로: "+ getPath(uri))
                            imageAddress = getPath(uri)
                            texttext = getPath(uri)

                        }
                    }
                    STORAGE_CODE -> {
                        val uri = data?.data
                        imageView.setImageURI(uri)
                    }
                }
            }
        }

        // 파일명을 날짜 저장
        fun RandomFileName() : String{
            val fileName = SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis())
            return fileName
        }


    @SuppressLint("MissingPermission")
    fun updateLocation(){
        val locationRequest = LocationRequest.create()
        locationRequest.run {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 1000
        }
        locationCallback = object :LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult?.let {
                    for (location in it.locations){
                        Log.d("위치정보","위도:${location.latitude} 경도:${location.longitude}")
                        lat = location.latitude
                        lon = location.longitude
                        addrChange(lat,lon)
                    }

                }
            }
        }
        fusedLocationClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper())
    }
    fun addrChange(lat:Double,lon:Double){
        var list:List<Address>? = null
        try {
            val d1:Double = lat
            val d2:Double = lon
            println(d1)
            println(d2)
            val geocoder:Geocoder = Geocoder(this)
            list = geocoder.getFromLocation(d1,d2,10)
        }catch (e: IOException){
            Log.d("위도/경도","입출력 오류" +
                    "")
        }
        if (list != null){
            if (list.isEmpty()){
                Toast.makeText(this,"해당되는 주소가 없습니다.",Toast.LENGTH_SHORT).show()
            }else{ //정상적으로 산출됨
                addr = list[0].getAddressLine(0)
                println("-=-=-=-=-=-=-=-="+addr)
                texttext = addr
            }
        }
    }

}