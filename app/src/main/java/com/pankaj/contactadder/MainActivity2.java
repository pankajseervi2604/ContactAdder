package com.pankaj.contactadder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    TextView tvIntro;
    EditText etName, etPhone, etAddress;
    Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvIntro = findViewById(R.id.tvIntro);
        etName = findViewById(R.id.txtName);
        etPhone = findViewById(R.id.txtPhone);
        etAddress = findViewById(R.id.txtAddress);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().toString().isEmpty() || etPhone.getText().toString().isEmpty() || etAddress.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity2.this, "Please enter all fields....", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent();
                    intent.putExtra("name",etName.getText().toString().trim());
                    intent.putExtra("phone",etPhone.getText().toString().trim());
                    intent.putExtra("address",etAddress.getText().toString().trim());
                    setResult(RESULT_OK,intent);
                    MainActivity2.this.finish();
                }
            }
        });
    }
}