package com.example.currentmaps1;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.LOCATION_SERVICE;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {

    private GoogleMap mMap;

    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    private LocationManager mLocationManager;
    private LocationRequest mLocationRequest;
    private com.google.android.gms.location.LocationListener listener;
    private long UPDATE_INTERVAL =2000;
    private long FASTEST_INTERVAL = 5000;
    private LocationManager locationManager;
    private LatLng latLng;
    private boolean isPermission;

    /** latitude longtitude **/
    private  TextView kor1, kor2, kor3, kor4, idEmp,idEmp2;

    private TextView id;
    private TextView tunamayo;
    private TextView hottuna;
    private TextView chickenmayo;
    private TextView hotchicken;
    private TextView terii;
    private TextView kode;
    private TextView outlet;
    private TextView tgl;

    private Button simpanData;
    private Button tombolCamera;

    private ImageView imgCamera;
    final int kodeKamera = 99;
    Uri imageUri;

    /** upload foto **/

    Bitmap bitmap, bitbit;

    boolean check = true;

    Button SelectImageGallery;

    EditText imageName;

    ProgressDialog progressDialog ;

    String GetImageNameEditText, id_2;

    String ImageName = "image_name" ;

    String ImagePath = "image_path" ;

    String ServerUploadPath ="http://gwalakb.000webhostapp.com/suki_new/img_upload_to_server.php" ;

    /** batas upload foto **/

    private String iid;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        final Intent intent = getIntent();
        iid = intent.getStringExtra("id2");
        idEmp = (TextView) findViewById(R.id.sp_kode);
        idEmp2 = (TextView) findViewById(R.id.sp_kode2);


        TextView hijau_Db = (TextView) findViewById(R.id.textView51);
        TextView merah_Db = (TextView) findViewById(R.id.textView52);
        TextView biru_Db = (TextView) findViewById(R.id.textView53);
        TextView orange_Db = (TextView) findViewById(R.id.textView54);
        TextView ungu_Db = (TextView) findViewById(R.id.textView55);


        /** latitude longtitude **/
        kor1 = (TextView) findViewById(R.id.kor1);
        kor2 = (TextView) findViewById(R.id.kor2);
        kor3 = (TextView) findViewById(R.id.kor3);
        kor4 = (TextView) findViewById(R.id.kor4);

        /**idEmp.setText(getIntent().getStringExtra("id2"));**/
        idEmp2.setText(iid);

        /** Mengambil Data Dari Main2Activity **/
        hijau_Db.setText(getIntent().getStringExtra("ambil_HijauMysql"));
        merah_Db.setText(getIntent().getStringExtra("ambil_MerahMysql"));
        biru_Db.setText(getIntent().getStringExtra("ambil_BiruMysql"));
        orange_Db.setText(getIntent().getStringExtra("ambil_OrangeMysql"));
        ungu_Db.setText(getIntent().getStringExtra("ambil_UnguMysql"));
        /** Batas Mengambil Data Dari Main2Activity **/


        id = (TextView) findViewById(R.id.idu);
        tunamayo = (TextView) findViewById(R.id.editTextTunamayo);
        hottuna = (TextView) findViewById(R.id.editTextHottuna);
        chickenmayo = (TextView) findViewById(R.id.editTextChickenmayo);
        hotchicken = (TextView) findViewById(R.id.editTextHotchicken);
        terii = (TextView) findViewById(R.id.editTextTeri);


        /** Mengambil Data Edit Text Main2Activity **/
        tunamayo.setText(getIntent().getStringExtra("ambilDataTunaMayo"));
        hottuna.setText(getIntent().getStringExtra("ambilDataHotTuna"));
        chickenmayo.setText(getIntent().getStringExtra("ambilDataChickenMayo"));
        hotchicken.setText(getIntent().getStringExtra("ambilDataHotChicken"));
        terii.setText(getIntent().getStringExtra("ambilDataTeri"));
        /** Batas Mengambil Data Edit Text Main2Activity **/


        imageName = (EditText)findViewById(R.id.editTextImageName);

        SelectImageGallery = (Button)findViewById(R.id.buttonSelect);
        imgCamera = (ImageView) findViewById(R.id.img_camera);
        simpanData = (Button) findViewById(R.id.button_simpan);
        tombolCamera = (Button) findViewById(R.id.button_camera);


        if (requestSinglePermission()) {

            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();

            mLocationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

            checkLocation();

        }

        /** upload foto **/

        SelectImageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();

                intent.setType("image/*");

                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);

            }
        });

        /**batas upload foto**/


        simpanData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmployee();
                ImageUploadToServerFunction();
                GetImageNameEditText = imageName.getText().toString();
                    Intent intent = new Intent(MapsActivity.this, Login.class);
                    startActivity(intent);
            }
        });

        tombolCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, kodeKamera);
            }
        });


        idEmp.setText(iid);
        getLatlang();
    }








    private void addEmployee(){

        final String nk = id.getText().toString().trim();
        final String mayotuna = tunamayo.getText().toString().trim();
        final String tunahot = hottuna.getText().toString().trim();
        final String mayochicken = chickenmayo.getText().toString().trim();
        final String chickenhot = hotchicken.getText().toString().trim();
        final String tr = terii.getText().toString().trim();


        class AddEmployee extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MapsActivity.this,"Menambahkan Returr...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MapsActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(konfigurasi.KEY_EMP_ID,nk);
                params.put(konfigurasi.KEY_TUNA_MAYO,mayotuna);
                params.put(konfigurasi.KEY_HOT_TUNA,tunahot);
                params.put(konfigurasi.KEY_CHICKEN_MAYO,mayochicken);
                params.put(konfigurasi.KEY_HOT_CHICKEN,chickenhot);
                params.put(konfigurasi.KEY_TERI,tr);


                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD_RETUR, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }




    public Bitmap x;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == kodeKamera && resultCode == RESULT_OK) {

            x = (Bitmap) data.getExtras().get("data");

            imgCamera.setImageBitmap(x);
        }
    }





    private boolean checkLocation() {

        if (!isLocationEnabled()){
            showAlert();
        }
        return isLocationEnabled();
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your Location Setting is set to 'off'.\nPlease enable Location to")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                    }
                });
        dialog.show();
    }


    private boolean isLocationEnabled() {

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }





    private boolean requestSinglePermission() {

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        isPermission = true;
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response){
                        if (response.isPermanentlyDenied()){
                            isPermission = false;
                        }
                    }


                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();

        return isPermission;
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

        if (latLng!=null){
            mMap.addMarker(new MarkerOptions().position(latLng).title("Lokasi Anda Saat Ini"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,14F));
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED){
            return;
        }
        startLocationUpdates();
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLocation == null){
            startLocationUpdates();
        }
        else {
            Toast.makeText(this,"Location not Detected", Toast.LENGTH_SHORT).show();
        }

    }


    private void startLocationUpdates() {

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED){
            return;
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                mLocationRequest,this);
    }




    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {


        String val = Double.toString(mLocation.getLatitude());
        String val1 = Double.toString(mLocation.getLongitude());

        double value = Double.parseDouble(val);
        double value1 = Double.parseDouble(val1);


        String msg = "Updated Location: " + Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude());
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        latLng = new LatLng(location.getLatitude(), location.getLongitude());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        /** mengambil LatLong Lokasi toko**/

        /** rumah : value == -7.7328679 && value1 == 110.590967   -7.731666  110.5902613
         * rumah redmi3 : value == -7.731666 && value1 == 110.5902613
         * versa : -7.7749265 , 110.4087518
         * versa : (value <= -7.7749000 && value >= -7.7751500 && value1 >= 110.4085001 && value1 <= 110.4087900)
         * versa : (value <= latlong1 && value >= latlong2 && value1 >= latlong3 && value1 <= latlong4)
         * **/
        String ll1 = kor1.getText().toString();
        String ll2 = kor2.getText().toString();
        String ll3 = kor3.getText().toString();
        String ll4 = kor4.getText().toString();

        double latlong1 = Double.parseDouble(ll1);
        double latlong2 = Double.parseDouble(ll2);
        double latlong3 = Double.parseDouble(ll3);
        double latlong4 = Double.parseDouble(ll4);


        if (value <= latlong1 && value >= latlong2 && value1 >= latlong3 && value1 <= latlong4) {
            simpanData.setEnabled(true);
        }else {
            simpanData.setEnabled(false);
        }return;


    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mGoogleApiClient !=null){
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mGoogleApiClient.isConnected()){
            mGoogleApiClient.disconnect();
        }
    }







    /**upload foto**/

    /**@Override
    protected void onActivityResult(int RC, int RQC, Intent I) {

        super.onActivityResult(RC, RQC, I);

        if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {

            Uri uri = I.getData();

            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                imgCamera.setImageBitmap(bitmap);

            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        if (RC == kodeKamera && RQC == RESULT_OK) {
            Bitmap bitbit = (Bitmap) I.getExtras().get("data");

            imgCamera.setImageBitmap(bitbit);
        }
    }**/

    public void ImageUploadToServerFunction(){

        ByteArrayOutputStream byteArrayOutputStreamObject ;

        byteArrayOutputStreamObject = new ByteArrayOutputStream();

        x.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);

        byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();

        final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);

        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {

            @Override
            protected void onPreExecute() {

                super.onPreExecute();

                progressDialog = ProgressDialog.show(MapsActivity.this,"Image is Uploading","Please Wait",false,false);
            }

            @Override
            protected void onPostExecute(String string1) {

                super.onPostExecute(string1);

                // Dismiss the progress dialog after done uploading.
                progressDialog.dismiss();

                // Printing uploading success message coming from server on android app.
                Toast.makeText(MapsActivity.this,string1,Toast.LENGTH_LONG).show();

                // Setting image as transparent after done uploading.
                imgCamera.setImageResource(android.R.color.transparent);


            }

            @Override
            protected String doInBackground(Void... params) {

                MapsActivity.ImageProcessClass imageProcessClass = new MapsActivity.ImageProcessClass();

                HashMap<String,String> HashMapParams = new HashMap<String,String>();

                HashMapParams.put(ImageName, GetImageNameEditText);

                HashMapParams.put(ImagePath, ConvertImage);

                String FinalData = imageProcessClass.ImageHttpRequest(ServerUploadPath, HashMapParams);

                return FinalData;
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();

        AsyncTaskUploadClassOBJ.execute();
    }





    public class ImageProcessClass{

        public String ImageHttpRequest(String requestURL,HashMap<String, String> PData) {

            StringBuilder stringBuilder = new StringBuilder();

            try {

                URL url;
                HttpURLConnection httpURLConnectionObject ;
                OutputStream OutPutStream;
                BufferedWriter bufferedWriterObject ;
                BufferedReader bufferedReaderObject ;
                int RC ;

                url = new URL(requestURL);

                httpURLConnectionObject = (HttpURLConnection) url.openConnection();

                httpURLConnectionObject.setReadTimeout(19000);

                httpURLConnectionObject.setConnectTimeout(19000);

                httpURLConnectionObject.setRequestMethod("POST");

                httpURLConnectionObject.setDoInput(true);

                httpURLConnectionObject.setDoOutput(true);

                OutPutStream = httpURLConnectionObject.getOutputStream();

                bufferedWriterObject = new BufferedWriter(

                        new OutputStreamWriter(OutPutStream, "UTF-8"));

                bufferedWriterObject.write(bufferedWriterDataFN(PData));

                bufferedWriterObject.flush();

                bufferedWriterObject.close();

                OutPutStream.close();

                RC = httpURLConnectionObject.getResponseCode();

                if (RC == HttpsURLConnection.HTTP_OK) {

                    bufferedReaderObject = new BufferedReader(new InputStreamReader(httpURLConnectionObject.getInputStream()));

                    stringBuilder = new StringBuilder();

                    String RC2;

                    while ((RC2 = bufferedReaderObject.readLine()) != null){

                        stringBuilder.append(RC2);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {

            StringBuilder stringBuilderObject;

            stringBuilderObject = new StringBuilder();

            for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {

                if (check)

                    check = false;
                else
                    stringBuilderObject.append("&");

                stringBuilderObject.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));

                stringBuilderObject.append("=");

                stringBuilderObject.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
            }

            return stringBuilderObject.toString();
        }

    }

    /** batas upload foto **/




    /** latitude longtitude **/
    private void getLatlang(){
        class GetLatlang extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MapsActivity.this,"Tunggu Yaa...","Sabaarr!!..",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showLatlang(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_LATLONG,iid);
                return s;
            }


        }
        GetLatlang ge = new GetLatlang();
        ge.execute();
    }



    private void showLatlang(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String ed = c.getString(konfigurasi.TAG_ID);
            String k1 = c.getString(konfigurasi.TAG_KOR1);
            String k2 = c.getString(konfigurasi.TAG_KOR2);
            String k3 = c.getString(konfigurasi.TAG_KOR3);
            String k4 = c.getString(konfigurasi.TAG_KOR4);


            idEmp.setText(ed);
            kor1.setText(k1);
            kor2.setText(k2);
            kor3.setText(k3);
            kor4.setText(k4);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
    private void showLatlang(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(konfigurasi.TAG_ID);
                String k1 = jo.getString(konfigurasi.TAG_KOR1);
                String k2 = jo.getString(konfigurasi.TAG_KOR2);
                String k3 = jo.getString(konfigurasi.TAG_KOR3);
                String k4 = jo.getString(konfigurasi.TAG_KOR4);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(konfigurasi.TAG_KOR1, k1);
                employees.put(konfigurasi.TAG_KOR2, k2);
                employees.put(konfigurasi.TAG_KOR3, k3);
                employees.put(konfigurasi.TAG_KOR4, k4);
                list.add(employees);

                kor1.setText(k1);
                kor2.setText(k2);
                kor3.setText(k3);
                kor4.setText(k4);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void getLatlang(){
        class GetLatlang extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MapsActivity.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showLatlang();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(konfigurasi.URL_GET_LATLONG);
                return s;
            }
        }
        GetLatlang gj = new GetLatlang();
        gj.execute();
    }

            **/

}
