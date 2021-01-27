package com.example.infinigentconsulting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SchemeAuditActivity extends AppCompatActivity {
    private ImageButton _input_scheme_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme_audit);
        _input_scheme_button = findViewById(R.id.input_scheme_audit_btn);
        _input_scheme_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SchemeAuditActivity.this, OutletDetailsActivity.class);
                startActivity(i);
            }
        });
    }
}