package com.example.infinigentconsulting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ImageBrowsingActivity extends AppCompatActivity {
    private ImageButton _img_previous_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_browsing);
        _img_previous_button = findViewById(R.id.img_previous_button);
        _img_previous_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ImageBrowsingActivity.this, SchemeDetailsActivity.class);
                startActivity(i);
            }
        });
    }
}