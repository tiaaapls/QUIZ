package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Calendar;


public class RegistrasiPendudukActivity extends AppCompatActivity {
    private EditText editNIK, editNama, editTempatLahir, editAlamat, editEmail, editTelepon;
    RadioButton rb_L, rb_P;
    RadioGroup rg;
    private Button btnDate, btnSubmit;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi_penduduk);
        initDatePicker();

        editNIK = findViewById(R.id.edit_nik);
        editNama = findViewById(R.id.edit_nama);
        rb_L = (RadioButton) findViewById(R.id.rb_laki2);
        rb_P = (RadioButton) findViewById(R.id.rb_perempuan);
        rg = (RadioGroup) findViewById(R.id.radio_group);
        editTempatLahir = findViewById(R.id.edit_tempatlahir);
        btnDate = findViewById(R.id.btn_date);
        btnDate.setText(getTodayDate());
        editAlamat = findViewById(R.id.edit_alamat);
        editEmail = findViewById(R.id.edit_email);
        editTelepon = findViewById(R.id.edit_telepon);
        btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setEnabled(false);
        
        addTextWatchers();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nik =editNIK.getText().toString();
                String nama =editNama.getText().toString();
                String gender = "";
                if (rb_L.isChecked()) {
                    gender = "Laki-laki";
                } else {
                    gender = "Perempuan";
                }
                String tempatlahir =editTempatLahir.getText().toString();
                String tanggallahir =btnDate.getText().toString();
                String alamat =editAlamat.getText().toString();
                String email =editEmail.getText().toString();
                String telepon =editTelepon.getText().toString();

                Intent intent = new Intent(RegistrasiPendudukActivity.this, DetailPendudukActivity.class);
                intent.putExtra("NIK", nik);
                intent.putExtra("Nama Lengkap", nama);
                intent.putExtra("Jenis Kelamin", gender);
                intent.putExtra("Tempat Lahir", tempatlahir);
                intent.putExtra("Tanggal Lahir", tanggallahir);
                intent.putExtra("Alamat", alamat);
                intent.putExtra("Email", email);
                intent.putExtra("Telepon", telepon);
                startActivity(intent);
            }
        });
    }

    private void addTextWatchers() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                checkFormValidity();
            }
        };
        editNIK.addTextChangedListener(textWatcher);
        editNama.addTextChangedListener(textWatcher);
        editTempatLahir.addTextChangedListener(textWatcher);
        editAlamat.addTextChangedListener(textWatcher);
        editEmail.addTextChangedListener(textWatcher);
        editTelepon.addTextChangedListener(textWatcher);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkFormValidity();
            }
        });
    }

    private void checkFormValidity() {
        boolean isValid = editNIK.getText().toString().trim().length() > 0 &&
                editNama.getText().toString().trim().length() > 0 &&
                (rb_L.isChecked() || rb_P.isChecked()) &&
                editTempatLahir.getText().toString().trim().length() > 0 &&
                btnDate.getText().toString().trim().length() > 0 &&
                editAlamat.getText().toString().trim().length() > 0 &&
                editEmail.getText().toString().trim().length() > 0 &&
                editTelepon.getText().toString().trim().length() > 0;

        btnSubmit.setEnabled(isValid);
    }

    private String getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month = month + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String makeDateString(int day, int month, int year) {
        return day + " " + getMonthFormat(month) + " " + year;
    }

    private String getMonthFormat(int month) {
        switch (month) {
            case 1:
                return "Januari";
            case 2:
                return "Februari";
            case 3:
                return "Maret";
            case 4:
                return "April";
            case 5:
                return "Mei";
            case 6:
                return "Juni";
            case 7:
                return "Juli";
            case 8:
                return "Agustus";
            case 9:
                return "September";
            case 10:
                return "Oktober";
            case 11:
                return "November";
            case 12:
                return "Desember";
            default:
                return "Januari";
        }
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                btnDate.setText(date);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
}