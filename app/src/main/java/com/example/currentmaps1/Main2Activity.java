package com.example.currentmaps1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity{

    private TextView code;
    private TextView mayotuna;
    private TextView tunahot;
    private TextView mayochicken;
    private TextView chickenhot;
    private TextView tr,id2;
    private TextView ttl;

    private Button buttonUpdate;
    private Button buttonDelete;
    final int kraguman = 112;
    final int wedi = 113;

    private String id;
    private String JSON_STRING;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final Intent intent = getIntent();

        id = intent.getStringExtra(konfigurasi.EMP_ID);

        code = (TextView) findViewById(R.id.textView56);
        mayotuna = (TextView) findViewById(R.id.textView51);
        tunahot = (TextView) findViewById(R.id.textView52);
        mayochicken = (TextView) findViewById(R.id.textView53);
        chickenhot = (TextView) findViewById(R.id.textView54);
        tr = (TextView) findViewById(R.id.textView55);
        id2 = (TextView) findViewById(R.id.id2);

        /**buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
         buttonDelete = (Button) findViewById(R.id.buttonDelete);

         buttonUpdate.setOnClickListener(this);
         buttonDelete.setOnClickListener(this);**/

        /**textView56.setText(id);**/

        getEmployee();

        final TextView hijau_Db = (TextView) findViewById(R.id.textView51);
        final TextView merah_Db = (TextView) findViewById(R.id.textView52);
        final TextView biru_Db = (TextView) findViewById(R.id.textView53);
        final TextView orange_Db = (TextView) findViewById(R.id.textView54);
        final TextView ungu_Db = (TextView) findViewById(R.id.textView55);

        final EditText tunaMayo = (EditText) findViewById(R.id.editTextTunamayo);
        final EditText hotTuna = (EditText) findViewById(R.id.editTextHottuna);
        final EditText chickenMayo = (EditText) findViewById(R.id.editTextChickenmayo);
        final EditText hotChicken = (EditText) findViewById(R.id.editTextHotchicken);
        final EditText teri = (EditText) findViewById(R.id.editTextTeri);
        final EditText nrb = (EditText)findViewById(R.id.editTextNrb);
        final EditText bpb = (EditText) findViewById(R.id.editTextBpb);
        Button persetujuan = (Button) findViewById(R.id.button1);

        persetujuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**Intent intent = new Intent(Main2Activity.this, Finish.class);
                intent.putExtra("ambil_HijauMysql", hijau_Db.getText().toString());
                intent.putExtra("ambil_MerahMysql", merah_Db.getText().toString());
                intent.putExtra("ambil_BiruMysql", biru_Db.getText().toString());
                intent.putExtra("ambil_OrangeMysql", orange_Db.getText().toString());
                intent.putExtra("ambil_UnguMysql", ungu_Db.getText().toString());
                intent.putExtra("ambilDataTunaMayo", tunaMayo.getText().toString());
                intent.putExtra("ambilDataHotTuna", hotTuna.getText().toString());
                intent.putExtra("ambilDataChickenMayo", chickenMayo.getText().toString());
                intent.putExtra("ambilDataHotChicken", hotChicken.getText().toString());
                intent.putExtra("ambilDataTeri", teri.getText().toString());
                intent.putExtra("ambilDataNrb", nrb.getText().toString());
                intent.putExtra("ambilDataBpb", bpb.getText().toString());
                startActivity(intent);**/

                int nilai = Integer.parseInt(code.getText().toString());

                if (nilai == 112){
                    Intent kraguman = new Intent(Main2Activity.this, MapsActivity.class);

                    kraguman.putExtra("id2",id2.getText().toString());
                    kraguman.putExtra("ambil_HijauMysql", hijau_Db.getText().toString());
                    kraguman.putExtra("ambil_MerahMysql", merah_Db.getText().toString());
                    kraguman.putExtra("ambil_BiruMysql", biru_Db.getText().toString());
                    kraguman.putExtra("ambil_OrangeMysql", orange_Db.getText().toString());
                    kraguman.putExtra("ambil_UnguMysql", ungu_Db.getText().toString());
                    kraguman.putExtra("ambilDataTunaMayo", tunaMayo.getText().toString());
                    kraguman.putExtra("ambilDataHotTuna", hotTuna.getText().toString());
                    kraguman.putExtra("ambilDataChickenMayo", chickenMayo.getText().toString());
                    kraguman.putExtra("ambilDataHotChicken", hotChicken.getText().toString());
                    kraguman.putExtra("ambilDataTeri", teri.getText().toString());
                    kraguman.putExtra("ambilDataNrb", nrb.getText().toString());
                    kraguman.putExtra("ambilDataBpb", bpb.getText().toString());
                    startActivity(kraguman);
                }else if (nilai == 118){
                    Intent intent = new Intent(Main2Activity.this, Finish.class);
                    intent.putExtra("ambil_HijauMysql", hijau_Db.getText().toString());
                    intent.putExtra("ambil_MerahMysql", merah_Db.getText().toString());
                    intent.putExtra("ambil_BiruMysql", biru_Db.getText().toString());
                    intent.putExtra("ambil_OrangeMysql", orange_Db.getText().toString());
                    intent.putExtra("ambil_UnguMysql", ungu_Db.getText().toString());
                    intent.putExtra("ambilDataTunaMayo", tunaMayo.getText().toString());
                    intent.putExtra("ambilDataHotTuna", hotTuna.getText().toString());
                    intent.putExtra("ambilDataChickenMayo", chickenMayo.getText().toString());
                    intent.putExtra("ambilDataHotChicken", hotChicken.getText().toString());
                    intent.putExtra("ambilDataTeri", teri.getText().toString());
                    intent.putExtra("ambilDataNrb", nrb.getText().toString());
                    intent.putExtra("ambilDataBpb", bpb.getText().toString());
                    startActivity(intent);
                }

            }
        });



    }




    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Main2Activity.this,"Tunggu Yaa...","Sabaarr!!..",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_EMP,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }


    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String ed = c.getString(konfigurasi.TAG_ID);
            String kd = c.getString(konfigurasi.TAG_KODE);
            String hijau = c.getString(konfigurasi.TAG_TUNA_MAYO);
            String merah = c.getString(konfigurasi.TAG_HOT_TUNA);
            String biru = c.getString(konfigurasi.TAG_CHICKEN_MAYO);
            String orange = c.getString(konfigurasi.TAG_HOT_CHICKEN);
            String ungu = c.getString(konfigurasi.TAG_TERI);

            id2.setText(ed);
            code.setText(kd);
            mayotuna.setText(hijau);
            tunahot.setText(merah);
            mayochicken.setText(biru);
            chickenhot.setText(orange);
            tr.setText(ungu);







        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
