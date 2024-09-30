package com.pankaj.contactadder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView tvWelcome,tvHelp,tvResult,textView ;
    Button button;
    ImageView imageView, ivcall, ivlocation;
    final int ACTIVITY2_REQUEST = 1;

    String name = "",address = "",phone = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvWelcome = findViewById(R.id.tvWelcome);
        tvHelp = findViewById(R.id.tvHelp);
        button = findViewById(R.id.button);
        tvResult = findViewById(R.id.tvResult);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        ivcall = findViewById(R.id.ivcall);
        ivlocation = findViewById(R.id.ivlocation);


        tvResult.setVisibility(View.GONE);
        ivcall.setVisibility(View.GONE);
        ivlocation.setVisibility(View.GONE);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.pankaj.contactadder.MainActivity2.class);
                startActivityForResult(intent,ACTIVITY2_REQUEST);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                startActivity(intent);
            }
        });



        ivcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+phone));
                startActivity(intent);
            }
        });

        ivlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("geo:0,0?q="+address));
                startActivity(intent);
            }
        });

    }

    //get data from MainActivity2
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ACTIVITY2_REQUEST){
            if(resultCode == RESULT_OK){
                assert data != null;
                ivcall.setVisibility(View.VISIBLE);
                ivlocation.setVisibility(View.VISIBLE);
                tvResult.setVisibility(View.VISIBLE);

                name = data.getStringExtra("name");
                phone = data.getStringExtra("phone");
                address = data.getStringExtra("address");

                String contact = "name: "+name+"\n"+"phone: "+phone+"\n"+"address: "+address;
                tvResult.setText(contact);

            }
            if(resultCode == RESULT_CANCELED){
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }
}