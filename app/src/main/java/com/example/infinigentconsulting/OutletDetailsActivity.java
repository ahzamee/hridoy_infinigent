package com.example.infinigentconsulting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class OutletDetailsActivity extends AppCompatActivity {
    private ImageButton _outlate_previous_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlet_details);
        _outlate_previous_button = findViewById(R.id.outlate_previous_button);
        _outlate_previous_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OutletDetailsActivity.this, SchemeDetailsActivity.class);
                startActivity(i);
            }
        });
    }
}