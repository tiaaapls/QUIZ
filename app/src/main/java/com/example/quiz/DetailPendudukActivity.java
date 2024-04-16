package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailPendudukActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_penduduk);

        Intent intent= getIntent();
        String nik = intent.getStringExtra("NIK");
        String nama = intent.getStringExtra("Nama Lengkap");
        String gender = intent.getStringExtra("Jenis Kelamin");
        String tempatlahir = intent.getStringExtra("Tempat Lahir");
        String tanggallahir = intent.getStringExtra("Tanggal Lahir");
        String alamat = intent.getStringExtra("Alamat");
        String email = intent.getStringExtra("Email");
        String telepon = intent.getStringExtra("Telepon");

        TextView hasil = findViewById(R.id.txt_hasil);
        hasil.setText("NIK : \n"+ nik + "\n\nNama Lengkap : \n" + nama + "\n\nJenis Kelamin : \n" + gender +
                "\n\nTempat Lahir : \n" + tempatlahir + "\n\nTanggal Lahir : \n" + tanggallahir + "\n\nAlamat : \n" + alamat +
                "\n\nEmail : \n" + email + "\n\nTelepon : \n" + telepon);
    }
}

