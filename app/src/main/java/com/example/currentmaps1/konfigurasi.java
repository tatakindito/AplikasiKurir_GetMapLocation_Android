package com.example.currentmaps1;

public class konfigurasi {

    //gwalakb.000webhostapp.com/suki_new/koneksi.php
    //https://lopersuki.000webhostapp.com/

    //JANGAN LUPA SESUAIKAN IP KOMPUTER DIMANA DATA PHP HTDOCS ANDROIDS BERADA
    public static final String URL_ADD="http://192.168.43.230/suki_new/tambah.php";
    public static final String URL_ADD_RETUR ="http://lopersuki.000webhostapp.com/suki_new/tambah_retur.php";
    public static final String URL_GET_ALL = "http://lopersuki.000webhostapp.com/suki_new/tampilSemua.php";
    public static final String URL_GET_LATLONG = "http://lopersuki.000webhostapp.com/suki_new/tampilLatlong.php?id=";
    public static final String URL_GET_EMP = "http://lopersuki.000webhostapp.com/suki_new/tampil.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.43.230/suki_new/update.php";
    public static final String URL_DELETE_EMP = "http://192.168.43.230/suki_new/hapus.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Scrip PHP
    public static final String KEY_EMP_ID = "id"; //id itu variabel untuk id
    public static final String KEY_KODE = "code"; //id itu variabel untuk id
    public static final String KEY_OUTLET = "toko"; //id itu variabel untuk id
    public static final String KEY_TANGGAL = "tanggal"; //id itu variabel untuk id
    public static final String KEY_TUNA_MAYO = "mayotuna"; //name itu variabel untuk name
    public static final String KEY_HOT_TUNA = "tunahot"; //desg itu variabel untuk posisi
    public static final String KEY_CHICKEN_MAYO = "mayochicken"; //salary itu variabel untuk gajih
    public static final String KEY_HOT_CHICKEN = "chickenhot"; //salary itu variabel untuk gajih
    public static final String KEY_TERI = "tr"; //salary itu variabel untuk gajih
    public static final String KEY_NRB = "nrB"; //salary itu variabel untuk gajih
    public static final String KEY_BPB = "bpB"; //salary itu variabel untuk gajih

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_KODE = "code";
    public static final String TAG_TUNA_MAYO = "mayotuna";
    public static final String TAG_HOT_TUNA = "tunahot";
    public static final String TAG_CHICKEN_MAYO = "mayochicken";
    public static final String TAG_HOT_CHICKEN = "chickenhot";
    public static final String TAG_TERI = "tr";
    public static final String TAG_NAMA_TOKO = "toko";
    public static final String TAG_TOTAL = "total";
    public static final String TAG_TANGGAL = "tanggal";

    public static final String TAG_KOR1 = "kor1"; //latlong
    public static final String TAG_KOR2 = "kor2"; //latlong
    public static final String TAG_KOR3 = "kor3"; //latlong
    public static final String TAG_KOR4 = "kor4"; //latlong


    //ID karyawan
    public static final String EMP_ID = "emp_id"; //emp itu singkatan dari Employee
    public static final String KODE_TOKO = "code_toko"; //emp itu singkatan dari Employee
}